package com.example.king_game.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.king_game.Adapter.PlayGridAdapter;
import com.example.king_game.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class PlayActivity extends AppCompatActivity {

    public static final String PLAY_PEOPLE_NUMBER = "PLAY_PEOPLE_NUMBER";

    private int mInputNumber;

    private ConstraintLayout mFinishLayout;

    private Button mCorrectBtn;

    private GridView mGridView;

    // 入力された番号までの配列 [0,1,2,3・・・]
    private ArrayList<Integer> mArrayNumber = new ArrayList<Integer>();
    // 選択された 番号 (0~)
    private  ArrayList<Integer> mTappedList = new ArrayList<Integer>();
    // 表示するNumberList
    private ArrayList<Integer> mShowNumberList = new ArrayList<>();

    private ArrayList<String> mCorrectNumList = new ArrayList<>();

    private TextView mFinishText;

    // 王様番号
    private static Integer mKingNum;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mShowNumberList.size() == 0) {
            mCorrectBtn.setVisibility(View.VISIBLE);

            StringBuilder str = new StringBuilder();
            for (int i=0; i < mCorrectNumList.size(); i++) {
                int num = i+1;
                if (mCorrectNumList.get(i).equals(getString(R.string.king))) {
                    str.append(num + "番目: " + mCorrectNumList.get(i) + "\n");
                    continue;
                }
                str.append( num + "番目: " + "No." + mCorrectNumList.get(i) + "\n");
            }
            mFinishText.setText(str);
        }

        PlayGridAdapter adapter = new PlayGridAdapter(this, mInputNumber, mTappedList);
        mGridView.setAdapter(adapter);
    }

    /**
     * 初期表示
     */
    private void initView() {
        setBackButton();

        mFinishLayout = findViewById(R.id.layout_finish_explain);
        mFinishLayout.setVisibility(View.GONE);

        mFinishText = findViewById(R.id.text_finish_explain);

        mCorrectBtn = findViewById(R.id.button_finish);
        mCorrectBtn.setVisibility(View.GONE);
        mCorrectBtn.setOnClickListener(v -> onClickVisibleChange(mFinishLayout, true));

        Button finishBtn = findViewById(R.id.button_close);
        finishBtn.setOnClickListener(v -> onClickVisibleChange(mFinishLayout, false));

        mGridView = findViewById(R.id.grid_view);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mTappedList.size() > 0) {
                    for (int i = 0; i < mTappedList.size(); i++) {
                        if (mTappedList.get(i) == position) {
                            return;
                        }
                    }
                }

                mTappedList.add(position);

                Intent intent;

                if (mKingNum.equals(mArrayNumber.get(position))) {
                    mCorrectNumList.add(getString(R.string.king));
                    intent = new Intent(PlayActivity.this, KingActivity.class);
                } else {
                    mCorrectNumList.add(String.valueOf(mShowNumberList.get(0)));
                    intent = new Intent(PlayActivity.this, CitizenActivity.class);
                    intent.putExtra(CitizenActivity.CITIZEN_NUMBER, mShowNumberList.get(0));
                    mShowNumberList.remove(mShowNumberList.get(0));
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
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * 閉じるボタンタップ処理
     */
    private void onClickVisibleChange(ConstraintLayout layout, boolean isVisible) {
        if (isVisible) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
    }

    /**
     * 正解番号や番号系の設定
     */
    private void setContent() {
        // 入力した値までの配列
        for (int i = 0; i < mInputNumber; i++) {
            mArrayNumber.add(i);

            if (i != mInputNumber-1) {
                mShowNumberList.add(i + 1);
            }
        }
        Collections.shuffle(mShowNumberList);
        Collections.shuffle(mArrayNumber);
        System.out.println(mArrayNumber);

        // 王様の番号付与
        if (mKingNum == null) {
            // 初回のみ
            Random random = new Random();
            mKingNum = random.nextInt(mInputNumber);
        }
        System.out.println("King Num = " + mKingNum + ", 入力された値" + mInputNumber);
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
