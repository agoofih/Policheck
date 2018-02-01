package se.my.daik.policheck.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.my.daik.policheck.screen.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.start(this);
    }
}
