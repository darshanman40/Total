package com.manchekar.darshan.total;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener{

	Button sqlUpdate, sqlView, sqlGetInfo, sqlDelete, sqlModify;
	EditText sqlName, sqlHotness,sqlRowId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		
		sqlGetInfo=(Button) findViewById(R.id.bGetInfo);
		sqlDelete=(Button) findViewById(R.id.bDeleteEntry);
		sqlModify=(Button) findViewById(R.id.bEditEntry);
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLopenView);
		
		sqlRowId = (EditText) findViewById(R.id.etRowId);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		
		
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		case R.id.bSQLUpdate:
			
			boolean didItWork=true;
			try{
			String name = sqlName.getText().toString();
			String hotness = sqlHotness.getText().toString();
			
			HotOrNot entry = new HotOrNot(SQLiteExample.this);
			entry.open();
			entry.createEntry(name,hotness);
			entry.close();
			
			}catch(Exception e){
				didItWork=false;
				}
			finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Hell yeah!");
					TextView tv = new TextView(this);
					tv.setText("Success!");
					d.setContentView(tv);
					d.show();
				}else{
					Dialog d = new Dialog(this);
					d.setTitle("Go to hell!");
					TextView tv = new TextView(this);
					tv.setText("YOU FAILED!");
					d.setContentView(tv);
					d.show();
				
					
				}
		}
			
			break;
		case R.id.bSQLopenView:
			
			Intent i =new Intent("com.manchekar.darshan.total.SQLVIE");
			startActivity(i);
			break;
		
		case R.id.bDeleteEntry:
			
			String dRow = sqlRowId.getText().toString();
			long ldRow = Long.parseLong(dRow);
		
			HotOrNot exx = new HotOrNot(this);
			try{
			exx.open();
			exx.deleteEntry(ldRow);
			exx.close();
			}catch(Exception e){
				Dialog d = new Dialog(this);
				d.setTitle("Error!");
				TextView tv = new TextView(this);
				tv.setText("Error Captured!");
				d.setContentView(tv);
				d.show();
				
			}
			break;
		case R.id.bEditEntry:
			String eName = sqlName.getText().toString();
			String eHotness = sqlHotness.getText().toString();
			String sRow = sqlRowId.getText().toString();
			long lRow = Long.parseLong(sRow);
			HotOrNot ex = new HotOrNot(this);
			
			ex.open();
			ex.updateEntry(lRow, eName, eHotness);
			ex.close();
			break;
			
		case R.id.bGetInfo:
			String s = sqlRowId.getText().toString();
			long l = Long.parseLong(s);
			String returnedName;
			String returnedHotness;
			HotOrNot hon = new HotOrNot(this);
			try{
			hon.open();
			returnedName = hon.getName(l);
			returnedHotness = hon.getHotness(l);
			hon.close();
			
			if(returnedName!=null || returnedHotness!=null ){
			sqlName.setText(returnedName);
			sqlHotness.setText(returnedHotness);
			}
			}catch(Exception e){
				Dialog d = new Dialog(this);
				d.setTitle("Error!");
				TextView tv = new TextView(this);
				tv.setText("Error Captured!");
				d.setContentView(tv);
				d.show();
				
			}
			break;
	
		}
	
	}

}
