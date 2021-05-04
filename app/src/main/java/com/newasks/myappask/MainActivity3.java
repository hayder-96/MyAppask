package com.newasks.myappask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity3 extends AppCompatActivity {

    private Intent intent;
    private int x;
    private MediaPlayer mediaPlayer;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mediaPlayer=MediaPlayer.create(this,R.raw.sound_click);

    }

    public void start(View view) {
        mediaPlayer.start();
        startgame(1,"i");
        startgame(2,"i");
        startgame(3,"i");
        startgame(4,"i");
        startgame(5,"i");
        startgame(6,"i");




    }


    private void startgame(int a,String Select) {

    intent=getIntent();
    x=intent.getIntExtra(Select,0);
        if(x==a) {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        i.putExtra(Select, a);
        startActivity(i);


        }
}

    public void share(View view) {

        String h="hayder";

        String link="https://play.google.com/store/apps/details?id=com.newasks.myappask";

        Intent intent=new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT,h+"\n"+link);
        startActivity(intent);
    }
}