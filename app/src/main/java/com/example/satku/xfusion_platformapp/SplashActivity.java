package com.example.satku.xfusion_platformapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.teramatrix.library.IoTSDK;;import static android.R.attr.onClick;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener{
Animation animation;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.imageView);
       animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animation.setAnimationListener(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.imageView).clearAnimation();
            }
        },animation.getDuration());
        findViewById(R.id.imageView).startAnimation(animation);

        IoTSDK ioTSdk = IoTSDK.getInstance(getApplicationContext());
        ioTSdk.requestPermission(this);
        }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        animation.cancel();

    }


    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
