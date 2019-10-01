package demormi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
            String destino,mensaje,name;
            String opcion = "";    
        try {                                
            Registry registro = LocateRegistry.getRegistry(1099);
            InterfazCliente cliente = new ClienteImpl();
            InterfazServer server = (InterfazServer) registro.lookup("Callback");
            
            
            System.out.println("Ingrese su nombre");
            name  = in.nextLine();           
            server.registrarme(cliente,name);          
             System.out.println("Eliga una opcion \n "
                                + "1 ---> para enviar mensaje al grupo \n "
                                + "2 ---> para enviar mensaje privado");
            do{               
                 opcion = in.nextLine();
                switch(opcion){
                    case "1": 
                        System.out.println("Ingrese su mensaje");
                         mensaje = in.nextLine();
                         mensaje = server.encriptar(mensaje);
                         server.sendbroadcastGroup(name,mensaje);
                         
                        break;
                    case "2":
                        System.out.println("Ingrese el destino");
                        destino = in.nextLine();
                        System.out.println("Ingrese el mensaje");                         
                        mensaje = in.nextLine();
                        mensaje = server.encriptar(mensaje);
                        server.sendbroadcast(destino, mensaje, name);                       
                        break; 
                        default:
                            System.out.println("opcion no valida");
                }
            }while(!opcion.equalsIgnoreCase("0"));            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
