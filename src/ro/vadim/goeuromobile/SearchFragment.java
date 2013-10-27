package ro.vadim.goeuromobile;

import java.io.IOException;
import java.nio.channels.AlreadyConnectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.database.DataSetObserver;
import android.inputmethodservice.Keyboard.Key;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SearchFragment extends Fragment{

	
	AutoCompleteTextView textDeparture = null;
	Button searchButton = null;
	
	
	private String[] suggestionsList = {};
	
	
	
	private void initTextDeparture(View view){
		
		textDeparture = (AutoCompleteTextView) view.findViewById(R.id.textDeparture);		
		
		AutocompleteAdapter adapter = new AutocompleteAdapter(view.getContext());
		
		textDeparture.setAdapter(adapter);
		
		textDeparture.setThreshold(2);
	} 
	
	private void initSearchButton(View view){
		
		searchButton = (Button)view.findViewById(R.id.buttonSearch);
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});		
	}
	
	private void initComponents(View view){
		
		initSearchButton(view);
		initTextDeparture(view);
	}
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.search_fragment_layout, container, false);
		initComponents(view);		
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
		
	
	public synchronized void updateTextDeparture(){
		final SearchFragment thisFragment = this;
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				ArrayAdapter<String> adapter = (ArrayAdapter)thisFragment.
						textDeparture.getAdapter();
				
				thisFragment.textDeparture.setAdapter(adapter);
				
			}
		});
	}
	

	public String[] getSuggestionsList() {
		return suggestionsList;
	}


	public synchronized void setSuggestionsList(String[] suggestionsList) {
		this.suggestionsList = suggestionsList;
		
		String suggestions = "";
		for(String suggestion:getSuggestionsList()){			
			suggestions+=suggestion+'\n';
		}
		Log.i("SearchFragment", "setSuggestionsList(): "+suggestions);
	}
	
	
	
	
}
