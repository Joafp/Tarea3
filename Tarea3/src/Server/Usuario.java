package Server;

public class Usuario {
    int NIS;
    int estado;//si es 0 esta inactivo en cambio si es 1 esta activo
    Consumo consumo;
    public Usuario(int NIS,int estado,Consumo consumo){
        this.NIS=NIS;
        this.estado=estado;
        this.consumo=consumo;
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
    public Consumo getConsumo(){
        return consumo;
    }
    public void setConsumo(Consumo consumo){
        this.consumo=consumo;
    }
}
