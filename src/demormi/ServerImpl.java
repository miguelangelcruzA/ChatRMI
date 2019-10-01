package demormi;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerImpl extends UnicastRemoteObject implements InterfazServer{    
    HashMap<String, InterfazCliente> lista02;
    String name;
    
    public ServerImpl() throws RemoteException{
        super();
        lista02 = new HashMap<String,InterfazCliente>();
        
    }     
    @Override
    public synchronized void registrarme(InterfazCliente cliente,String nombre) throws RemoteException {
        cliente.notificacion("se registrado el usuario " + nombre);
        lista02.put(nombre, cliente);
        
        
        
        broadcast(nombre);
    }
    @Override
    public synchronized void unregisterme(String nickname) throws RemoteException {
        for(InterfazCliente c: lista02.values()){
            try {
                c.notificacion("el usuario " + nickname + " ha sido removido");
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }        
        lista02.remove(nickname);
        
    }
    private void broadcast(String nickname) throws RemoteException{
        for (InterfazCliente c: lista02.values()) {
            try {
                c.notificacion("usuarios registrados: " + lista02.keySet());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }            
        }        
    }
    @Override
    public  void sendbroadcast(String nickname, String mensaje, String origen) throws RemoteException {                
        try {
        String info = origen + ": " + mensaje;  
        InterfazCliente c = lista02.get(nickname);        
            c.notificacion(origen + ": " + desencriptar(mensaje));
            
            //lista02.get(nickname).notificacion(origen +": " +  mensaje);                
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void sendbroadcastGroup(String nickname, String mensaje) throws RemoteException {
        for (InterfazCliente c: lista02.values()) {
            try {
                c.notificacion(nickname + ": " +  mensaje);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
    }        
    @Override
    public String encriptar(String msj) throws UnsupportedEncodingException {
     
     return Base64.getEncoder().encodeToString(msj.getBytes("utf-8"));
    
    }
    @Override
    public String desencriptar(String msj) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(msj.getBytes());
        return new String(decode,"utf-8");
    }
 
}
