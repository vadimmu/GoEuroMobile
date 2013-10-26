package ro.vadim.goeuromobile;

import android.os.AsyncTask;

public class UiUpdater {
	
	public static void getSearchSuggestions(final String partialLocationString){
		
		Thread suggestionRetrieverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				String [] suggestions = TripSearcher.getSearcher().getSearchSuggestions(partialLocationString);
				
				if(MainActivity.getCurrentFragment().
						getClass().
							equals(SearchFragment.class)){
					
					SearchFragment currentFragment = (SearchFragment) MainActivity.getCurrentFragment();
					currentFragment.setSuggestionsList(suggestions);
					currentFragment.updateLocationList();
				}
			}
		});
		
		suggestionRetrieverThread.start();
		
	}
	
	
	
	
}
