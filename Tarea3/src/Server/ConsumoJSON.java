package Server;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;
public class ConsumoJSON {
    public static String objetoString(Consumo p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("NIS",p.getNIS());
        JSONArray list = new JSONArray();
        for(Integer temp: p.getConsumos()){
        	list.add(temp);
        }
        // if(list.size() > 0) {
        	obj.put("asignaturas", list);
        //}
        return obj.toJSONString();
    }
    public static Consumo stringObjeto(String str) throws Exception {
    	Consumo p = new Consumo();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;
        Long NIS = (Long) jsonObject.get("NIS");
        p.setNIS(NIS);
        JSONArray msg = (JSONArray) jsonObject.get("Consumos");
        Iterator<Integer> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	p.getConsumos().add(iterator.next());
        }
        return p;
	}

    
}
