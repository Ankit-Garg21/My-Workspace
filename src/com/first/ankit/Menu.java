package com.first.ankit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	// Show all the classes in a menu format
	// Menu shown as list in a array of strings.
	String classes[] = {"FirstActivity", "Test", "Email"
			, "Camera", "Data", "GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs", "SimpleBrowser",
			"Flipper", "SharedPrefs", "InternalData", "SQLiteExample", "Accelerate", "TextVoice", "GLExample", "GLCube" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Show application in full screen
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// Show array of string as list.
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		//Get name of the selected class according to the position in the list
		String className = classes[position];
		try{
			//Get class name from list.
		Class ourClass = Class.forName("com.first.ankit."+className);
		//Start the intent.
		Intent ourIntent = new Intent(Menu.this, ourClass);
		startActivity(ourIntent);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	@Override
	
	// Show Menu items
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}

	//For secondary menu options
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		
		case R.id.aboutUs:
			Intent i = new Intent("com.first.ankit.ABOUT");
			startActivity(i);
			break;
		
		case R.id.preferences:
			Intent p = new Intent("com.first.ankit.PREFS");
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}
}