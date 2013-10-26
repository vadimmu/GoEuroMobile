package ro.vadim.goeuromobile;

import android.os.Looper;

public class Utils {
	
	
	public static boolean thisIsTheMainThread(){
		if(Looper.myLooper() == Looper.getMainLooper())
			return true;
		
		return false;
	}
	

}
