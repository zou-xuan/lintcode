package testJson;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ReadJson {
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser parser=new JsonParser();
		JsonObject object=(JsonObject)parser.parse(new FileReader("test.json"));
		System.out.println("name="+object.get("name").getAsString());
		JsonArray array=object.get("language").getAsJsonArray();
		for(int i=0;i<array.size();i++){
			System.out.println("----------------");
			JsonObject subobject=array.get(i).getAsJsonObject();
			System.out.println("id"+subobject.get("id").getAsInt());
			System.out.println("cat"+subobject.get("cat").getAsString());
			System.out.println("ide"+subobject.get("ide").getAsString());
		}
	}

}
