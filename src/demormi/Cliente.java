
package demormi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import static javafx.application.Platform.exit;

/**
 *
 * @author migue
 */
public class Cliente {
    public static void main(String[] args) {
        try {
            String destino,mensaje;
            String opcion = "0";
            boolean condicion = true;
            Scanner in = new Scanner(System.in);
            Registry registro = LocateRegistry.getRegistry("192.168.100.4", 1099);
            InterfazCliente cliente = new ClienteImpl();
            InterfazServer server = (InterfazServer) registro.lookup("Callback");
            System.out.println("Ingrese su nombre para ser registrado");
            String name  = in.nextLine();
            server.setname(name);
            server.registrarme(cliente, server.getname());          
             System.out.println("Eliga una opcion \n 1 ---> para enviar mensaje al grupo \n 2 ---> para enviar mensaje privado \n 3 ---> para salir\n");
            do{               
                 opcion = in.nextLine();
                switch(opcion){
                    case "1": 
                        System.out.println("Ingrese su mensaje");
                         mensaje = in.nextLine();
                        server.sendbroadcastGroup(name,mensaje);
                        break;
                    case "2":
                        System.out.println("Ingrese el destino");
                        destino = in.nextLine();
                        System.out.println("Ingrese el mensaje");                         
                        mensaje = in.nextLine();
                        server.sendbroadcast(destino, mensaje, name);                       
                        break;
                    case "3":                                             
                        server.unregisterme(name);
                        condicion = false;                                      
                        break;
                    default:
                        System.out.println("no es una opcion valida");
                }
            }while(condicion);            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
    }
}
