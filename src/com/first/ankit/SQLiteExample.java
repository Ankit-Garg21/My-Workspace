package com.first.ankit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlGetInfo, sqlEditEntry, sqlDeleteEntry;
	EditText sqlName, sqlHotness, sqlRowId;
	String name, hotness;
	boolean didItWork;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLView);
		sqlName = (EditText) findViewById(R.id.etGirlName);
		sqlHotness = (EditText) findViewById(R.id.etGirlHotness);
		
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlEditEntry = (Button) findViewById(R.id.bEditEntry);
		sqlDeleteEntry = (Button) findViewById(R.id.bDeleteEntry);
		sqlRowId = (EditText) findViewById(R.id.etRowId);
		
		sqlGetInfo.setOnClickListener(this);
		sqlEditEntry.setOnClickListener(this);
		sqlDeleteEntry.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSQLUpdate :
			didItWork = true;
			try{
			name = sqlName.getText().toString();
			hotness = sqlHotness.getText().toString();
			
			HotOrNot entry = new HotOrNot(SQLiteExample.this);
			entry.open();
			entry.createEntry(name, hotness);
			entry.close();
			} catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Yes");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bSQLView :
			Intent i = new Intent("com.first.ankit.SQLView");
			startActivity(i);
			break;
		case R.id.bGetInfo :
			String row = sqlRowId.getText().toString();
			long l = Long.parseLong(row);
			HotOrNot h = new HotOrNot(this);
			h.open();
			String returnedName = h.getName(l);
			String returnedHotness = h.getHotness(l);
			h.close();
			
			sqlName.setText(returnedName);
			sqlHotness.setText(returnedHotness);
			break;
		case R.id.bEditEntry :
			try{
			String newName = sqlName.getText().toString();
			String newHotness = sqlHotness.getText().toString();
			String sRow = sqlRowId.getText().toString();
			long lRow = Long.parseLong(sRow);
			
			HotOrNot edit = new HotOrNot(this);
			edit.open();
			edit.UpdateEntry(lRow, newName, newHotness);
			edit.close();
			didItWork = true;
			} catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Yes");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bDeleteEntry :
			try{
			String sdRow = sqlRowId.getText().toString();
			long ldRow = Long.parseLong(sdRow);
			HotOrNot del = new HotOrNot(this);
			del.open();
			del.deleteEntry(ldRow);
			del.close();
			didItWork = true;
			} catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Yes");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		}
	}
}