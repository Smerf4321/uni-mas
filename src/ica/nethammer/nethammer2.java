/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica.nethammer;

import icametaagent.Router;
import java.io.IOException;

/**
 *
 * @author v8073331
 */
public class nethammer2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        Router router = new Router("r1");
        
        Thread t = new Thread(router);
        t.start();
        t.join();
    }
}
