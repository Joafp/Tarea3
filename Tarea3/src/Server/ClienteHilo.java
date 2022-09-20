package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.Scanner;
public class ClienteHilo extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    public ClienteHilo(DataInputStream in,DataOutputStream out){
        this.in=in;
        this.out=out;
    }
    @Override
    public void run(){
        String mensaje=new String();
        Scanner sn=new Scanner(System.in);
        int opcion=0;
        boolean salir=false;
        while(!salir){
            System.out.println("1. Almacenar consumo en el archivo");
            System.out.println("2. Consumo hasta el momento");
            System.out.println("3. Salir");
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
