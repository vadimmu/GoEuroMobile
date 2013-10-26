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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SearchFragment extends Fragment{

	EditText textFrom = null;
	EditText textTo = null;
	
	ListView locationList = null;
	Button searchButton = null;
	
	
	private ArrayList<String> suggestionsList = new ArrayList<String>(10);
	
	
	private void initTextFrom(View view){
		
		textFrom = (EditText)view.findViewById(R.id.textFrom);
		
		textFrom.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
								
				if(textFrom.getText().length() >= 2){
					
					Log.i("SearchFragment", "textFrom: getting suggestions");
					UiUpdater.getSearchSuggestions(textFrom.getText().toString());
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});		
		
		
	}
	
	
	private void initTextTo(View view){
		textTo = (EditText)view.findViewById(R.id.textTo);
		textTo.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				if(textTo.getText().length() >= 2){
					
					
				}
				
				return false;
			}
		});
	}
	
	private void initLocationList(View view){
		
		locationList = (ListView)view.findViewById(R.id.listLocations);
		locationList.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				//arg2 is the index;`
				locationList.invalidate();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
				
			}
			
		});
		
		
		
		locationList.setAdapter(new ArrayAdapter(
				getActivity(),
				android.R.layout.simple_list_item_1,
				getSuggestionsList()));
		
		
		((ArrayAdapter)locationList.getAdapter()).notifyDataSetChanged();
		
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
		initTextFrom(view);
		initTextTo(view);
		initLocationList(view);
		initSearchButton(view);
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
	


	public synchronized void updateLocationList(){
		
		final SearchFragment thisFragment = this;
		
		
		
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Log.i("SearchFragment", "updateLocationList()");
				((ArrayAdapter)thisFragment.locationList.getAdapter()).notifyDataSetChanged();
				thisFragment.locationList.invalidate();
				thisFragment.locationList.refreshDrawableState();
				
			}
		});
		
		
	}

	public ArrayList<String> getSuggestionsList() {
		return suggestionsList;
	}


	public synchronized void setSuggestionsList(String[] suggestionsList) {
		this.suggestionsList = new ArrayList<String>(suggestionsList.length);
		
		String suggestions = "";
		for(String suggestion:getSuggestionsList()){
			this.suggestionsList.add(suggestion);
			suggestions+=suggestion+'\n';
		}		
		Log.i("SearchFragment", "setSuggestionsList(): "+suggestions);
	}
	
	
	
	
}
