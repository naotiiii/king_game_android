package com.hato.king_game.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hato.king_game.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    private EditText mPeopleNum;

    private Button mStartButton;

    private ConstraintLayout mMainLayout;
    private InputMethodManager mInputMethodManager;


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
        mPeopleNum = findViewById(R.id.edit_text_people);
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mPeopleNum.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    mInputMethodManager.hideSoftInputFromWindow(mPeopleNum.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });

        mStartButton = findViewById(R.id.button_start);
        mStartButton.setOnClickListener(v -> onClickStartBtn());

        mMainLayout = findViewById(R.id.constraint_layout_main);
    }

    /**
     * Imageアニメーション
     */
    private void animationImage() {
        ImageView kingImage = findViewById(R.id.image_king);
        ImageView citizenImage = findViewById(R.id.image_citizen);

        Path path = new Path();
        path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(kingImage, View.X, View.Y, path);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(citizenImage, View.X, View.Y, path);
        animator1.setDuration(2000);
        animator1.start();
    }

    /**
     * スタートボタン処理
     */
    private void onClickStartBtn() {
        mPeopleNum.getText().toString();
        if (mPeopleNum.getText().toString().equals("")) {
            showValidationAlert(getString(R.string.explain_play_number));
            return;
        }

        int num = -1;
        try {
            num = Integer.parseInt(mPeopleNum.getText().toString());
        } catch (NumberFormatException e) {
            showValidationAlert(getString(R.string.validation_string_to_int));
            return;
        }

        if (1 > num || 30 < num) {
            showValidationAlert(getString(R.string.validation_int_range));
            return;
        }

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra(PlayActivity.PLAY_PEOPLE_NUMBER, mPeopleNum.getText().toString());
        startActivity(intent);
    }

    private void showValidationAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton(getString(R.string.close), null)
                .setMessage(message);
        builder.create();
        builder.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mInputMethodManager.hideSoftInputFromWindow(mMainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        mMainLayout.requestFocus();

        return super.onTouchEvent(event);
    }
}

