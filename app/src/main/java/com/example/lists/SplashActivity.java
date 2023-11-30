package com.example.lists;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.Handler;
        import android.widget.ProgressBar;

        import androidx.appcompat.app.AppCompatActivity;

        import java.util.Timer;
        import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    int progressInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        ProgressBar progressBar= findViewById(R.id.progressBar);

        Activity activity = SplashActivity.this;
        progressBar.setProgress(progressInt);
        progressBar.setMax(100);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                progressInt = progressInt +10;
                progressBar.setProgress(progressInt);

                if (progressBar.getProgress() == 100){
                    timer.cancel();
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    activity.startActivity(intent);
                    finish();
                }
            }
        },1000,50);

    }
}