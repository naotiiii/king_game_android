package com.example.king_game.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.king_game.R;

import org.w3c.dom.Text;

public class CitizenActivity extends AppCompatActivity {

    public static final String CITIZEN_NUMBER = "CITIZEN_NUMBER";

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
        numText.setText(String.format("You are No.%s\nあなたは、%s番です", num, num));

        Button button = findViewById(R.id.button_confirmed);
        button.setOnClickListener(v -> finish());
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
