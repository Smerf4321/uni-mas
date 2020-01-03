/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icamonitors;

import icamessages.Message;
import java.util.Observable;

/**
 *
 * @author v8036651
 */
public class CMDMonitor extends Monitor
{
    /**
     * A name of the agent must be passed as it says which agent the monitor is on,
     * this is to compare the intended recipient and the actual,
     * requires a message to be passed for the output.
     * @param name 
     * @author v8036651
     */
    public CMDMonitor(String name) 
    {
        super(name);
    }
    
    /**
     * Method to output to the command line showing the name of the sender, the
     * name of the recipient, the actual recipient, the message type and the
     * message details when a message is received.
     * @parm message
     * @author v8036651
     */
    @Override
    public void ReceivedMessage (Message message,String actualSender)
    {
        System.out.println("+==========================================+");
        System.out.println("|This message was received from:           |");
        System.out.printf("|%42s|\n",message.getSender());
        System.out.println("|This message was received by:             |");
        System.out.printf("|%42s|\n",agentName);
        System.out.println("|This message was meant to be received by: |");
        System.out.printf("|%42s|\n",message.getRecipient());
        System.out.println("|This message type is:                     |");
        System.out.printf("|%42s|\n",message.getMessageType());
        System.out.println("|The contents of the message is:           |");
        System.out.printf("|%42s|\n",message.getMessageDetails());
        System.out.println("+==========================================+");
        System.out.println("");
    }
    
    /**
     * Method to output to the command line showing the name of the sender, the
     * name of the intended sender, the intended recipient, the message type and the
     * message details when a message is sent.
     * @parm message
     * @author v8036651
     */
    @Override
    public void SentMessage (Message message,String actualSender)
    {
        System.out.println("+==========================================+");
        System.out.println("|This message was sent from:               |");
        System.out.printf("|%42s|\n",agentName);
        System.out.println("|This message is meant to be sent from:    |");
        System.out.printf("|%42s|\n",message.getSender());
        System.out.println("|This message is meant to be received by:  |");
        System.out.printf("|%42s|\n",message.getRecipient());
        System.out.println("|This message type is:                     |");
        System.out.printf("|%42s|\n",message.getMessageType());
        System.out.println("|The contents of the message is:           |");
        System.out.printf("|%42s|\n",message.getMessageDetails());
        System.out.println("+==========================================+");
        System.out.println("");
    }

    /**
     * Update method which shows the sender and recipient then the agent it is attached to.
     * @param o
     * @param message 
     * @author v8036651
     */
    public void update(Observable o, Object message,String actualSender) 
    {
        System.out.println("Hello world");
        this.ReceivedMessage((Message)message,actualSender);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

//1234567890
//1234567890
//1234567890
//1234567890
//1234
//PrintF length 44