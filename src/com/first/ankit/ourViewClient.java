package com.first.ankit;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ourViewClient extends WebViewClient {

	@Override
	
	// Override Url loading so links don't open in defaault mobile browser... but in the current application
	public boolean shouldOverrideUrlLoading(WebView v, String url){
		v.loadUrl(url);
		return true;
	}
}