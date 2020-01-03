/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica.nethammer;

import icametaagent.HammerUser;
import icamessages.Message;
import icamessages.MessageType;
import icametaagent.Portal;
import icametaagent.SocketAgent;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author v8073331
 */
public class nethammer {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        Scanner keyb = new Scanner(System.in);
        
        
        //CMDMonitor monitor = new CMDMonitor("r1");
        //router.addObserver(monitor);
        Portal[] portals;
        
        HammerUser[] users;
        
        System.out.print("Enter the IP address: ");
        String ip = keyb.nextLine();
        
        System.out.print("How many portals to create: ");
        while(!keyb.hasNextInt()){
            keyb.nextLine();
        }
        
        int portalsno = keyb.nextInt();
        keyb.nextLine();
        
        portals = new Portal[portalsno];
        
        for(int x = 0; x < portalsno; x++){
            Portal p = new Portal("p-" + x);
            //CMDMonitor mon = new CMDMonitor("p-" + x);
            //p.addObserver(mon);
            Socket s = new Socket(ip, 42069);
            SocketAgent sa = new SocketAgent(p, s);
            sa.start();
            
            sa.messageHandler(p, new Message(p.getName(), "GLOBAL", MessageType.ADD_PORTAL, ""));
            
            portals[x] = p;
        }
        
        System.out.print("How many agents to create to each portal: ");
        
        while(!keyb.hasNextInt()){
            keyb.nextLine();
        }
        int agentsno = keyb.nextInt();
        keyb.nextLine();
        
        users = new HammerUser[agentsno * portalsno];
        
        for(int x = 0; x < portalsno; x++){
            for(int y = 0; y < agentsno; y++){
                
                HammerUser user = new HammerUser("a-" + ((x*agentsno)+y), portals[x]);
                
                portals[x].messageHandler(user, new Message(user.getName(), "GLOBAL", MessageType.ADD_METAAGENT, ""));
                
                users[x*agentsno + y] = user;
            }
        }
        
        Thread.sleep(1000);
        
        System.out.print("How many messages to send from each of the agents: ");
        
        
        while(!keyb.hasNextInt()){
            keyb.nextLine();
        }
        int agentsmsgc = keyb.nextInt();
        keyb.nextLine();
        
        Random rand = new Random();
        
        for(int x = 0; x < portals[0].routingTable.size(); x++){
            for(int y = 0; y < agentsmsgc; y++){
                int u = rand.nextInt(portals[0].routingTable.size());
                
                users[x].connection.messageHandler(users[x], new Message(users[x].getName(), ("a-" + u), MessageType.USER_MSG, String.valueOf(System.currentTimeMillis())));
            }
        }
        Thread.sleep(5000);
        
        
        System.exit(0);
    }
}
