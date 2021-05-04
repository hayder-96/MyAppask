package com.newasks.myappask;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.newasks.myappask.Model.Item;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private Button btn_ask, btn1, btn2, btn3, btn4, time, btn_true, btn_false, siz;
    private TextView tri_nin, point;
    private int rand;
    private Intent intent;
    private Random random = new Random();
    private int i, answer, ti = 20, id, s = 0, t, open;
    private MediaPlayer media_false, media_true, media_click;
    private Handler handler = new Handler();
    private ArrayList<Item> ii;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btn_false = findViewById(R.id.image_false);
        btn_true = findViewById(R.id.btn_true);
        tri_nin = findViewById(R.id.tri_nin);
        btn_ask = findViewById(R.id.btn_ask);
        btn1 = findViewById(R.id.btn_answer1);
        btn2 = findViewById(R.id.btn_answer2);
        btn3 = findViewById(R.id.btn_answer3);
        btn4 = findViewById(R.id.btn_answer4);
        point = findViewById(R.id.point);
        time = findViewById(R.id.time);
        siz = findViewById(R.id.size);
        media_click = MediaPlayer.create(this, R.raw.sound_click);
        media_false = MediaPlayer.create(this, R.raw.sound_false);
        media_true = MediaPlayer.create(this, R.raw.sound_true);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1707823485728314/8751187053");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                handler.postDelayed(run,1000);
            }

        });



        MobileAds.initialize(this, "ca-app-pub-1707823485728314/8696999124");


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);



