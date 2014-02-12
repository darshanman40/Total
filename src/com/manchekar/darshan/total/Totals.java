package com.manchekar.darshan.total;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Totals extends Activity{

	MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total);
		ourSong=MediaPlayer.create(Totals.this, R.raw.mario);
		
		SharedPreferences getPrefs=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean musicChk = getPrefs.getBoolean("checkbox", true);
		
		if(musicChk==true)
			ourSong.start();
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				}catch(InterruptedException e){e.printStackTrace();}
			finally{
				Intent openStartingPoint = new Intent("com.manchekar.darshan.total.MENU");
				
				startActivity(openStartingPoint);
			}
		}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}
	
	
	
}
