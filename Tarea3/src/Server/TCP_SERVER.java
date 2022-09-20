package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
public class TCP_SERVER{
    public static void main(String []args){
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
                System.out.println("Cliente conectado");
                in =new DataInputStream(sc.getInputStream());
                out=new DataOutputStream(sc.getOutputStream());
                out.writeUTF("Indica tu numero de identificacion");
                String nombreCliente=in.readUTF();
                ServerHilo hilo=new ServerHilo(in,out,nombreCliente);
                hilo.start();
                System.out.println("Creada la conexion con: "+nombreCliente);

            }
        }catch(IOException ex){
            Logger.getLogger(TCP_SERVER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}