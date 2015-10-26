package com.first.ankit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyClass extends View {

	Bitmap rball;
	float changingY;
	Typeface font;		// For selecting the font type of the text
	
	public MyClass(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//Initialize image, Y position of the image and font type of the text 
		rball = BitmapFactory.decodeResource(getResources(), R.drawable.ok);
		changingY = 0;
		font = Typeface.createFromAsset(context.getAssets(), "trebucit.ttf");
	}
	@Override
	protected void onDraw(Canvas canvas) {
		
		int h;
		
		//Get height of the screen
		h = canvas.getHeight();
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		// Set background color
		canvas.drawColor(Color.WHITE);
		
		//Set text attributes and draw on the canvas
		Paint textPaint = new Paint();
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setColor(Color.RED);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		canvas.drawText("Ankit", (canvas.getWidth()/2), 100, textPaint);
		
		// Set background Image
		canvas.drawBitmap(rball, (canvas.getWidth()/2), changingY, null);
		if(changingY < canvas.getHeight())
			changingY = changingY+10;
		else
			changingY = 0;
		
		//Define a rectangle, Set rectangle attributes and draw on the canvas
		Rect middleRect = new Rect();
		middleRect.set(0, (h/2), canvas.getWidth(), (h/2)+100);
		Paint ourColor = new Paint();
		ourColor.setColor(Color.BLUE);
		canvas.drawRect(middleRect, ourColor);
		
		// Start over again
		invalidate();
	}
}