loadRewardedVideoAd();












        ii = new ArrayList<>();
        intent = getIntent();


        i = intent.getIntExtra("i", 0);


        if (i == 1) {
            DatabaseIslam ss = new DatabaseIslam(this);
            ii = ss.GetDataIslam();
            print();

            if (ii.size() <= 1) {
                ClearData();
            }
        } else if (i == 2) {
            DatabaseHistory dh = new DatabaseHistory(this);
            ii = dh.GetDatahistory();
            print();

            if (ii.size() <= 1) {

                ClearData();
            }
        } else if (i == 3) {
            Databasesport dp = new Databasesport(this);
            ii = dp.GetDatasport();
            print();

            if (ii.size() <= 1) {
                ClearData();
            }


        } else if (i == 4) {
            Databasesciences ds = new Databasesciences(this);
            ii = ds.GetDataSciences();
            print();

            if (ii.size() <= 1) {
                ClearData();
            }
        } else if (i == 5) {
            Databasegeography dg = new Databasegeography(this);
            ii = dg.GetDatageography();
            print();

            if (ii.size() <= 1) {
                ClearData();
            }
        } else if (i == 6) {
            DatabaseActrs da = new DatabaseActrs(this);
            ii = da.GetDataactr();
            print();

            if (ii.size() <= 1) {
                ClearData();
            }


        }

    }

    private void print() {

        handler.removeCallbacks(run);
        ti = 20;
        rand = random.nextInt(ii.size());
        id = ii.get(rand).getId();
        btn_ask.setText(ii.get(rand).getAsk());
        btn1.setText(ii.get(rand).getAnswer1());
        btn2.setText(ii.get(rand).getAnswer2());
        btn3.setText(ii.get(rand).getAnswer3());
        btn4.setText(ii.get(rand).getAnswer4());
        answer = Integer.parseInt(ii.get(rand).getAnswercoect());
        Time();

        if (mInterstitialAd.isLoaded() && rand==1|| rand==5|| rand==10||rand==15||rand==20||rand==25||rand==30||rand==35||rand==40||rand==45||rand==50) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }




    }

    private void Answetrue() {
        Inemmotrtrue();
        s++;
        siz.setText(s + "");


        if (ii.size() <= 0) {
            ClearData();
        } else {
            ii.remove(rand);
            print();

        }


    }

    private void Answerfalse() {
        t = Integer.parseInt(tri_nin.getText().toString());
        Inemmotrfalse();
        t--;
        tri_nin.setText(t + "");
        if (t <= 0) {
            tri_nin.setText(0 + "");
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage(getResources().getString(R.string.loser));
            b.setCancelable(false);

            b.setPositiveButton(getResources().getString(R.string.try_again), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tri_nin.setText(5 + "");
                    s = 0;
                    point.setText(3+"");
                    siz.setText(0 + "");
                    ti = 20;
                    handler.postDelayed(run, 1000);
                    Restart();
                }

            });
            b.setNegativeButton(getResources().getString(R.string.out), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();


            handler.postDelayed(run, 1000);

        }

        if (ii.size() <= 0) {
            ClearData();
        } else {
            if (t <= 0) {
                handler.removeCallbacks(run);
            } else {
                ii.remove(rand);
                print();
            }
        }
    }


    public void btn1(View view) {
        if (answer == 1) {
            Answetrue();
        } else {
            Answerfalse();

        }
    }

    public void btn2(View view) {
        if (answer == 2) {
            Answetrue();
        } else {
            Answerfalse();
        }
    }

    public void btn3(View view) {
        if (answer == 3) {
            Answetrue();
        } else {
            Answerfalse();
        }
    }

    public void btn4(View view) {
        if (answer == 4) {
            Answetrue();
        } else {
            Answerfalse();
        }

    }


    Runnable run = new Runnable() {
        @Override
        public void run() {

            Time();
        }
    };

    private void Time() {
        handler.postDelayed(run, 1000);
        time.setText(ti + "");
        ti--;

        if (ii.size() <= 1) {
            ClearData();
        } else {
            if (ti <= 0) {
                t = Integer.parseInt(tri_nin.getText().toString());
                Inemmotrfalse();

                tri_nin.setText(t + "");
                if (t <= 1) {
                    tri_nin.setText(0 + "");
                    AlertDialog.Builder b = new AlertDialog.Builder(this);
                    b.setMessage(getResources().getString(R.string.loser));
                    b.setCancelable(false);
                    b.setPositiveButton(getResources().getString(R.string.try_again), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tri_nin.setText(5 + "");
                            s = 0;
                            siz.setText(0 + "");
                            ti = 20;
                            handler.postDelayed(run, 1000);
                            Restart();


                        }
                    });
                    b.setNegativeButton(getResources().getString(R.string.out), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();

                    handler.removeCallbacks(run);
                } else {
                    Answerfalse();
                    ti = 20;
                    print();
                }
            }
        }
    }

    private void Inemmotrfalse() {

        btn_false.setVisibility(View.VISIBLE);
        Animation a = AnimationUtils.loadAnimation(getBaseContext(), R.anim.main1);
        btn_false.startAnimation(a);
        media_false.start();
        btn_false.setVisibility(View.INVISIBLE);

    }

    private void Inemmotrtrue() {

        btn_true.setVisibility(View.VISIBLE);
        Animation a = AnimationUtils.loadAnimation(getBaseContext(), R.anim.main1);
        btn_true.startAnimation(a);
        media_true.start();
        btn_true.setVisibility(View.INVISIBLE);

    }

    private void ClearData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String ss=siz.getText().toString();
        builder.setTitle(getResources().getString(R.string.game_over)+"لقد اجبت على"+ss+"سؤال من 50");
        builder.setMessage(getResources().getString(R.string.select));
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.out), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }


        });
        builder.setNegativeButton(getResources().getString(R.string.try_game), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                tri_nin.setText(5 + "");
                s = 0;
                siz.setText(0 + "");
                ti = 20;
                handler.postDelayed(run, 1000);
                Restart();


            }

        });
        builder.show();
        handler.removeCallbacks(run);
        siz.setText(50 + "");

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(run);



    }


    private void Restart() {
        i = intent.getIntExtra("i", 0);
        if (i == 1) {
            DatabaseIslam ss = new DatabaseIslam(this);
            ii = ss.GetDataIslam();
        } else if (i == 2) {
            DatabaseHistory dh = new DatabaseHistory(this);
            ii = dh.GetDatahistory();
        } else if (i == 3) {
            Databasesport dp = new Databasesport(this);
            ii = dp.GetDatasport();
        } else if (i == 4) {
            Databasesciences ds = new Databasesciences(this);
            ii = ds.GetDataSciences();
        } else if (i == 5) {
            Databasegeography dg = new Databasegeography(this);
            ii = dg.GetDatageography();
        } else if (i == 6) {
            DatabaseActrs da = new DatabaseActrs(this);
            ii = da.GetDataactr();
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.outer));
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
                handler.postDelayed(run, 1000);
            }
        }).show();

        handler.removeCallbacks(run);

    }

    private void OpenAnswer() {
        media_click.start();
        open = Integer.parseInt(point.getText().toString());
        if (open == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage(getResources().getString(R.string.no_point));
            builder.setPositiveButton(getResources().getString(R.string.done), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (mRewardedVideoAd.isLoaded()){
                        mRewardedVideoAd.show();
                        handler.removeCallbacks(run);
                    }else{
                        handler.postDelayed(run,1000);
                        Toast.makeText(getBaseContext(),"لا يمكن فتح الاعلان اتصل بالانترنت",Toast.LENGTH_SHORT).show();
                    }

                }
            });
            builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                    handler.postDelayed(run, 1000);
                }
            }).show();
            handler.removeCallbacks(run);
        } else {

            handler.removeCallbacks(run);
            open--;
            point.setText(open + "");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage(getResources().getString(R.string.open) + answer);
            builder.setPositiveButton(getResources().getString(R.string.done), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    handler.postDelayed(run, 1000);
                }
            }).show();
        }

    }

    public void help(View view) {
        OpenAnswer();
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {



    }

    @Override
    public void onRewardedVideoStarted() {

        handler.removeCallbacks(run);
    }

    @Override
    public void onRewardedVideoAdClosed() {

        loadRewardedVideoAd();
        handler.postDelayed(run,1000);
        point.setText(1+"");

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-1707823485728314/8696999124",
                new AdRequest.Builder().build());


    }
}



