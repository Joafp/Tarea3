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
    private Socket sc;
    public ClienteHilo(DataInputStream in,DataOutputStream out,Socket sc){
        this.in=in;
        this.out=out;
        this.sc=sc;
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
            System.out.println("5 Listar NIS activos");
            System.out.println("6 Listar NIS inactivos");
            System.out.println("7 lista consumos");
            try{
                Log myLog=new Log("log.txt");
                opcion=sn.nextInt();
                out.writeInt(opcion);
                switch(opcion){
                    case 1:
                        System.out.println("Ingrese el consumo");
                        int consumo=sn.nextInt();
                        out.writeInt(consumo);
                        mensaje=in.readUTF();
                        System.out.println(mensaje);
                        myLog.addLine("Origen: "+sc.getInetAddress()+" Destino"+sc.getPort()+"Ingresar consumo");
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 5:
                        int tama=in.readInt();
                        int nisa;
                        int estadoa;
                        System.out.println("Lista de usuarios activos");
                        for(int i=0;i<tama;i++){
                            nisa=in.readInt();
                            estadoa=in.readInt();
                            if (estadoa==1){
                                System.out.println(nisa);
                            }
                        }
                        myLog.addLine("Origen: "+sc.getInetAddress()+" Destino"+sc.getPort()+"Listar usuario activos");
                        break;
                    case 6:
                        int tami=in.readInt();
                        int nisi;
                        int estadoi;
                        System.out.println("Lista de usuarios Inactivos");
                        for(int i=0;i<tami;i++){
                            nisi=in.readInt();
                            estadoi=in.readInt();
                            if (estadoi==0){
                                System.out.println(nisi);
                            }
                        }
                        myLog.addLine("Origen: "+sc.getInetAddress()+" Destino"+sc.getPort()+"Listar usuario inactivos");
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
