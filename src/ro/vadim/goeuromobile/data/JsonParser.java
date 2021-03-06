package ro.vadim.goeuromobile.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
	
	private static JsonParser parser = null;
	
	private ObjectMapper mapper = null;
	
	
	
	
	
	private JsonParser(){		
		mapper = new ObjectMapper();		
	}	
	
	public static JsonParser getParser(){
		
		if(parser == null)
			parser = new JsonParser();
		
		return parser;		
	}
	
	
	
	
	
	public Map<String, Object> extractObject(String message){
		Map<String, Object> jsonObject = null;
		
        if(mapper == null)
        	mapper = new ObjectMapper();
        
        try{    
            jsonObject = mapper.readValue(message, HashMap.class);
        }
        
        catch (JsonParseException e) {
        	Log.println(Log.WARN, "JSON ERROR (JsonParseException) (original message) : ", message);
            Log.println(Log.WARN, "JSON ERROR (JsonParseException)", e.toString());                 
        }
        
        catch (JsonMappingException e) {
            Log.println(Log.WARN, "JSON ERROR (JsonMappingException) (original message) : ", message);
            Log.println(Log.WARN, "JSON ERROR (JsonMappingException)", e.toString());                
        }
                        
        finally{        	
        	return jsonObject;
        }
	}
	
	public ArrayList<Map> extractArrayOfObjects(String message){
		ArrayList<Map> jsonObject = null;
		
        if(mapper == null)
        	mapper = new ObjectMapper();
        
        try{    
            jsonObject = mapper.readValue(message, ArrayList.class);
        }
        
        catch (JsonParseException e) {
        	Log.println(Log.WARN, "JSON ERROR (JsonParseException) (original message) : ", message);
            Log.println(Log.WARN, "JSON ERROR (JsonParseException)", e.toString());                 
        }
        
        catch (JsonMappingException e) {
            Log.println(Log.WARN, "JSON ERROR (JsonMappingException) (original message) : ", message);
            Log.println(Log.WARN, "JSON ERROR (JsonMappingException)", e.toString());                
        }
                        
        finally{        	
        	return jsonObject;
        }		
	}
	
	public ArrayList<Map>extractArrayOfObjects(Map<String, Object> parentObject, String tag){
		
		ArrayList<Map> childObjectList = (ArrayList<Map>)parentObject.get(tag);		
		return childObjectList;		
	}
		
	public Map<String, Object> extractObjectFromObject(Map<String, Object> parentObject, String tag){		
		Map<String, Object> childObject = (Map<String, Object>) parentObject.get(tag);		
		return childObject;
	}
	
	
	
	
	
	
	
	

}
