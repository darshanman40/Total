package com.manchekar.darshan.total;

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

	Button go,back,forward,refresh,clearHistory;
	WebView ourBrow;
	EditText url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		go = (Button)findViewById(R.id.bGo);
		back = (Button)findViewById(R.id.bBack);
		forward = (Button)findViewById(R.id.bForward);
		refresh = (Button)findViewById(R.id.bRefresh);
		clearHistory = (Button)findViewById(R.id.bHistory);
		
		ourBrow=(WebView) findViewById(R.id.wvBrowser);
		
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		
		ourBrow.setWebViewClient(new ourViewClient());
		url = (EditText)findViewById(R.id.etURL);
		
		go.setOnClickListener(this);
		
		try{
		ourBrow.loadUrl("http://www.google.com");
		}catch(Exception e){
			e.printStackTrace();
			}
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		case R.id.bGo:
			String theWebSite = url.getText().toString();
			ourBrow.loadUrl("http://"+theWebSite);
			InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
		case R.id.bBack:
			if(ourBrow.canGoBack())
			ourBrow.goBack();
			break;
		case R.id.bForward:
			if(ourBrow.canGoForward())
				ourBrow.goForward();
			
			break;
		case R.id.bRefresh:
			ourBrow.reload();
				
			break;
		case R.id.bHistory:
			ourBrow.clearHistory();
			break;
		}
	}

	
	
}
