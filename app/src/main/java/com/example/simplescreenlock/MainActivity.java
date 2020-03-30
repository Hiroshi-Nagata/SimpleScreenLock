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

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                seconds = numberPicker.getValue() * 1000;
                countDown = new CountDown(seconds, 1000);

                countDown.setOnFinishListener(new OnFinishListener() {
                    @Override
                    public void onFinish() {
                        textTimer.setText(simpleDateFormat.format(initialValue));
                    }
                });

                countDown.setOnTickListener(new OnTickListener() {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        textTimer.setText(simpleDateFormat.format(millisUntilFinished + initialValue + seconds));
                    }
                });

                textTimer = findViewById(R.id.text_timer);
                textTimer.setText(simpleDateFormat.format(initialValue));

                buttonStart = findViewById(R.id.text_timer_start);
                buttonStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countDown.start();
                    }
                });
            }
        });
    }

    private void initNumberPicker() {
        numberPicker.setMaxValue(24);
        numberPicker.setMinValue(0);
        numberPicker.setValue(5);
//        return numberPicker.getValue();
    }

    private long getNumberPicker(long value) {
        return value;
    }

    private void setNumberPicker(long value) {
        this.seconds = value;
    }
}


