package Server;

public class Usuario {
    int NIS;
    int estado;//si es 0 esta inactivo en cambio si es 1 esta activo
    public Usuario(int NIS,int estado){
        this.NIS=NIS;
        this.estado=estado;
    }
    public int getestado(){
        return this.estado;
    }
    public void iniciado(){
        this.estado=1;
    }
    public void apagado(){
        this.estado=0;
    }
    public int getNIS(){
        return this.NIS;
    }
}
