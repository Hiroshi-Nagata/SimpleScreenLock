package com.example.simplescreenlock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CountDown mCountDown;
    private TextView mTextTimer;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.JAPAN);
    private Button mButtonStart;
    private int mInitialValue = -32400000;
    private NumberPicker mSecondsNumberPicker;
    private NumberPicker mMinutesNumberPicker;
    private NumberPicker mHourNumberPicker;
    private long mSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSecondsNumberPicker = findViewById(R.id.text_seconds_number_picker);
        mMinutesNumberPicker = findViewById(R.id.text_minutes_number_picker);
        mHourNumberPicker = findViewById(R.id.text_hour_number_picker);

        initNumberPicker();

        mSecondsNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                mSeconds = numberPicker.getValue() * 1000;
                mCountDown = new CountDown(mSeconds, 1000);

                mTextTimer = findViewById(R.id.text_timer);
                mTextTimer.setText(mSimpleDateFormat.format(mInitialValue + mSeconds));

                mCountDown.setOnFinishListener(new OnFinishListener() {
                    @Override
                    public void onFinish() {
                        mTextTimer.setText(mSimpleDateFormat.format(mInitialValue));
                    }
                });

                mCountDown.setOnTickListener(new OnTickListener() {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTextTimer.setText(mSimpleDateFormat.format(millisUntilFinished + mInitialValue));
                    }
                });

                mButtonStart = findViewById(R.id.button_timer_start);
                mButtonStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCountDown.start();
                    }
                });
            }
        });
    }

    private void initNumberPicker() {
        mSecondsNumberPicker.setValue(0);
        mSecondsNumberPicker.setMaxValue(60);
        mSecondsNumberPicker.setMinValue(0);

        mMinutesNumberPicker.setValue(0);
        mMinutesNumberPicker.setMaxValue(60);
        mMinutesNumberPicker.setMinValue(0);

        mHourNumberPicker.setValue(0);
        mHourNumberPicker.setMaxValue(23);
        mHourNumberPicker.setMinValue(0);
    }
}


