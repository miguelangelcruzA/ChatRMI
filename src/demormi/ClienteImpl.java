package demormi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClienteImpl extends UnicastRemoteObject implements InterfazCliente{
    
     public ClienteImpl() throws RemoteException{
                  
     }
    @Override
    public void notificacion(String info) throws RemoteException {
        System.out.println(info);
    }

//    @Override
  //  public void notificacion(String destino, String info) throws RemoteException {
    //    System.out.println( destino + ": " + info);
   // }

        
    
}
