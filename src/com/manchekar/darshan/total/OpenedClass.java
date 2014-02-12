package com.manchekar.darshan.total;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenedClass extends Activity implements View.OnClickListener, OnCheckedChangeListener, android.widget.RadioGroup.OnCheckedChangeListener{
 
	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String gotBread;
	String setData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.send);
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et = getData.getString("name", "Darshi is...");
		String values=getData.getString("List", "4");
		initialize();
		
		
		
		if(values.contentEquals("1")){
			question.setText(et);
		}//else{
			//question.setText("not option 4");
		//Bundle gotBasket = getIntent().getExtras();
		//gotBread=gotBasket.getString("key");
		//question.setText(gotBread);
	//	}
	}
	private void initialize() {
		// TODO Auto-generated method stub
		question=(TextView)findViewById(R.id.tvQuestion);
		test=(TextView)findViewById(R.id.tvText);
		returnData=(Button)findViewById(R.id.bReturn);
		returnData.setOnClickListener(this);
		selectionList=(RadioGroup)findViewById(R.id.rgAnswers);
		selectionList.setOnCheckedChangeListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", setData);
		person.putExtras(backpack);
		setResult(RESULT_OK,person);
		finish();
	}
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		
		// TODO Auto-generated method stub
		switch(arg1){
		case R.id.rCrazy:
			setData = "Probably Right!";
			break;
		case R.id.rSexy:
			setData = "Definitely Right!";
			break;
		case R.id.Both:
			setData = "Spot On!";
			break;
		}
		test.setText(setData);
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
