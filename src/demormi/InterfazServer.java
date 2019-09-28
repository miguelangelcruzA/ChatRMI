/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demormi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author migue
 */
public interface InterfazServer extends Remote{
    
    public void registrarme(InterfazCliente cliente, String nickname) throws RemoteException;
    public void unregisterme(String name) throws RemoteException;
    public void sendbroadcast(String nickname, String mensaje,String origen) throws RemoteException;
    public void sendbroadcastGroup(String nickname ,String mensaje) throws RemoteException;
    public void setname(String name) throws RemoteException;
    public String getname() throws RemoteException;
    
    
    
	
}
