package com.example.simplescreenlock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CountDown mSecondsCountDown;
    private CountDown mMinutesCountDown;
    private TextView mTextTimer;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.JAPAN);
    private Button mButtonStart;
    private int mInitialValue = -32400000;
    private NumberPicker mSecondsNumberPicker;
    private NumberPicker mMinutesNumberPicker;
    private NumberPicker mHourNumberPicker;
    private long mSeconds;
    private long mMinutes;
    private long mHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSecondsNumberPicker = findViewById(R.id.text_seconds_number_picker);
        mMinutesNumberPicker = findViewById(R.id.text_minutes_number_picker);
        mHourNumberPicker = findViewById(R.id.text_hour_number_picker);

        initNumberPicker();

        mHourNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                setHour(mHourNumberPicker.getValue());
            }
        });

        mMinutesNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                setMinutes(mMinutesNumberPicker.getValue());
            }
        });

        mSecondsNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                setSeconds(mSecondsNumberPicker.getValue());
            }
        });

//        mSecondsNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
//                mSeconds = numberPicker.getValue() * 1000;
//                mSecondsCountDown = new CountDown(mSeconds, 1000);
//
//                mTextTimer = findViewById(R.id.text_timer);
//                mTextTimer.setText(mSimpleDateFormat.format(mInitialValue + getMinutes()));
//
//                mSecondsCountDown.setOnFinishListener(new OnFinishListener() {
//                    @Override
//                    public void onFinish() {
//                        mTextTimer.setText(mSimpleDateFormat.format(mInitialValue));
//                    }
//                });
//
//                mSecondsCountDown.setOnTickListener(new OnTickListener() {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        mTextTimer.setText(mSimpleDateFormat.format(millisUntilFinished + mInitialValue));
//                    }
//                });
//
//                mButtonStart = findViewById(R.id.button_timer_start);
//                mButtonStart.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mSecondsCountDown.start();
//                    }
//                });
//            }
//        });

        mButtonStart = findViewById(R.id.button_timer_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.text_title_dialog_lock_start)
                        .setMessage(R.string.text_message_dialog_lock_start)
                        .setPositiveButton(R.string.text_positive_dialog_lock_start, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), ScreenLockActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.text_negative_dialog_lock_start, null)
                        .show();
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

    public long getSeconds() {
        return mSeconds;
    }

    public void setSeconds(long seconds) {
        this.mSeconds = seconds;
    }

    public long getMinutes() {
        return mMinutes;
    }

    public void setMinutes(long minutes) {
        this.mMinutes = minutes;
    }

    public long getHour() {
        return mHour;
    }

    public void setHour(long hour) {
        this.mHour = hour;
    }
}


