package com.first.ankit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Save and load data....
public class SharedPrefs extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResults;
	Button save, load;
	String stringData, dataReturned;
	
	// Shared Preference is used to save data privately
	SharedPreferences shared;
	//Define editor to edit data to the file
	SharedPreferences.Editor editor;
	// file name defined
	public static String fileName = "MySharedData";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setUpVariables();
		
		/* Load file.. Think of it as a folder which contains our shared preferences data like 
		 * boolean, string or any variable
		 */
		shared = getSharedPreferences(fileName, 0);
	}

	private void setUpVariables() {
		// TODO Auto-generated method stub
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
		save = (Button) findViewById(R.id.bSave);
		load = (Button) findViewById(R.id.bLoad);
		
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSave :
			stringData = sharedData.getText().toString();
			// Edit file.. Put data into the file
			editor = shared.edit();
			editor.putString("sharedString", stringData);
			
			// Update and save file
			editor.commit();
			break;
		case R.id.bLoad :
			// Load file after commit...
			shared = getSharedPreferences(fileName, 0);
			dataReturned = shared.getString("sharedString", "Unable to load..");
			dataResults.setText(dataReturned);
			break;
		}
	}
}