package com.example.updates2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private Handler mHandler = new Handler();
    ImageView imageView;
    Button tosignup,tologin;
    ProgressBar progressBar;
    Integer vll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.logo);
        progressBar = findViewById(R.id.pg_bar);
        tologin = findViewById(R.id.login);
        tosignup = findViewById(R.id.signup);
        progressBar.setVisibility(View.VISIBLE);
        Animation animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        Animation animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        SharedPreferences sharedPreferences = getSharedPreferences("token",0);
        String tmp = sharedPreferences.getString("token",null);


        animFadeIn.reset();
        imageView.clearAnimation();
        imageView.startAnimation(animFadeIn);
        animFadeOut.reset();
        imageView.clearAnimation();
        imageView.startAnimation(animFadeOut);
        animFadeIn.reset();
        imageView.clearAnimation();
        imageView.startAnimation(animFadeIn);
        tosignup.setVisibility(View.GONE);
        tologin.setVisibility(View.GONE);
        vll =  1;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tosignup.setVisibility(View.VISIBLE);
                tologin.setVisibility(View.VISIBLE);
                    tosignup.clearAnimation();
                    tosignup.startAnimation(animFadeIn);
                    tologin.clearAnimation();
                    tologin.startAnimation(animFadeIn);
                    animFadeOut.reset();
                    tosignup.clearAnimation();
                    tosignup.startAnimation(animFadeOut);
                    tologin.clearAnimation();
                    tologin.startAnimation(animFadeOut);
                    animFadeIn.reset();
                    tosignup.clearAnimation();
                    tosignup.startAnimation(animFadeIn);
                    tologin.clearAnimation();
                    tologin.startAnimation(animFadeIn);
                progressBar.setVisibility(View.GONE);
                if(tmp != null){
                    Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
                }
                }
        },7000);

        tosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,sign_in.class);
                startActivity(intent);
            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this,log_in.class);
                startActivity(intent1);
            }
        });

    }
}