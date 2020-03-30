package com.example.simplescreenlock;

import android.os.CountDownTimer;

public class CountDown extends CountDownTimer implements OnSetSecondsListener{

    OnFinishListener onFinishListener;
    OnTickListener onTickListener;
    OnSetSecondsListener onSetSecondsListener;

    public CountDown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void onSetSeconds(long seconds) {
        if (onSetSecondsListener != null) onSetSecondsListener.onSetSeconds(seconds);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (onTickListener != null) onTickListener.onTick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        if (onFinishListener != null) onFinishListener.onFinish();
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public void setOnTickListener(OnTickListener onTickListener) {
        this.onTickListener = onTickListener;
    }

    public void setOnSecondsListener(OnSetSecondsListener onSetSecondsListener) {
        this.onSetSecondsListener = onSetSecondsListener;
    }
}
