package com.first.ankit;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener {

	/* SoundPool is loaded into memory.... Used for short clips
	 * MediaPlayer is for long clips 
	 */
	
	SoundPool sP;
	int explosion = 0;
	MediaPlayer mP1, mP2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		
		// Set up a sound pool...
		sP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sP.load(this, R.raw.f, 1);
		// Set up a media player
		mP1 = MediaPlayer.create(this, R.raw.s);
		//mP2 = MediaPlayer.create(this, R.raw.r);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		// Play sound when clicked on the view
		if(explosion!=0)
		sP.play(explosion, 1, 1, 0, 0, 1);		
	}

	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		mP1.start();
	//	mP2.start();
		return false;
	}
}