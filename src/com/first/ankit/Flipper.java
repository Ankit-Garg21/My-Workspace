package com.first.ankit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener{

	ViewFlipper flip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
		flip = (ViewFlipper) findViewById(R.id.viewFlipper1);
		flip.setFlipInterval(1000);
		flip.startFlipping();
		flip.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		flip.showNext();
	}

}
