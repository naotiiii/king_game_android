package com.example.king_game.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.king_game.R;

public class MainActivity extends AppCompatActivity {

    private EditText mPeopleNum;

    private Button mStartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        mPeopleNum = findViewById(R.id.edit_text_people);

        mStartButton = findViewById(R.id.button_start);
        mStartButton.setOnClickListener(v -> onClickStartBtn());
    }

    /**
     * スタートボタン処理
     */
    private void onClickStartBtn() {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra(PlayActivity.PLAY_PEOPLE_NUMBER, mPeopleNum.getText().toString());
        startActivity(intent);
    }
}