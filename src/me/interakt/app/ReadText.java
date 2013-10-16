package me.interakt.app;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadText extends Activity implements OnClickListener,
		OnTouchListener, OnInitListener {
	public static final String TAG = "ReadText";
	EditText read;
	TextView title;
	ArrayList<ImageView> buttons = new ArrayList<ImageView>();
	private TextToSpeech tts;
	private int MY_DATA_CHECK_CODE = 0;
	public Locale pt_BR = new Locale("pt", "BR");

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_readtext);

		Intent checkTTSIntent = new Intent();
		checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

		read = (EditText) findViewById(R.id.edittext_read);
		title = (TextView) findViewById(R.id.readtext_title);
		buttons.add((ImageView) findViewById(R.id.read_read));
		buttons.add((ImageView) findViewById(R.id.read_del));
		buttons.add((ImageView) findViewById(R.id.read_share));
		buttons.add((ImageView) findViewById(R.id.read_help));

		for (ImageView v : buttons) {
			v.setOnTouchListener(this);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "onTouched: " + v.getTag().toString());
			((ImageView) v).setBackgroundResource(R.drawable.image_bg_click);
			return true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			((ImageView) v).setBackgroundResource(R.drawable.image_bg);
			switch (v.getId()) {
			case R.id.read_read:
				Log.d(TAG, "Reading...");
				speakWords(read.getText().toString());
				break;
			case R.id.read_del:
				Log.d(TAG, "Deleting...");
				read.setText("");
				shutUp();
				break;
			case R.id.read_share:
				Log.d(TAG, "Sharing...");
				if(!read.getText().toString().isEmpty()){
					Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					//shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mensagem do interakt!");
					shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,	"interakt:  " + read.getText().toString());
					startActivity(Intent.createChooser(shareIntent, "Compartilhar via..."));
				}
				else{
					Toast toast = Toast.makeText(getApplicationContext(), "Mensagem vazia", Toast.LENGTH_SHORT);
					toast.show();
				}
				
				break;
			case R.id.read_help:
				Log.d(TAG, "Asking for help...");
				break;
			default:
				Log.d(TAG, "onTouched: barro");
			}
			return true;
		}

		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// switch v.getId(){
		// case:

		// }

	}

	public void onInit(int initStatus) {
		// check for successful instantiation
		if (initStatus == TextToSpeech.SUCCESS) {
			tts.setLanguage(pt_BR);
			//if (tts.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
			//	tts.setLanguage(Locale.US);
		} else if (initStatus == TextToSpeech.ERROR) {
			Toast.makeText(this, "Sorry! Text To Speech failed...",
					Toast.LENGTH_LONG).show();
		}
	}

	/*
	 * @Override public void onInit(int status) { if (status ==
	 * TextToSpeech.SUCCESS){ int result = speech.setLanguage(Locale.US);
	 * 
	 * if (result == TextToSpeech.LANG_MISSING_DATA || result ==
	 * TextToSpeech.LANG_NOT_SUPPORTED){ Log.e(TAG,
	 * "This Language is not supported"); }
	 * 
	 * else{ buttons.get(0).setEnabled(true); speak(); } }
	 * 
	 * else{ Log.e(TAG, "Initilization Failed!"); }
	 * 
	 * }
	 */
	private void speakWords(String speech) {
		// speak straight away
		tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
	}
	
	private void shutUp() {
		// speak straight away
		tts.stop();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// the user has the necessary data - create the TTS
				tts = new TextToSpeech(this, this);
			} else {
				// no data - install it now
				Intent installTTSIntent = new Intent();
				installTTSIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installTTSIntent);
			}
		}
	}

}
