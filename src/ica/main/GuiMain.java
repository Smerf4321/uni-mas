package ica.main;


import icaGUI.ObserverGUI;
import icametaagent.Router;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vakus
 */
public class GuiMain {
    
    public static ObserverGUI gui;
    public static Router router;
    
    public static void main(String[] args) {
        gui = new ObserverGUI();
    }
}
