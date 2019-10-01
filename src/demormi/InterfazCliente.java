package demormi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author migue
 */
public interface InterfazCliente extends Remote{
    public void notificacion(String info) throws RemoteException;	
    //public void notificacion(String nickname,String info) throws RemoteException;	


}

