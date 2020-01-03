/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaGUI;


import icamessages.Message;
import icametaagent.Portal;
import icametaagent.Router;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author v8077971
 * @author v8036651
 * @author v8073331
 */
//JScrollPane scrollPane = new JScrollPane(record);
//        record.setFillsViewportHeight(true);
//        record.setVisible(true);
//        record.setPreferredSize(new Dimension((int)(size.getWidth() / 4) - 1, (int)(size.getHeight() / 10) -1));
//        recordPanel.add(record);
public class ObserverInterface {

    private final Dimension size;
    protected int counter = 0;

    final String[] columnNames = {"Sender", "Actual Sender", "Recipient", "Actual Recipient", "Message Type", "Message Data", "Date", "Message Number"};
    JTable record;
    JScrollPane scrollPane;
    final JPanel mainPanel;
    public Object[][] data = new Object[][]{};

    private Portal portal;
    private Router router;

    public ObserverInterface(Dimension d) {

        record = new JTable(new DefaultTableModel(data, columnNames));
        scrollPane = new JScrollPane(record);
        size = d;

        mainPanel = new JPanel(new GridLayout(1, 1));
        mainPanel.setSize(size);
        mainPanel.add(scrollPane);
    }

    public void update(Message msg,String actualSender, String actualRecipient) 
    {
        FileWriter fileWriter = null;
        
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            DefaultTableModel model = (DefaultTableModel) record.getModel();
            counter = counter+1;
            /**
             * Writes to a file the same data that is to be put into the table.
             */
            String str = "Message Sender: " + msg.getSender() + " Actual Sender:" + actualSender + " Message Recipient:" + msg.getRecipient() + " Actual Recipient:" + actualRecipient + " Message Type:" + msg.getMessageType() + " Message Date and Time:" + formatter.format(date) + " Message Number:" + counter + "\n Message Details:" + msg.getMessageDetails() + "\n";
            try {
            fileWriter = new FileWriter("JTableData.txt", true);
            BufferedWriter Writer = new BufferedWriter(fileWriter);
            Writer.write(str);
            Writer.close();
            }
            catch (IOException ex) 
            {
            Logger.getLogger(ObserverInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * Checks for if the current number of rows exceeds 500 and if so then
             * removes first one from the table and adds a new one at the end.
             */
            if (record.getRowCount() < 500)
            {
                model.addRow(new Object[]{msg.getSender(),actualSender, msg.getRecipient(), actualRecipient, msg.getMessageType(),msg.getMessageDetails(), formatter.format(date),counter});
                record.setModel(model);
                //auto scroll
                record.changeSelection(record.getRowCount()-1, 0, false, false);
            }
            else
            {
                model.removeRow(0);
                model.addRow(new Object[]{msg.getSender(),actualSender, msg.getRecipient(), actualRecipient, msg.getMessageType(),msg.getMessageDetails(), formatter.format(date),counter});
                record.setModel(model);
                //auto scroll
                record.changeSelection(record.getRowCount()-1, 0, false, false);
            }
            mainPanel.revalidate();
            mainPanel.repaint();
            
            
        } 
        }
    
        

