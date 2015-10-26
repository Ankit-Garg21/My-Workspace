package com.first.ankit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {

	MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle ankit) {
		// TODO Auto-generated method stub
		super.onCreate(ankit);
		setContentView(R.layout.splash);
		
		//Start the song in raw folder.
		ourSong = MediaPlayer.create(Splash.this, R.raw.z);
		
		//Using preferences in app
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		if(music == true)
		ourSong.start();

		new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(3000);
					ourSong.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Intent Istart = new Intent("com.first.ankit.Menu");
				startActivity(Istart);

			}
		}).start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
