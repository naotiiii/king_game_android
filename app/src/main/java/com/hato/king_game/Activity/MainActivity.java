package com.hato.king_game.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hato.king_game.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private Button mStartButton;
    private int numberOfPeople = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        initView();
        AdView adView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void initView(){
        TextView numberText = findViewById(R.id.number_of_people);

        ImageButton plusButton = findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfPeople += 1;
                numberText.setText(displayNum());
            }
        });

        ImageButton minusButton = findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOfPeople <= 0) return;
                numberOfPeople -= 1;
                numberText.setText(displayNum());
            }
        });

        mStartButton = findViewById(R.id.button_start);
        mStartButton.setOnClickListener(v -> onClickStartBtn());
    }

    /**
     * スタートボタン処理
     */
    private void onClickStartBtn() {

        if (1 > numberOfPeople || 30 < numberOfPeople) {
            showValidationAlert(getString(R.string.validation_int_range));
            return;
        }

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra(PlayActivity.PLAY_PEOPLE_NUMBER, numberOfPeople);
        startActivity(intent);
    }

    private void showValidationAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton(getString(R.string.close), null)
                .setMessage(message);
        builder.create();
        builder.show();
    }

    private StringBuilder displayNum() {
        StringBuilder sb = new StringBuilder();
        if (numberOfPeople < 10) {
            sb.append("0");
        }
        return sb.append(numberOfPeople);
    }
}

