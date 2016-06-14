package testJson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WriteJson {
	public static void main(String[] args) {
		JsonObject object=new JsonObject();
		object.addProperty("name", "it");
		JsonArray array=new JsonArray();
		JsonObject lan1=new JsonObject();
		lan1.addProperty("id", 1);
		lan1.addProperty("cat", "java");
		lan1.addProperty("ide", "eclipse");
		array.add(lan1);
		object.add("language", array);
		System.out.println(object);
		
		
	}
}
