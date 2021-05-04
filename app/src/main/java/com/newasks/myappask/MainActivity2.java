package com.newasks.myappask;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity2 extends AppCompatActivity {

    private Intent intent;
    private MediaPlayer mediaPlayer;
    private Button i, h, s, sc, g, a;
    private Animation animation;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mediaPlayer = MediaPlayer.create(this, R.raw.sound_click);
        i = findViewById(R.id.but_den);
        h = findViewById(R.id.but_hist);
        s = findViewById(R.id.but_spo);
        sc = findViewById(R.id.but_sciences);
        g = findViewById(R.id.but_geg);
        a = findViewById(R.id.but_act);
        animation = AnimationUtils.loadAnimation(this, R.anim.main2);
        i.startAnimation(animation);
        h.startAnimation(animation);
        s.startAnimation(animation);
        sc.startAnimation(animation);
        g.startAnimation(animation);
        a.startAnimation(animation);
    }

    public void islam(View view) {
        mediaPlayer.start();
        intent = new Intent(getBaseContext(), MainActivity3.class);
        intent.putExtra("i", 1);
        startActivity(intent);
    }

    public void history(View view) {
        mediaPlayer.start();
        intent = new Intent(getBaseContext(), MainActivity3.class);
        intent.putExtra("i", 2);
        startActivity(intent);
    }

    public void sport(View view) {
        mediaPlayer.start();
        intent = new Intent(getBaseContext(), MainActivity3.class);
        intent.putExtra("i", 3);
        startActivity(intent);


    }

    public void sciences(View view) {
        mediaPlayer.start();
        intent = new Intent(getBaseContext(), MainActivity3.class);
        intent.putExtra("i", 4);
        startActivity(intent);
    }

    public void geography(View view) {
        mediaPlayer.start();
        intent = new Intent(getBaseContext(), MainActivity3.class);
        intent.putExtra("i", 5);
        startActivity(intent);
    }

    public void actors(View view) {
        mediaPlayer.start();
        intent = new Intent(getBaseContext(), MainActivity3.class);
        intent.putExtra("i", 6);
        startActivity(intent);
    }
}