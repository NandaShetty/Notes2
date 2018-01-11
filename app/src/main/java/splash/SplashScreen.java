package splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import sample.app.com.notes2.MainActivity;
import sample.app.com.notes2.R;

/**
 * Created by Nanda devi shetty b on 29-12-2017.
 */

public class SplashScreen  extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
