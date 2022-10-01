package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.net.Socket;
import java.util.logging.Logger;

import org.junit.platform.reporting.shadow.org.opentest4j.reporting.events.core.HostName;
public class TCP_CLIENTE {
    public static void main(String [] args) throws InterruptedException{
        final String HOST="127.0.0.1";
        final int PUERTO=5000;
        DataInputStream in;
        DataOutputStream out;
        try{
            Log myLog=new Log("log.txt");
            Scanner sn=new Scanner(System.in);
            String mensaje=new String();
            System.out.println("Para ingresar al servidor por favor ingrese su numero NIS");
            int NIS=sn.nextInt();
            Socket sc=new Socket(HOST,PUERTO);
            in =new DataInputStream(sc.getInputStream());
            out=new DataOutputStream(sc.getOutputStream());
            out.writeInt(NIS);
            mensaje=in.readUTF();
            System.out.println(mensaje);
            int aux=in.readInt();
            if (aux==1){
                myLog.addLine("Origen: "+sc.getLocalPort()+" Destino: "+sc.getPort()+" Realizar conexion");
                ClienteHilo hilo=new ClienteHilo(in,out,sc,NIS);
                hilo.start();
                hilo.join();
                sc.close();
            }else{
                System.out.println("Programa terminado");
            }
           
        }catch(IOException ex){
            Logger.getLogger(TCP_CLIENTE.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
