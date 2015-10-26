package com.first.ankit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {

	// Variables for components in email.xml
	EditText personsEmail, intro, personsName, stupidThings, hatefulAction, outro;
	String emailAdd, beginning, name, stupidAction, hatefulAct, out;
	Button sendEmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initialize();
		sendEmail.setOnClickListener(this);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		personsEmail = (EditText) findViewById(R.id.etEmails);
		intro = (EditText) findViewById(R.id.etIntro);
		personsName = (EditText) findViewById(R.id.etName);
		stupidThings = (EditText) findViewById(R.id.etThings);
		hatefulAction = (EditText) findViewById(R.id.etAction);
		outro = (EditText) findViewById(R.id.etOutro);
		sendEmail = (Button) findViewById(R.id.bSentEmail);
	}
	
	public void onClick(View v)
	{
		convert();
		// Email addresses to which we are sending should be an array of strings.
		String emailAddress[] = {emailAdd};
		String message = "Hello "+ name+
		" I just wanted to say "+beginning+
		". Not only that but I hate u when you "+stupidAction+
		" and I want to "+hatefulAct+
		" Well that's all and "+out+
		" Oh And"+'\n'+" PS. _|_";
		
	//	sendEmail.setText(message);
		//Start phone's default email application and send email.
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		
		//Add extra attributes to message such as subject, body...
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Cowboy Bebop");
		emailIntent.setType("plain/text"); 
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	}

	private void convert() {
		// TODO Auto-generated method stub
		emailAdd = personsEmail.getText().toString();
		beginning = intro.getText().toString();
		name = personsName.getText().toString();
		stupidAction = stupidThings.getText().toString();
		hatefulAct = hatefulAction.getText().toString();
		out = outro.getText().toString();
		}

	// If activity is paused or suspended...and not gonna be used later, finish it.
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}


