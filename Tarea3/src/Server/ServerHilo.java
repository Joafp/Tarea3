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
        boolean salir=false;
        try{
            boolean existe=false;
            NIS=in.readInt();
            System.out.println("Estableciendo conexion entre el servidor y el cliente con NIS: "+NIS); 
            for(int i=0;i<usuarios.size();i++){
                if(usuarios.get(i).NIS==NIS){
                    if (usuarios.get(i).estado==1){
                        existe=true;
                    }
                    break;
                }
            }
            if (existe==true){
                out.writeUTF("Este NIS ya cuenta con una conexion activa");
                System.out.println("Conexion denegada al cliente : "+NIS);
                out.writeInt(0);
                salir=true;
            }
            else{
                Consumo consumo=new Consumo(NIS);
                Usuario us=new Usuario(NIS, 1,consumo);//Al conectar se pone el estado en 1
                usuarios.add(us);// se añade un nuevo usuario activo al servidor
                out.writeUTF("Conexion exitosa");
                out.writeInt(1);
                System.out.println("Creada la conexion con el NIS: : "+NIS);
            }
        } catch (IOException e) {
                Logger.getLogger(ServerHilo.class.getName()).log(Level.SEVERE,null,e);
            }
        while(!salir){
            try {
                opcion=in.readInt();
                switch(opcion){
                    case 1:
                        int consumo=in.readInt();
                        for(int i=0;i<usuarios.size();i++){
                            if(usuarios.get(i).NIS==NIS){
                                usuarios.get(i).getConsumo().getConsumos().add(consumo);
                                break;
                            }
                        }
                        escribirconsumo(f,consumo);
                        out.writeUTF("Consumo guardado correctamente");
                        System.out.println("Se escribio el consumo en el cliente con NIS: "+NIS);
                        break;
                    case 2:
                       NIS=in.readInt();
                       for(int i=0;i<usuarios.size();i++){
                            if(usuarios.get(i).getNIS()==NIS){
                               usuarios.get(i).apagado();
                            }
                        }
                        out.writeUTF("Se termino la conexion exitosamente");
                        System.out.println("Se termino correctamente la conexion con el cliente con NIS: "+NIS);
                        salir=true;
                        break;
                    case 3:
                        out.writeInt(usuarios.size());
                        for(int i=0;i<usuarios.size();i++){
                            out.writeInt(usuarios.get(i).getNIS());
                            out.writeInt(usuarios.get(i).getestado());
                        }
                        break;
                    case 4:
                        out.writeInt(usuarios.size());
                        for(int i=0;i<usuarios.size();i++){
                            out.writeInt(usuarios.get(i).getNIS());
                            out.writeInt(usuarios.get(i).getestado());
                        }
                     break;
                    case 5:
                        int auxNIS=in.readInt();
                        for(int i=0;i<usuarios.size();i++){
                            if(usuarios.get(i).getNIS()==auxNIS){
                                out.writeInt(usuarios.get(i).getConsumo().getConsumos().size());
                                for(int j=0;j<usuarios.get(i).getConsumo().getConsumos().size();j++){
                                    out.writeInt(usuarios.get(i).getConsumo().getConsumos().get(j));
                                }
                            }
                        }
                        break;
                    default:
                        out.writeUTF("Solo numeros del 1 al 5");
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
