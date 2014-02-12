package com.manchekar.darshan.total;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener{
	
	TabSpec specs;
	TabHost th;
	Button newTab,bStart,bStop;
	TextView showResults;
	long start=0, stop;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.tabs);
		th=(TabHost)findViewById(R.id.tabhost);
		newTab=(Button)findViewById(R.id.bAddTab);
		bStart=(Button)findViewById(R.id.bStartWatch);
		bStop=(Button)findViewById(R.id.bStopWatch);
		showResults=(TextView)findViewById(R.id.tvShowResult);
		
		newTab.setOnClickListener(this);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		
		th.setup();
		specs=th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		specs=th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab2");
		th.addTab(specs);
		specs=th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAddTab:
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text =new TextView(Tabs.this);
					text.setText("You've created a new Tab");
					return (text);
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);
			break;
		case R.id.bStartWatch:
			start = System.currentTimeMillis();
			
			break;
		case R.id.bStopWatch:
			if(start!=0){
			stop = System.currentTimeMillis();
			long result=stop-start;
			int milis=(int)result%100;
			int second=(int)result/1000;
			int minutes=(int) second/60;
			start=0;
			
			
			showResults.setText(String.format("%d:%02d:%02d" , minutes,second,milis));
			}
			break;
		}
		
	}

}
