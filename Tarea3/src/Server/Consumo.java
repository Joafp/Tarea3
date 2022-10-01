package Server;
import java.util.ArrayList;
import java.util.List;
public class Consumo {
    long NIS;
    List <Integer> consumos;
    public Consumo(){
        consumos=new ArrayList<Integer>();
    }
    public Consumo(int NIS){
        this.NIS=NIS;
        consumos=new ArrayList<Integer>();
    }
    public long getNIS(){
        return NIS;
    }
    public List<Integer> getConsumos(){
        return consumos;
    }
    public void setNIS(long NIS){
        this.NIS=NIS;
    }
    public void setConsumos(List<Integer> Consumos){
        this.consumos=Consumos;
    }
}
