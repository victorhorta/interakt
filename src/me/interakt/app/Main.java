package me.interakt.app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Main extends Activity implements OnTouchListener, android.view.View.OnClickListener {
	public final static String TAG = "Main";
	ImageView display1;

	ArrayList<ImageView> buttons = new ArrayList<ImageView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttons.add((ImageView) findViewById(R.id.imageButton_speak));
		buttons.add((ImageView) findViewById(R.id.imageButton_readtext));
		buttons.add((ImageView) findViewById(R.id.imageButton_edit));
		buttons.add((ImageView) findViewById(R.id.imageButton_config));

		for (ImageView v : buttons) {
			v.setOnTouchListener(this);
		}

	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// public boolean onTouch(View v, MotionEvent event) {
		Log.d(TAG, "onTouched: " + v.getTag().toString());
		// Log.d(TAG, "onTouched: ");

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			((ImageView) v).setBackgroundResource(R.drawable.image_bg_click);
			return true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			((ImageView) v).setBackgroundResource(R.drawable.image_bg);
			switch (v.getId()){
				case R.id.imageButton_readtext:
					Log.d(TAG, "Starting new intent from: " + v.getTag().toString());
					Intent i1 = new Intent(Main.this, ReadText.class);
					Main.this.startActivity(i1);
					break;
				case R.id.imageButton_speak:
					Log.d(TAG, "Starting new intent from: " + v.getTag().toString());
					Intent i2 = new Intent(Main.this, ReadBoard.class);
					Main.this.startActivity(i2);
					break;
				case R.id.imageButton_edit:
					Log.d(TAG, "Starting new intent from: " + v.getTag().toString());
					//Intent i3 = new Intent(Main.this, EditNodes.class);
					//Main.this.startActivity(i3);
					break;
				default:
					Log.d(TAG, "onTouched: barro");
				return true;
			}
		}

		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
			case R.id.imageButton_readtext:
				Log.d(TAG, "onClick: " + v.getTag().toString());
				Intent i = new Intent(Main.this, ReadText.class);
				Main.this.startActivity(i);
				break;
			default:
				Log.d(TAG, "onTouched: barro");
		}
			
	}



	/*
	 * @Override public boolean onTouch(View v, MotionEvent event) { Log.d(TAG,
	 * "onTouched!!"); // TODO Auto-generated method stub
	 * 
	 * if (event.getAction() == MotionEvent.ACTION_DOWN) {
	 * ((ImageView)v).setBackgroundResource(R.drawable.image_bg_click); return
	 * true; } else if (event.getAction() == MotionEvent.ACTION_UP) {
	 * ((ImageView)v).setBackgroundResource(R.drawable.image_bg); return true; }
	 * 
	 * return false; }
	 */

}

/*
 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
 * menu; this adds items to the action bar if it is present.
 * getMenuInflater().inflate(R.menu.intro, menu); return true; }
 */