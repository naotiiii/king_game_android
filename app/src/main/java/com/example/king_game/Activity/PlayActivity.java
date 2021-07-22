package com.example.king_game.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.king_game.Adapter.PlayGridAdapter;
import com.example.king_game.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class PlayActivity extends AppCompatActivity {

    public static final String PLAY_PEOPLE_NUMBER = "PLAY_PEOPLE_NUMBER";

    private int mInputNumber;

    private GridView mGridView;

    // 入力された番号までの配列 [0,1,2,3・・・]
    private ArrayList mArrayNumber = new ArrayList<Integer>();
    // 選択された 番号 (0~)
    private ArrayList mTappedList = new ArrayList<Integer>();
    // 王様番号
    private static Integer mKingNum = null;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);

        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();

        PlayGridAdapter adapter = new PlayGridAdapter(this, mInputNumber);
        mGridView.setAdapter(adapter);
    }

    /**
     * 初期表示
     */
    private void initView() {
        setBackButton();

        mGridView = findViewById(R.id.grid_view);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTappedList.add(position);
                Intent intent;
                if (mKingNum == position) {
                    intent = new Intent(PlayActivity.this, KingActivity.class);
                } else {
                    intent = new Intent(PlayActivity.this, CitizenActivity.class);
                }
                startActivity(intent);
            }
        });

        // 入力された数を int形にキャストする。
        try {
            mInputNumber = Integer.parseInt(getIntent().getStringExtra(PLAY_PEOPLE_NUMBER));
        } catch (NumberFormatException e) {
            Log.d("PlayActivity", String.valueOf(e));
        }

        setContent();
    }


    /**
     * ツールバーに戻るボタンをセット
     */
    private void setBackButton() {
        Toolbar toolbar = findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
    }


    /**
     * 正解番号や番号系の設定
     */
    private void setContent() {
        // 入力した値までの配列
        for (int i = 0; i < mInputNumber; i++) {
            mArrayNumber.add(i);
        }
        System.out.println(mArrayNumber);

        // 王様の番号付与
        if (mKingNum == null) {
            // 初回のみ
            Random random = new Random();
            mKingNum = random.nextInt(mInputNumber);
        }
        System.out.println("random Num = " + mKingNum + ", 入力された値" + mInputNumber);






    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // backボタンがタップされた
            case android.R.id.home:
                mKingNum = null;
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
