package com.example.simplescreenlock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CountDown countDown;
    private TextView textTimer;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.JAPAN);
    private Button buttonStart;
    private int initialValue = -32400000;
    private NumberPicker numberPicker;
    private int hour;
    private long seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberPicker = findViewById(R.id.text_number_picker);
        initNumberPicker();

        countDown = new CountDown(1000, 1000);


        countDown.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish() {
                textTimer.setText(simpleDateFormat.format(initialValue));
            }
        });

        countDown.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                textTimer.setText(simpleDateFormat.format(millisUntilFinished + initialValue));
            }
        });



        textTimer = findViewById(R.id.text_timer);
        textTimer.setText(simpleDateFormat.format(initialValue));

        buttonStart = findViewById(R.id.text_timer_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDown.setOnSecondsListener(new OnSetSecondsListener() {
                    @Override
                    public void onSetSeconds(long seconds) {
                        seconds = numberPicker.getValue();
                        setNumberPicker(seconds);
                    }
                });
                countDown.start();
            }
        });
    }

    private void initNumberPicker() {
        numberPicker.setMaxValue(24);
        numberPicker.setMinValue(0);
//        return numberPicker.getValue();
    }

    private void setNumberPicker(long value) {
        this.seconds = value;
        Log.d("mainactibiety", String.valueOf(numberPicker.getValue()));

    }
}


