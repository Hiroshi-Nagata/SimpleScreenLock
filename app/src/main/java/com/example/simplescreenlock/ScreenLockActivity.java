package com.example.simplescreenlock;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScreenLockActivity extends AppCompatActivity {

    private String mSECONDS_KEY = "mSECONDS_KEY";
    private String mMINUTES_KEY = "mMINUTES_KEY";
    private String mHOUR_KEY = "mHOUR_KEY";
    private int mSeconds;
    private int mMinutes;
    private int mHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_lock);

        Intent intent = getIntent();
        mSeconds = intent.getIntExtra(mSECONDS_KEY, 0);
        mMinutes = intent.getIntExtra(mMINUTES_KEY, 0);
        mHour = intent.getIntExtra(mHOUR_KEY, 0);
        Log.d(this.getLocalClassName(), "seconds : " + mSeconds);
        TextView textView = findViewById(R.id.text_timer);
        textView.setText(String.valueOf(mSeconds));
    }
}
