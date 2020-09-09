package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



        TextView timerText;
        SeekBar timerBar;
        Boolean counterIsActive = false;
        Button goButton;
        CountDownTimer countDownTimer;


    public void resetTimer(){

        timerText.setText("0:30");
        timerBar.setProgress(30);
        timerBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go");
        counterIsActive = false;

    }



    public void buttonClick(View view){

        if (counterIsActive) {

            resetTimer();


        } else {

            counterIsActive = true;
            timerBar.setEnabled(false);
            goButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.rooster);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }

    }


    public void updateTimer(int secondsLeft){

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        timerText.setText(Integer.toString(minutes) + ":" + secondString);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerText = (TextView) findViewById(R.id.timer);
        timerBar = (SeekBar) findViewById(R.id.timerBar);
        goButton = findViewById(R.id.activateButton);

        timerBar.setMax(600);
        timerBar.setProgress(30);

        timerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            updateTimer(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*
        activateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){



                CountDownTimer eggTimer = new CountDownTimer(30000, 1000) {
                    public void onTick(long millisUntilFinished) {

                       if(isStopped) {
                           timerText.setText(String.valueOf(counter));
                           timerBar.setProgress(counter);
                           activateButton.setText("Stop");
                           isStopped = false;
                           counter--;

                       } else {
                           timerText.setText(String.valueOf(counter));
                           activateButton.setText("Go");

                           cancel();

                       }




                    }

                    public void onFinish() {
                        timerText.setText("Done");
                    }
                }.start();
            }
        });
        */

    }
}