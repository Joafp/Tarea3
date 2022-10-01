package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
public class ServerHilo extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private int NIS;
    private List<Usuario> usuarios;
    private Socket sc;
    private ServerSocket servidor;
    public ServerHilo(DataInputStream in,DataOutputStream out,List<Usuario> usuarios,ServerSocket servidor){
        this.servidor=servidor;
        this.in=in;
        this.out=out;
        this.usuarios=usuarios;
    }
    @Override
    public void run(){
        Scanner sn=new Scanner(System.in);
        String mensaje=new String();
        int opcion;
        File f=new File("consumos.txt");
        while(true){
            try {
                opcion=in.readInt();
                switch(opcion){
                    case 1:
                        int consumo=in.readInt();
                        escribirconsumo(f,consumo);
                        out.writeUTF("Consumo guardado correctamente");
                        System.out.println("Se escribio el consumo en el cliente"+NIS);
                        break;
                    case 2:
                        break;
                    case 3:
                       NIS=in.readInt();
                       for(int i=0;i<usuarios.size();i++){
                          if(usuarios.get(i).NIS==NIS){
                               usuarios.get(i).apagado();
                            }
                        }
                        out.writeUTF("Se termino la conexion exitosamente");
                       System.out.println("Se termino correctamente la conexion con el cliente "+NIS);
                        break;
                    case 5:
                        out.writeInt(usuarios.size());
                        for(int i=0;i<usuarios.size();i++){
                            out.writeInt(usuarios.get(i).getNIS());
                            out.writeInt(usuarios.get(i).getestado());
                        }
                        break;
                    case 6:
                        out.writeInt(usuarios.size());
                        for(int i=0;i<usuarios.size();i++){
                            out.writeInt(usuarios.get(i).getNIS());
                            out.writeInt(usuarios.get(i).getestado());
                        }
                     break;
                    default:
                        out.writeUTF("Solo numeros del 1 al 6");
                }
                
            } catch (IOException e) {
                Logger.getLogger(ServerHilo.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
    public void escribirconsumo(File f,int consumo) throws IOException{
        FileWriter fw=new FileWriter(f,true);
        fw.write(NIS+":"+consumo+"\r\n");
        fw.close();
    }
}