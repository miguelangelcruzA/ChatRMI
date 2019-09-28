/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demormi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author migue
 */
public class Server {
    public static void main(String[] args) {
        try {
             ServerImpl obj = new ServerImpl();
             Registry registro = LocateRegistry.createRegistry(1099);             
             registro.bind("Callback", obj);
             System.out.println("Server listo");
        } 
        catch (Exception e) {
            
        }

        
        
        
    }
}
