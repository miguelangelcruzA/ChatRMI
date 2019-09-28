/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demormi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author migue
 */
public class ServerImpl extends UnicastRemoteObject implements InterfazServer{
    ArrayList<InterfazCliente> lista;
    HashMap<String, InterfazCliente> lista02;
    String name;
    
    public ServerImpl() throws RemoteException{
        super();
        lista = new ArrayList<InterfazCliente>();
        lista02 = new HashMap<String,InterfazCliente>();
        //sustituir un arraylist por un hashmap
    }
    
    
    @Override
    public synchronized void registrarme(InterfazCliente cliente,String nombre) throws RemoteException {
        lista.add(cliente);
        lista02.put(nombre, cliente);
        cliente.notificacion("se registrado el usuario con nombre " + nombre);
        
        
        broadcast(nombre);
    }

    @Override
    public synchronized void unregisterme(String nickname) throws RemoteException {
        for(InterfazCliente c: lista02.values()){
            try {
                c.notificacion("el usuario " + nickname + " ha sido removido");
                
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }        
        lista02.remove(nickname);
        
    }
    private void broadcast(String nickname) throws RemoteException{
        for (InterfazCliente c: lista02.values()) {
            try {
                c.notificacion("usuarios registrados: " + lista02.keySet());
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }            
        }        
    }

    @Override
    public  void sendbroadcast(String nickname, String mensaje,String origen) throws RemoteException {                
        lista02.get(nickname).notificacion(origen, mensaje);                
    }

    @Override
    public void setname(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String getname() throws RemoteException {
        return name;    
       }
    @Override
    public void sendbroadcastGroup(String nickname, String mensaje) throws RemoteException {
        for (InterfazCliente c: lista02.values()) {
            try {
                c.notificacion(nickname, mensaje);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
            
        }
    }
 
}
