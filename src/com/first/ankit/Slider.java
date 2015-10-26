package com.first.ankit;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

// Using FrameLayout
public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener {

	Button handle1, handle2, handle3, handle;
	CheckBox cbSlide;
	SlidingDrawer sD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		handle1 = (Button) findViewById(R.id.button1);
		handle2 = (Button) findViewById(R.id.button2);
		handle3 = (Button) findViewById(R.id.button3);
		cbSlide = (CheckBox) findViewById(R.id.cbSlide);
		sD = (SlidingDrawer) findViewById(R.id.slidingD);
		sD.setOnDrawerOpenListener(this);
		cbSlide.setOnCheckedChangeListener(this);
		handle1.setOnClickListener(this);
		handle2.setOnClickListener(this);
		handle3.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button1:
			sD.open();
			break;
		case R.id.button2:
			sD.toggle();
			break;
		case R.id.button3:
			sD.close();
			break;
		}
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if(arg0.isChecked()){
			sD.lock();
		}else{
			sD.unlock();
		}
	}

	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mP = MediaPlayer.create(this, R.raw.r);
		mP.start();
	}
}
