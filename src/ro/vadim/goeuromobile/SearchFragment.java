package ro.vadim.goeuromobile;

import java.io.IOException;
import java.nio.channels.AlreadyConnectedException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import ro.vadim.goeuromobile.data.AutocompleteAdapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment{
	
	
	
	private AutoCompleteTextView textDeparture = null;
	private AutoCompleteTextView textArrival = null;
	private Button buttonSearch = null;
	
	private Button buttonDepartureDate = null;
	private TextView textDepartureDate = null;
	
	private Button buttonArrivalDate = null;
	private TextView textArrivalDate = null;
	
	
	private void setupTimeButton(final TextView targetTextView){
		
		int[] dmy = Utils.getDDMMYYYY();
		
		DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				
				int [] dmy = new int[3];
				dmy[0] = dayOfMonth;
				dmy[1] = monthOfYear;
				dmy[2] = year;
				
				targetTextView.setText(Utils.getStringFromDMY(dmy));
				
			}
		};
		
		new DatePickerDialog(getActivity(), date, dmy[2], dmy[1], dmy[0]).show();
		
	}
	
	
	private void initButtonArrivalDate(View view){
		buttonArrivalDate = (Button) view.findViewById(R.id.buttonArrivalDate);
		buttonArrivalDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setupTimeButton(textArrivalDate);
				
			}
		});

	}
	
	private void initButtonDepartureDate(View view){
		buttonDepartureDate = (Button) view.findViewById(R.id.buttonDepartureDate);
		buttonDepartureDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setupTimeButton(textDepartureDate);
				
			}
		});

	}
	
	
	
	
	private void initTextDepartureDate(View view){		
		textDepartureDate = (TextView) view.findViewById(R.id.textDepartureDate);
		textDepartureDate.setText(Utils.getTodayString());		
	}
	private void initTextArrivalDate(View view){
		textArrivalDate = (TextView) view.findViewById(R.id.textArrivalDate);
		textArrivalDate.setText(Utils.getTodayString());
	}
	
	
	private void initAutocompletTextView(View view, AutoCompleteTextView textView){
		
		AutocompleteAdapter adapter = new AutocompleteAdapter(view.getContext());		
		textView.setAdapter(adapter);		
		textView.setThreshold(2);
		
	}
	
	private void initTextDeparture(View view){
		
		textDeparture = (AutoCompleteTextView) view.findViewById(R.id.textDeparture);		
		initAutocompletTextView(view, textDeparture);
	} 
	
	private void initTextArrival(View view){
		
		textArrival = (AutoCompleteTextView) view.findViewById(R.id.textArrival);		
		initAutocompletTextView(view, textArrival);
	}
	
	private void initButtonSearch(View view){
		
		buttonSearch = (Button)view.findViewById(R.id.buttonSearch);
		
		final View parentView = view;
		buttonSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog dialog = Utils.buildSimpleAlertDialog(parentView);
				dialog.show();
			}
		});
	}
	
	
	
	
	
	private void initComponents(View view){
		
		initButtonSearch(view);
				
		initTextDeparture(view);
		initTextArrival(view);
		
		initTextDepartureDate(view);
		initTextArrivalDate(view);
		
		initButtonDepartureDate(view);
		initButtonArrivalDate(view);
		
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
	
}
