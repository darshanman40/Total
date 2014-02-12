package com.manchekar.darshan.total;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{

	Button save,load;
	EditText sharedData;
	TextView dataResults;
	public static String filename="MySharedString";
	SharedPreferences someData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedprefs);
		
		save = (Button)findViewById(R.id.bSave);
		load = (Button)findViewById(R.id.bLoad);
		sharedData = (EditText)findViewById(R.id.etSharedPrefs);
		dataResults = (TextView)findViewById(R.id.tvLoadSharedPrefs);
		
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		
		 someData = getSharedPreferences(filename,0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSave:
			String strData = sharedData.getText().toString();

			SharedPreferences.Editor editor = someData.edit();
			editor.putString("sharedString", strData);
			editor.commit();
			break;
		
		case R.id.bLoad:
			someData = getSharedPreferences(filename,0);
			String dataReturned = someData.getString("sharedString","Couldn't Load data");
			dataResults.setText(dataReturned);
			break;
		}
	}

}
