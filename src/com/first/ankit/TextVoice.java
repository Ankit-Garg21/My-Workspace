package com.first.ankit;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TextVoice extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	Button b;
	EditText speak;
	String random;
	String[] texts = {
			"Heyy What's up", "Yipee kai yay Motherfucker", "Rest in peace", "Checkmate"
	};
	TextToSpeech tts;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice);
        
        b = (Button) findViewById(R.id.bTextSpeech);
        speak = (EditText) findViewById(R.id.etSpeak);
        b.setOnClickListener(this);
        tts = new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
			
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status!=TextToSpeech.ERROR){
					tts.setLanguage(Locale.US);
				}
			}
		});
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(speak.getText().toString().length()>=1){
		random = speak.getText().toString();
		//speak.setText("Working!");
		tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);
		} else {
			//speak.setText("Working");
			Random r = new Random();
			random = texts[r.nextInt(texts.length)];
			tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(tts!=null){
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
	}
}