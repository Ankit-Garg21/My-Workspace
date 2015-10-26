package com.first.ankit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener{

	// WebView is used for viewing web content
	WebView ourBrowser;
	EditText url;
	Button go, back, forward, refresh, history;
	
	// Input Method Manager to manage input settings
	InputMethodManager iMM;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		ourBrowser = (WebView) findViewById(R.id.wvBrowser);
		
		//Enable Javascript
		ourBrowser.getSettings().setJavaScriptEnabled(true);
		
		//Set dimensions zoomed out
		ourBrowser.getSettings().setLoadWithOverviewMode(true);
		ourBrowser.getSettings().setUseWideViewPort(true);
		
		//Set our view client for web view
		ourBrowser.setWebViewClient(new ourViewClient());
		
		// Setting up of input method manager
		iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		try{
		ourBrowser.loadUrl("http://www.gmail.com");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		go = (Button) findViewById(R.id.bGo);
		back = (Button) findViewById(R.id.bBack);
		forward = (Button) findViewById(R.id.bForward);
		refresh = (Button) findViewById(R.id.bRefresh);
		history = (Button) findViewById(R.id.bHistory);
		url = (EditText) findViewById(R.id.etURL);
		
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		history.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bGo :
			String website = url.getText().toString();
			
			//Hide android keyboard after input
			iMM.hideSoftInputFromWindow(url.getWindowToken(), 0);
			// Load website...
			ourBrowser.loadUrl(website);
			break;
		case R.id.bBack :
			if(ourBrowser.canGoBack())
				//Go back a page
			ourBrowser.goBack();
			break;
		case R.id.bForward :
			if(ourBrowser.canGoForward())
				//Go forward a page
				ourBrowser.goForward();
			break;
		case R.id.bRefresh :
			//Reload the current page
			ourBrowser.reload();
			break;
		case R.id.bHistory :
			//Clear history
			ourBrowser.clearHistory();
			break;
		}
	}
}