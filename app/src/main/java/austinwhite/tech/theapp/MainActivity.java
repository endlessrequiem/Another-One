package austinwhite.tech.theapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button refresh = findViewById(R.id.refresh);
        final Button startGame = findViewById(R.id.startGame);
        final Button anotherOne = findViewById(R.id.anotherOne);

        anotherOne.setVisibility(View.GONE);
        refresh.setVisibility(View.GONE);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anotherOne.setVisibility(View.VISIBLE);
                refresh.setVisibility(View.VISIBLE);
                refresh.setEnabled(false);
                int max = 1;
                int min = 1;
                int time = 1;

                gameSet(max,
                        min,
                        time,
                        anotherOne,
                        refresh,
                        startGame
                );


            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void gameSet(int max, int min, int time, final Button anotherOne, final Button refresh, final Button startGame) {


        final int[] cyclesArr = {1};


        final TextView instructions = findViewById(R.id.instructions);
        final TextView timer = findViewById(R.id.timer);
        final TextView cycles = findViewById(R.id.cyclesView);
        final TextView anotherPress = findViewById(R.id.clicks);


        final RadioButton easy = findViewById(R.id.easy);
        final RadioButton medium = findViewById(R.id.medium);
        final RadioButton hard = findViewById(R.id.hard);

        if (easy.isChecked()) {
            max = 100;
            min = 1;
            time = 30000;
            medium.setEnabled(false);
            hard.setEnabled(false);
            anotherOne.setEnabled(true);
            easy.setVisibility(View.GONE);
            medium.setVisibility(View.GONE);
            hard.setVisibility(View.GONE);
            startGame.setVisibility(View.GONE);

        } else if (medium.isChecked()) {
            max = 200;
            min = 100;
            time = 45000;
            hard.setEnabled(false);
            easy.setEnabled(false);
            anotherOne.setEnabled(true);
            easy.setVisibility(View.GONE);
            medium.setVisibility(View.GONE);
            hard.setVisibility(View.GONE);
            startGame.setVisibility(View.GONE);


        } else if (hard.isChecked()) {
            max = 300;
            min = 200;
            time = 60000;
            easy.setEnabled(false);
            medium.setEnabled(false);
            anotherOne.setEnabled(true);
            easy.setVisibility(View.GONE);
            medium.setVisibility(View.GONE);
            hard.setVisibility(View.GONE);
            startGame.setVisibility(View.GONE);

        } else {
            Toast.makeText(this, getString(R.string.setDifficulty), Toast.LENGTH_SHORT).show();

        }

            if (max > 1) {
                final int randnumber = (int) ((Math.random() * (max - min)) + min);
                instructions.setText(getString(R.string.your_number) + " " + randnumber);


                final int[] score = {1};



                final CountDownTimer countDown = new CountDownTimer(time, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        anotherPress.setText(getString(R.string.youLose));
                        anotherOne.setEnabled(false);
                        refresh.setEnabled(true);
                        easy.setEnabled(false);
                        medium.setEnabled(false);
                        hard.setEnabled(false);
                    }

                }.start();

                startGame.setEnabled(false);


                final int finalMax = max;
                final int finalMin = min;
                final int finalTime = time;
                anotherOne.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        int i = score[0]++;
                        if (i == randnumber) {
                            countDown.cancel();
                            anotherPress.setText(String.valueOf(i));
                            anotherOne.setEnabled(false);
                            gameSet(finalMax,
                                    finalMin,
                                    finalTime,
                                    anotherOne,
                                    refresh,
                                    startGame);
                            cycleCounter(cycles, cyclesArr);


                        } else {
                            anotherPress.setText(String.valueOf(i));
                        }
                    }
                });

            } else {
                Log.i("Debug Check", "Else Statement from anotherOne.onClickListener"); //for debug purposes

            }

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        }

    @SuppressLint("SetTextI18n")
    private void cycleCounter(TextView cycles, int[] cyclesArr) {
        int counterCycle = cyclesArr[0]++;
        cycles.setText(getString(R.string.cycle) + " " + counterCycle);
    }

    public void restart() {
        finish();
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

}