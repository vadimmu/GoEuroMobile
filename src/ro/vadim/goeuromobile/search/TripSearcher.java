package ro.vadim.goeuromobile.search;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import ro.vadim.goeuromobile.data.HttpRequester;
import ro.vadim.goeuromobile.data.JsonParser;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

public class TripSearcher{

	private static TripSearcher searcher = null;
	
	private TripSearcher(){
				
	}
	
	public static TripSearcher getSearcher(){
		
		if(searcher == null)
			searcher = new TripSearcher();
		
		return searcher;		
	}
	
	public ArrayList<Map> getLocationData(String partialLocationString){
		
		partialLocationString = partialLocationString.split(",")[0].trim().toLowerCase();
		partialLocationString.replaceAll(" ", "%");
		
		
		String message = HttpRequester.sendGet("http://pre.dev.goeuro.de:12345/api/v1/suggest/position/en/name/"+partialLocationString);
		
		ArrayList<Map> locationList = null;
		
		try {
			
			JsonParser parser = JsonParser.getParser();			
			Map<String, Object> messageObject = parser.extractObject(message);
			locationList = parser.extractArrayOfObjects(messageObject, "results");
						
			Log.i("GOT RESULTS !", locationList.toString());
			
		} 
		
		catch (NullPointerException e){
			Log.e("testing parsing capabilities", e.toString());
		}
		
		finally{
			return locationList;
		}
	}
	
	public String[] getSearchSuggestions(String partialLocationString){
		
		String[] suggestions = {};
		
		ArrayList<Map> listOfLocationObjects = getLocationData(partialLocationString);
		
		if(listOfLocationObjects == null)
			return suggestions;
				
		suggestions = new String[listOfLocationObjects.size()];
		
		int i=0;
		for(Map<String, Object> locationObject : listOfLocationObjects){
			suggestions[i++] = (String)locationObject.get("name");
		}
		
		return suggestions;
	}
	
	
	
	
	
	
}
