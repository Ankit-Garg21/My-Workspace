package com.first.ankit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {

	TabHost tH;
	TabSpec tS;
	Button newTab, bStart, bStop, bReset;
	TextView showResults;
	Long lStart, lStop;
	int millis, second, minute;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		newTab = (Button) findViewById(R.id.bAddTab);
		bStart = (Button) findViewById(R.id.bStartWatch);
		bStop = (Button) findViewById(R.id.bStopWatch);
		bReset = (Button) findViewById(R.id.bReset);
		lStart = (long) 0;
		showResults = (TextView) findViewById(R.id.tvShowResults);
		
		newTab.setOnClickListener(this);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		bReset.setOnClickListener(this);
		// Initialize a TabHost
		tH = (TabHost) findViewById(R.id.tabhost);
		tH.setup();
		
		// Specify content to be added in TabHost
		tS = tH.newTabSpec("Tag 1");
		tS.setContent(R.id.tab1);
		
		//Assign name to the new tab
		tS.setIndicator("StopWatch");
		
		// Add customized TabSpec in TabHost
		tH.addTab(tS);
		
		//Same for other tabs
		tS = tH.newTabSpec("Tag 2");
		tS.setContent(R.id.tab2);
		tS.setIndicator("Tab 2");
		tH.addTab(tS);
		
		tS = tH.newTabSpec("Tag 3");
		tS.setContent(R.id.tab3);
		tS.setIndicator("Add a tab");
		tH.addTab(tS);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.bAddTab :
			
			// Define our own TabSpec
			TabSpec ourSpec = tH.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);
					text.setText("You've created a new tab");
					return (text);
				}
			});
			
			ourSpec.setIndicator("New");
			tH.addTab(ourSpec);
			break;
		case R.id.bStartWatch :
			
			// Get System's current time
			lStart = System.currentTimeMillis();
			break;
		case R.id.bStopWatch :
			lStop =System.currentTimeMillis();
			if(lStart!=0){
				long result = lStop - lStart;
				millis = (int) result;
				second = (int) result/1000;
				minute = second/60;
				millis = millis % 100;
				second = second % 60;
				showResults.setText(String.format("%d:%02d:%02d", minute,second,millis));
			}
			break;
		case R.id.bReset :
			lStart = lStop = (long) 0;
			showResults.setText("0");
			break;
		}
	}
}