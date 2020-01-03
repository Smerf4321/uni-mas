/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icametaagent;

import icamessages.Message;
import icamessages.MessageType;

/**
 *
 * @author v8036651
 */
public class HammerUser extends MetaAgent
{
    public Portal connection;
    /**
     * Constructor for a user,
     * Draws from the super class of MetaAgent.
     * @param name
     * @param p 
     * @author v8036651
     */
    public HammerUser(String name, Portal p) 
    {
        super(name);
        connection = p;
        System.out.println("Created: " + name);
    }

    /**
     * Overwrites the messageHandler method,
     * This is used to display the message or pass the message on depending on
     * the recipient of the message.
     * @param agent
     * @param msg 
     * @author v8073331
     */
    @Override
    public void messageHandler(MetaAgent agent, Message msg) 
    {
        if(msg.getRecipient().equals(this.name) && msg.getMessageType().equals(MessageType.USER_MSG))
        {
            System.out.println("Took " + (System.currentTimeMillis() - Long.parseLong(msg.getMessageDetails())) + "ms to recieve message");
        }
        else
        {
            connection.messageHandler(this, new Message(this.name, msg.getSender(), MessageType.ERROR, "Message (" + msg.toString() + ") recieved by wrong agent"));
        }
    }
}