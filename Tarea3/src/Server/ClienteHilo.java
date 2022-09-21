package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.net.Socket;
import java.util.logging.Logger;
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
        boolean menu;
        while(!salir){
            menu=false;
            System.out.println("1. Almacenar consumo en el archivo");
            System.out.println("2. Para desconexion");
            System.out.println("3 Listar NIS activos");
            System.out.println("4 Listar NIS inactivos");
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
                        myLog.addLine("Origen: "+sc.getLocalPort()+" Destino: "+sc.getPort()+" Ingresar Consumo");
                        while(!menu){
                            System.out.println("Presiona 1 para volver al menu");
                            int aux=sn.nextInt();
                            if(aux==1){
                                menu=true;
                            }else{
                                System.out.println("Debes presionar 1 si quieres volver al menu");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Indique numero de NIS a ser desconectado");
                        int NIS=sn.nextInt();
                        out.writeInt(NIS);
                        System.out.println("Realizando desconexion");
                        mensaje=in.readUTF();
                        System.out.println(mensaje);
                        myLog.addLine("Origen: "+sc.getPort()+" Destino: "+sc.getInetAddress()+" Realizar desconexion");
                        while(!menu){
                            System.out.println("Presiona 1 para volver al menu");
                            int aux=sn.nextInt();
                            if(aux==1){
                                menu=true;
                            }else{
                                System.out.println("Debes presionar 1 si quieres volver al menu");
                            }
                        }
                        break;
                    case 3:
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
                        myLog.addLine("Origen: "+sc.getPort()+" Destino: "+sc.getInetAddress()+" Lista usuarios activos");
                        while(!menu){
                            System.out.println("Presiona 1 para volver al menu");
                            int aux=sn.nextInt();
                            if(aux==1){
                                menu=true;
                            }else{
                                System.out.println("Debes presionar 1 si quieres volver al menu");
                            }
                        }
                        break;
                    case 4:
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
                        myLog.addLine("Origen: "+sc.getPort()+" Destino: "+sc.getInetAddress()+" Lista usuarios inactivos");
                        while(!menu){
                            System.out.println("Presiona 1 para volver al menu");
                            int aux=sn.nextInt();
                            if(aux==1){
                                menu=true;
                            }else{
                                System.out.println("Debes presionar 1 si quieres volver al menu");
                            }
                        }
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
