package ro.vadim.goeuromobile;
import java.util.Map;


public interface MessageHandler {

	public void manageMessage(Map<String, Object> jsonData);
	
}
