package com.first.ankit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

	/*
	 * Save data in internal storage If application is uninstalled the data will
	 * be deleted from the internal memory
	 */
	EditText sharedData;
	TextView dataResults;
	Button save, load;
	FileOutputStream fos;
	String fileName = "InternalStorage";
	String saveData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setUpVariables();
	}

	private void setUpVariables() {
		// TODO Auto-generated method stub
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
		save = (Button) findViewById(R.id.bSave);
		load = (Button) findViewById(R.id.bLoad);

		save.setOnClickListener(this);
		load.setOnClickListener(this);

		try {
			fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSave:
			saveData = sharedData.getText().toString();
			try {
				fos = openFileOutput(fileName, Context.MODE_PRIVATE);
				fos.write(saveData.getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bLoad:
			// Call AsyncTask class and execute its methods
			new LoadSomeStuff().execute(fileName);
			break;
		}
	}

	/*
	 * AsyncTask class parameters First variable refers to what we are passing
	 * (in this case String) Second variable refers to our progress bar
	 * (integer) Third variable refers to what we are returning (String in this
	 * case)
	 */
	public class LoadSomeStuff extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;
		protected void onPreExecute(String s) {
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.setProgress(0);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String collected = null;
			FileInputStream fis = null;
			
			for(int i=0; i<20; i++) {
				publishProgress(5);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
				
			try {
				fis = openFileInput(fileName);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) != -1) {
					collected = new String(dataArray);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return (collected);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			dialog.incrementProgressBy(5);
		}

		protected void onPostExecute(String result) {
			dataResults.setText(result);
		}
	}
}