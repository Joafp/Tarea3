package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.Scanner;
public class ClienteHilo extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private int NIS;
    public ClienteHilo(DataInputStream in,DataOutputStream out){
        this.in=in;
        this.out=out;
    }
    @Override
    public void run(){
        String mensaje=new String();
        System.out.println("ingrese la opcion");
        Scanner sn=new Scanner(System.in);
        int opcion=0;
        boolean salir=false;
        while(!salir){
            System.out.println("1. Almacenar consumo en el archivo");
            System.out.println("2. Consumo hasta el momento");
            System.out.println("3. Para desconexion");
            System.out.println("4. Para realizar una conexion al servidor con el NIS especifico");
            System.out.println("5 Listar NIS activos");
            System.out.println("6 Listar NIS inactivos");
            try{
                opcion=sn.nextInt();
                out.writeInt(opcion);
                switch(opcion){
                    case 1:
                        System.out.println("Ingrese el consumo");
                        int consumo=sn.nextInt();
                        out.writeInt(consumo);
                        mensaje=in.readUTF();
                        System.out.println(mensaje);
                        break;
                    case 2:
                        break;
                    case 3:
                     System.out.println("Indique numero de NIS a ser desconectado");
                     int NIS=sn.nextInt();
                     out.writeInt(NIS);
                     System.out.println("Realizando desconexion");
                     mensaje=in.readUTF();
                     System.out.println(mensaje);
                     break;
                    case 4:
                     System.out.println("Indique numero de NIS");
                     NIS=sn.nextInt();
                     out.writeInt(NIS);
                     mensaje=in.readUTF();
                     System.out.println(mensaje);
                     break;
                    default:
                        mensaje=in.readUTF();
                        System.out.println(mensaje);
                }
            }catch(IOException ex){
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
        }
    }
}
