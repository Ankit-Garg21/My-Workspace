package com.first.ankit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity {

	TextView tvinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		
		tvinfo = (TextView) findViewById(R.id.tvSQLinfo);
		
		HotOrNot info = new HotOrNot(this);
		info.open();
		String data = info.getdata();
		info.close();
		tvinfo.setText(data);
	}
}