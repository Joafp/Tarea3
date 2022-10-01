package Server;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.net.DatagramPacket;
import java.net.Socket;
import java.sql.Date;
import java.util.logging.Logger;
public class ClienteHilo extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket sc;
    private int NIS;
    public ClienteHilo(DataInputStream in,DataOutputStream out,Socket sc,int NIS){
        this.in=in;
        this.out=out;
        this.sc=sc;
        this.NIS=NIS;
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
            System.out.println("3. Listar NIS activos");
            System.out.println("4. Listar NIS inactivos");
            System.out.println("5. Listar Consumo");
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
                        out.writeInt(this.NIS);
                        System.out.println("Realizando desconexion");
                        mensaje=in.readUTF();
                        System.out.println(mensaje);
                        myLog.addLine("Origen: "+sc.getLocalPort()+" Destino: "+sc.getPort()+" Realizar desconexion");
                        salir=true;
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
                        myLog.addLine("Origen: "+sc.getLocalPort()+" Destino: "+sc.getPort()+" Lista usuarios activos");
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
                        myLog.addLine("Origen: "+sc.getLocalPort()+" Destino: "+sc.getPort()+" Lista usuarios inactivos");
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
                    case 5:
                        System.out.println("Lista de consumos por el Clinte con NIS: "+NIS);
                        out.writeInt(NIS);
                        int tam=in.readInt();
                        for(int i=0;i<tam;i++){
                            System.out.println("- "+in.readInt());
                        }
                        myLog.addLine("Origen: "+sc.getLocalPort()+" Destino: "+sc.getPort()+" Lista usuarios inactivos");
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
