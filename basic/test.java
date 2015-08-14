import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

public class test {

	private static final String ARMY_PLATE_STRING = "VKHBSLJMGC"; // 所有军车牌的牌头字母

	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader(new File("G:/json.txt"));
		int next;
		String json = "";
		next = reader.read();
		while (next != -1) {
			json += (char) next;
			next = reader.read();
		}
		System.out.println(json);
		JSONObject jsonObject = JSONObject.fromObject(json);
		
		
		Iterator iter = jsonObject.keySet().iterator();
		while (iter.hasNext()) {
		    String name = (String) iter.next();
		    String value = jsonObject.getString(name);
		    value = value.replace("\\s", "");
		    value = value.replace("\"", "");
		    value = value.replace("[", "");
		    value = value.replace("]", "");
		    System.out.println(name+"---"+value);
		}
		// Map<String, Object> map = jsonObject;
	}
}
