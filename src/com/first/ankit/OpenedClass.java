package com.first.ankit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Button;

public class OpenedClass extends Activity implements View.OnClickListener, OnCheckedChangeListener{
	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String gotBread, setData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		
		// Get all the shared preferences values 
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		// Select the required data from the received data
		String et = getData.getString("name", "A");
		String values = getData.getString("list", "1");
		if(values.contentEquals("1"))
			question.setText(et);
		
		/*Bundle gotBasket = getIntent().getExtras();
		gotBread = gotBasket.getString("key");
		question.setText(gotBread);*/
	}
	private void initialize() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturn);
		returnData.setOnClickListener(this);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		selectionList.setOnCheckedChangeListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", setData);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();
	}
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch(arg1){
		case R.id.rbYes:
			setData = "Right";
			break;
		case R.id.rbNo:
			setData = "Wrong";
			break;
		case R.id.rbMaybe:
			setData = "Probably right";
			break;
		}
		test.setText(setData);
	}
}