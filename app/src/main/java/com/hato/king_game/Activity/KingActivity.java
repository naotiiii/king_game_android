package com.hato.king_game.Activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hato.king_game.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Timer;
import java.util.TimerTask;

import static com.hato.king_game.R.animator.animation_flashing;

public class KingActivity extends AppCompatActivity {

    public static final String KING_NUMBER = "KING_NUMBER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_king);

        initView();

        AdView adView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    /**
     * 初期表示
     */
    private void initView() {
        setBackButton();

        Button button = findViewById(R.id.button_confirmed);
        button.setOnClickListener(v -> finish());

       // animationKing();
    }

    /**
     * 王様画像 アニメーション
     */
    private void animationKing() {
        ImageView kingImage = findViewById(R.id.image_king);
        AnimatorSet animation = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                animation_flashing);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                animation.setTarget(kingImage);
            }
        }, 0, 2100);
    }

    /**
     * ツールバーに戻るボタンをセット
     */
    private void setBackButton() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // backボタンがタップされた
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
