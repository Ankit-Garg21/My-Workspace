package com.first.ankit;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener{
	// Variables for the components in photo.xml
	ImageView iv;
	ImageButton ib;
	Button b;
	Intent iCamera;
	final static int cameraData = 0;
	Bitmap bm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);	//Set layout photo.xml.
		initialize();
		
		//Set default image as wallpaper if Set wallpaper button is pressed
		
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bm = BitmapFactory.decodeStream(is);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		iv = (ImageView) findViewById(R.id.ivShowPic);
		ib = (ImageButton) findViewById(R.id.ibTakePic);
		b = (Button) findViewById(R.id.bSetWall);
		ib.setOnClickListener(this);
		b.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSetWall:
			try {
				//Set wallpaper. Need Permission first
				getApplicationContext().setWallpaper(bm);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.ibTakePic:
			
			// Action to take picture using default camera application
			iCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(iCamera,cameraData);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//View Image when Activity returns data.
		if(resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			bm = (Bitmap) extras.get("data");
			iv.setImageBitmap(bm);
		}
	}
}