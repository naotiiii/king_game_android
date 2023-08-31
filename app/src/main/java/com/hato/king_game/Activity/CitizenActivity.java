package com.hato.king_game.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hato.king_game.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class CitizenActivity extends AppCompatActivity {

    public static final String CITIZEN_NUMBER = "CITIZEN_NUMBER";

    private static AdView mAdView;

    private static AdRequest mAdRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);

        initView();
    }

    private void initView() {
        setBackButton();

        // 番号付与
        TextView numText = findViewById(R.id.text_citizen_num);
        int num = getIntent().getIntExtra(CITIZEN_NUMBER, -1);
        if (num == -1) {
            finish();
            return;
        }
        numText.setText(String.format("NO.%s", num));
        setImage(num);

        Button button = findViewById(R.id.button_confirmed);
        button.setOnClickListener(v -> finish());

        setAdViewBANNER();
    }

    /**
     * 広告をセット
     */
    private void setAdViewBANNER() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    /**
     * 画像のセット
     */
    private void setImage(int num) {
        ImageView imageView = findViewById(R.id.image_citizen);
        Drawable drawable = getDrawable(R.drawable.citizen_1);
        if (num == 1) {
            drawable = getDrawable(R.drawable.citizen_1);
        } else if (num == 2) {
            drawable = getDrawable(R.drawable.citizen_2);
        } else if (num == 3) {
            drawable = getDrawable(R.drawable.citizen_3);
        } else if (num == 4) {
            drawable = getDrawable(R.drawable.citizen_4);
        } else if (num == 5) {
            drawable = getDrawable(R.drawable.citizen_5);
        } else if (num == 6) {
            drawable = getDrawable(R.drawable.citizen_6);
        } else if (num == 7) {
            drawable = getDrawable(R.drawable.citizen_7);
        } else if (num == 8) {
            drawable = getDrawable(R.drawable.citizen_8);
        } else if (num == 9) {
            drawable = getDrawable(R.drawable.citizen_9);
        } else if (num == 10) {
            drawable = getDrawable(R.drawable.citizen_10);
        } else if (num == 11) {
            drawable = getDrawable(R.drawable.citizen_11);
        } else if (num == 12) {
            drawable = getDrawable(R.drawable.citizen_12);
        } else if (num == 13) {
            drawable = getDrawable(R.drawable.citizen_13);
        } else if (num == 14) {
            drawable = getDrawable(R.drawable.citizen_14);
        } else if (num == 15) {
            drawable = getDrawable(R.drawable.citizen_15);
        } else if (num == 16) {
            drawable = getDrawable(R.drawable.citizen_16);
        } else if (num == 17) {
            drawable = getDrawable(R.drawable.citizen_17);
        } else if (num == 18) {
            drawable = getDrawable(R.drawable.citizen_18);
        } else if (num == 19) {
            drawable = getDrawable(R.drawable.citizen_19);
        } else if (num == 20) {
            drawable = getDrawable(R.drawable.citizen_20);
        } else if (num == 21) {
            drawable = getDrawable(R.drawable.citizen_21);
        } else if (num == 22) {
            drawable = getDrawable(R.drawable.citizen_22);
        } else if (num == 23) {
            drawable = getDrawable(R.drawable.citizen_23);
        } else if (num == 24) {
            drawable = getDrawable(R.drawable.citizen_24);
        } else if (num == 25) {
            drawable = getDrawable(R.drawable.citizen_25);
        } else if (num == 26) {
            drawable = getDrawable(R.drawable.citizen_26);
        } else if (num == 27) {
            drawable = getDrawable(R.drawable.citizen_27);
        } else if (num == 28) {
            drawable = getDrawable(R.drawable.citizen_28);
        } else if (num == 29) {
            drawable = getDrawable(R.drawable.citizen_29);
        }
        imageView.setImageDrawable(drawable);
    }

    /**
     * ツールバーに戻るボタンをセット
     */
    private void setBackButton() {
        getSupportActionBar().setTitle(R.string.citizen_message);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * 戻るボタンタップ処理
     */
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
