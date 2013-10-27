package ro.vadim.goeuromobile.data;

import java.util.ArrayList;

import ro.vadim.goeuromobile.R;
import ro.vadim.goeuromobile.Utils;
import ro.vadim.goeuromobile.R.layout;
import ro.vadim.goeuromobile.search.TripSearcher;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public class AutocompleteAdapter extends ArrayAdapter<String> implements Filterable {
    private ArrayList<String> resultList;
    private static final int DEFAULT_RESOURCE_ID = R.layout.list_item;
    
    private String defaultString = null;
    
    public AutocompleteAdapter(Context context, String defaultString) {
        super(context, DEFAULT_RESOURCE_ID);
        this.defaultString = defaultString;
    }
    
    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return resultList.get(index);
    }
        
    private ArrayList<String> autocomplete(String autocompleteString){
    	
    	if(!Utils.isOnline())
    		return new ArrayList<String>(0);
    	
    	
    	if((autocompleteString.trim().equals(this.defaultString.trim())) ||
    			((autocompleteString.length() < 2))){
    				
    		return new ArrayList<String>(0);
    	}	
    	    	
    	String[] suggestions = TripSearcher.getSearcher().
    							getSearchSuggestions(autocompleteString);
    	
    	ArrayList<String> results = new ArrayList<String>(suggestions.length);
    	for(String suggestion:suggestions){
    		results.add(suggestion);
    	}
    	
    	return results;
	    	
	
    }
        
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.
                    resultList = autocomplete(constraint.toString());
                    
                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }
}