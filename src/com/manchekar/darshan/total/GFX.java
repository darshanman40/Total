package com.manchekar.darshan.total;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity{

	WakeLock wL;
	MyBringBack ourView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Wake-lock
		PowerManager pM=(PowerManager)getSystemService(Context.POWER_SERVICE);
		 wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
		
		super.onCreate(savedInstanceState);
		wL.acquire();
		
		ourView = new MyBringBack(this);
		setContentView(ourView);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}
	
	
	

}
