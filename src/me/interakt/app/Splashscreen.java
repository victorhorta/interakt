package me.interakt.app;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

//public class Splashscreen extends Activity {
//	private static final int SPLASH_DISPLAY_TIME = 3000; /* 3 seconds */
//
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	    setContentView(R.layout.activity_splash);
//
//	    new Handler().postDelayed(new Runnable() {
//
//	        public void run() {
//
//	            Intent mainIntent = new Intent(Splashscreen.this,
//	                    Main.class);
//	            Splashscreen.this.startActivity(mainIntent);
//
//	            Splashscreen.this.finish();
//	            overridePendingTransition(R.anim.mainfadein,
//	                    R.anim.splashfadeout);
//	        }
//	    }, SPLASH_DISPLAY_TIME);
//	}
//
//}

public class Splashscreen extends Activity
{
	private final String TAG = "Splashscreen";
	private final static int MSG_CONTINUE = 1234;
    private final static long DELAY = 2000;

    @Override
    protected void onCreate(Bundle args)
    {
        super.onCreate(args);
        setContentView(R.layout.activity_splash);

        mHandler.sendEmptyMessageDelayed(MSG_CONTINUE, DELAY);
    }

    @Override
    protected void onDestroy()
    {
        mHandler.removeMessages( MSG_CONTINUE );    
        super.onDestroy();
    }

    private void _continue()
    {
        startActivity(new Intent(this, Main.class));
        overridePendingTransition(R.anim.mainfadein,
                R.anim.splashfadeout);
        finish();
    }

    private final Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch(msg.what){
                case MSG_CONTINUE:
                	Log.d(TAG, "Continuing to the main screen");
                    _continue();
                    break;
            }
        }
    };
}