package com.manchekar.darshan.total;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	MyBringBackSurface ourSurfaceView;
	float x,y,sX,sY,fX,fY,dX,dY,scaledX,scaledY,aniX,aniY;
	Bitmap test,plus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView=new MyBringBackSurface(this);
		x=0;
		y=0;
		sX=0;
		sY=0;
		fX=0;
		fY=0;
		dX = dY = scaledX = scaledY = aniX = aniY = 0;
		ourSurfaceView.setOnTouchListener(this);
		test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.pluss);
		setContentView(ourSurfaceView);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			fX=fY=dX = dY = scaledX = scaledY = aniX = aniY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX=fX-sX;
			dY=fY-sY;
			scaledX=dX/30;
			scaledY=dY/30;
			break;
		}
		
		
		return true;
	}
	
	public class MyBringBackSurface extends SurfaceView implements Runnable{

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;
		
		public MyBringBackSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder=getHolder();
			
		}
		
		public void pause(){
			isRunning=false;
			while(true){
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				break;
			}
		}
		
		public void resume(){
			isRunning=true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		@Override
		public void run() {
			
			// TODO Auto-generated method stub
		while(isRunning){
			if(!ourHolder.getSurface().isValid() )
				continue;
			if(aniX==sX || aniY==sY)
				continue;
			Canvas canvas = ourHolder.lockCanvas();
			canvas.drawRGB(24, 10 ,250);
			
			if(x!=0 && y!=0){
				//canvas.drawBitmap(plus, x, y, null);
				canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
			}
			if(sX!=0 && sY!=0){
				canvas.drawBitmap(plus, sX-(plus.getWidth()/2), sY-(plus.getHeight()/2), null);
				
			}
			if(fX!=0 && fY!=0){
				canvas.drawBitmap(test, x-(test.getWidth()/2)-aniX, y-(test.getHeight()/2)-aniY, null);
				canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getHeight()/2), null);
								
			}
			aniX+=scaledX;
			aniY+=scaledY;
			ourHolder.unlockCanvasAndPost(canvas);
			}
			
		}

	}


}
