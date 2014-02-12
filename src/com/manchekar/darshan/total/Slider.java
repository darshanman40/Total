package com.manchekar.darshan.total;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener{

	Button h1,h2,h3,h4;
	CheckBox chkbox;
	SlidingDrawer sd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		
		h1=(Button)findViewById(R.id.handle1);
		h2=(Button)findViewById(R.id.handle2);
		h3=(Button)findViewById(R.id.handle3);
		h4=(Button)findViewById(R.id.handle4);
		chkbox=(CheckBox)findViewById(R.id.cbSlidable);
		sd=(SlidingDrawer)findViewById(R.id.slidingD);
		
		sd.setOnDrawerOpenListener(this);
		chkbox.setOnCheckedChangeListener(this);
		h1.setOnClickListener(this);
		h2.setOnClickListener(this);
		h3.setOnClickListener(this);
		h4.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()){
		case R.id.handle1:
			sd.open();
			break;
		
		case R.id.handle2:
			
			break;
			
		case R.id.handle3:
			sd.toggle();
			break;
			
		case R.id.handle4:
			sd.close();
			break;
		}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if(arg0.isChecked()){
			sd.lock();
		}else{
			sd.unlock();
		}
		
	}
	public void onDrawerOpened(){
		MediaPlayer mp=MediaPlayer.create(this, R.raw.explosion);
		mp.start();
	}

}
