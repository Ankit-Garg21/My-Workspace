package com.first.ankit;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Test extends Activity implements View.OnClickListener{

	Button testButton;
	ToggleButton Tbutton;
	EditText input;
	TextView display;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		method1();		
		Tbutton.setOnClickListener(this);
		
		testButton.setOnClickListener(this);
	}
	private void method1() {
		// TODO Auto-generated method stub
		testButton = (Button) findViewById(R.id.testButton);
		Tbutton = (ToggleButton) findViewById(R.id.Tbutton);
		input = (EditText) findViewById(R.id.etext);
		display = (TextView) findViewById(R.id.TextView1);

	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.testButton:
			String check = input.getText().toString();
			display.setText(check);
				if(check.contentEquals("left")){
					display.setGravity(Gravity.LEFT);
				} else if(check.contentEquals("center")){
					display.setGravity(Gravity.CENTER);
				} else if(check.contentEquals("right")){
					display.setGravity(Gravity.RIGHT);
				} else if(check.contentEquals("red")){
					display.setTextColor(Color.RED);
				} else if(check.contentEquals("Ankit")){
					Random r = new Random();	//Generate a random value everytime.
					display.setTextSize(r.nextInt(100));	//Random size and Random color.
					display.setTextColor(Color.rgb(r.nextInt(265), r.nextInt(265), r.nextInt(265)));
				
					switch(r.nextInt(3))
					{
					case 0:
						display.setGravity(Gravity.LEFT);
						break;
					case 1:
						display.setGravity(Gravity.CENTER);
						break;
					case 2:
						display.setGravity(Gravity.RIGHT);
						break;
					}
				}
				else{
					display.setGravity(Gravity.CENTER);
				}
				
				break;
				
		case R.id.Tbutton:
			if(Tbutton.isChecked()){
				//Set to password type.
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}	else{
				//Set to text type.
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
			}
	}

}
