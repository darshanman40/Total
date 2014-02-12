package com.manchekar.darshan.total;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{

	String classes[]={"Main Activity","Text Play","Email","Camera","Data","GFX","GFXSurface","SoundStuff","Slider","Tabs","Simple Browser","Flipper","SharedPrefs","Internal Data","External Data","SQLiteExample","Accelerate","Http Example"};
	//classes[4];
	
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		
		
		
		
		return true;
		
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.aboutUs:
			Intent i=new Intent("com.manchekar.darshan.total.ABOUT");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent p= new Intent("com.manchekar.darshan.total.PREFS");
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		
		}
		return false;
		
		//return super.onOptionsItemSelected(item);
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
		
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String cheese=classes[position];
		super.onListItemClick(l, v, position, id);
		try{
			Class ourClass=Class.forName("com.manchekar.darshan.total."+cheese.replaceAll("\\s",""));
			Intent ourIntent=new Intent(Menu.this,ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){e.printStackTrace();}
	
	}

	
	
}
