package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.net.Socket;
import java.util.logging.Logger;
public class TCP_CLIENTE {
    public static void main(String [] args) throws InterruptedException{
        final String HOST="127.0.0.1";
        final int PUERTO=5000;
        DataInputStream in;
        DataOutputStream out;
        try{
            Socket sc=new Socket(HOST,PUERTO);
            in =new DataInputStream(sc.getInputStream());
            out=new DataOutputStream(sc.getOutputStream());
            Scanner sn=new Scanner(System.in);
            String mensaje=in.readUTF();
            System.out.println(mensaje);
            int NIS=sn.nextInt();
            out.writeInt(NIS);
            ClienteHilo hilo=new ClienteHilo(in,out,sc);
            hilo.start();
            hilo.join();
            sc.close();
        }catch(IOException ex){
            Logger.getLogger(TCP_CLIENTE.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
