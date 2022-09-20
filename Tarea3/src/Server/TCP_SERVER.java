package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
public class TCP_SERVER{
    List<ServerHilo> hiloservidor;
    List<Usuario> usuarios;
    public void ejecutar(){
        ServerSocket servidor=null;
        Socket sc=null;
        DataInputStream in;
        DataOutputStream out;
        final int PUERTO=5000;
        try{
            servidor=new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");
            while(true){
                sc=servidor.accept();
                in =new DataInputStream(sc.getInputStream());
                out=new DataOutputStream(sc.getOutputStream());
                ServerHilo hilo=new ServerHilo(in,out,usuarios,servidor);//le mando la lista de usuario para que el cliente pueda ver los activos e inactivos
                hilo.start();
                /* 
                System.out.println("Cliente conectado");
                out.writeUTF("Indica tu numero de identificacion");
                int NIS=in.readInt();
                Usuario us=new Usuario(NIS, 1);//Al conectar se pone el estado en 1
                usuarios.add(us);// se añade un nuevo usuario activo al servidor
                ServerHilo hilo=new ServerHilo(NIS,usuarios);//le mando la lista de usuario para que el cliente pueda ver los activos e inactivos
                hilo.start();
                System.out.println("Creada la conexion con: "+NIS);
                */
            }
            
        }catch(IOException ex){
            Logger.getLogger(TCP_SERVER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String []args){
       TCP_SERVER tms=new TCP_SERVER();
       tms.hiloservidor=new ArrayList<ServerHilo>();
       tms.usuarios=new ArrayList<Usuario>();
       tms.ejecutar();
    }
}