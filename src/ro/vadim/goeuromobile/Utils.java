package ro.vadim.goeuromobile;

import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

public class Utils {

	private static Calendar c = Calendar.getInstance();
	
	
	
	
	public static int[] getDDMMYYYY(){
		int[] dmy = new int[3];
		dmy[0] = c.get(Calendar.DAY_OF_MONTH);
		dmy[1] = c.get(Calendar.MONTH); 
		dmy[2] = c.get(Calendar.YEAR);
		
		return dmy;
	}
	
	
	
	public static String getStringFromDMY(int[] dmy){
		
		String date = String.valueOf(dmy[0])+"/"+
				String.valueOf(dmy[1]+1)+"/"+
				String.valueOf(dmy[2]);
		
		return date;
		
	}
	
	
	
	public static String getTodayString(){
		
		int [] dmy = new int[3];
		
		dmy[0] = c.get(Calendar.DAY_OF_MONTH);
		dmy[1] = c.get(Calendar.MONTH);
		dmy[2] = c.get(Calendar.YEAR);
		
		String date = getStringFromDMY(dmy);
		
		return date;
	}
	
	
	
	
	public static AlertDialog buildSimpleAlertDialog(View parentView){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());
		builder.setTitle("No search, yet");
		builder.setTitle("Search is not yet implemented");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		return builder.create();
	}
	
	
	public static boolean isOnline() {
		
	    ConnectivityManager cm =
	        (ConnectivityManager) MainActivity.getCurrentFragment().
	        							getActivity().
	        								getSystemService(
	        										Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	
}
