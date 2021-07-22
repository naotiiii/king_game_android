package com.example.king_game.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.versionedparcelable.VersionedParcel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.king_game.R;

import java.text.ParseException;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private EditText mPeopleNum;

    private Button mStartButton;

    private ConstraintLayout mMainLayout;
    private InputMethodManager mInputMethodManager;


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

        mMainLayout = findViewById(R.id.constraint_layout_main);
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * スタートボタン処理
     */
    private void onClickStartBtn() {
        if (mPeopleNum.getText().toString() == null || mPeopleNum.getText().toString().equals("")) {
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

