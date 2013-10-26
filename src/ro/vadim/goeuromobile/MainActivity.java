package ro.vadim.goeuromobile;

import java.io.IOException;
import java.util.Map;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	
	private static Fragment currentFragment = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loadSearchFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	
	private void loadSearchFragment(){
		
		SearchFragment newFragment = new SearchFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		
		setCurrentFragment(newFragment);
	}
	
	
	public static synchronized Fragment getCurrentFragment() {
		return currentFragment;
	}

	public static synchronized void setCurrentFragment(Fragment currentFragment) {
		MainActivity.currentFragment = currentFragment;
	}
}
