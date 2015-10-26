package com.first.ankit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener{
	
	// Variables for components in get.xml
	EditText send;		// For edit  text
	Button SA, SAFR;		// For two buttons
	TextView gotAnswer;			// For text view
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		send = (EditText) findViewById(R.id.etSend);
		SA = (Button) findViewById(R.id.bSA);
		SAFR = (Button) findViewById(R.id.bSAFR);
		gotAnswer = (TextView) findViewById(R.id.tvGot);
		SA.setOnClickListener(this);
		SAFR.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSA:
			
			// Convert data in the edit text to string and store in bread
			String bread = send.getText().toString();
			
			//Initialize a new bundle and insert the data into the bundle with a unique reference key 
			Bundle basket = new Bundle();
			basket.putString("key", bread);
			
			//Initialize an intent to open next class
			Intent a = new Intent(Data.this, OpenedClass.class);
			
			//Put the bundle into the intent to send to next activity
			a.putExtras(basket);
			startActivity(a);
			break;
		case R.id.bSAFR:
			Intent i = new Intent(Data.this, OpenedClass.class);
			startActivityForResult(i,0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			
			// Get the received data into a bundle 
			Bundle basket = data.getExtras();
			
			//Get required data from the bundle using the reference key
			String s = basket.getString("answer");
			gotAnswer.setText(s); 
		}
	}
	
}