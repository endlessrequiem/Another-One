package austinwhite.tech.theapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button refresh = findViewById(R.id.refresh);
        final Button startGame = findViewById(R.id.startGame);
        final Button anotherOne = findViewById(R.id.anotherOne);
        anotherOne.setEnabled(false);
        refresh.setEnabled(false);



        final TextView instructions = findViewById(R.id.instructions);
        final TextView timer = findViewById(R.id.timer);

        final RadioButton easy = findViewById(R.id.easy);
        final RadioButton medium = findViewById(R.id.medium);
        final RadioButton hard = findViewById(R.id.hard);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numrange = 1;
                int min = 1;
                int time = 1;

                gameSet(numrange,
                        min,
                        time,
                        easy,
                        medium,
                        hard,
                        refresh,
                        startGame,
                        anotherOne,
                        instructions,
                        timer
                );


            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void gameSet(int numrange, int min, int time, final RadioButton easy, final RadioButton medium, final RadioButton hard, final Button refresh, final Button startGame, final Button anotherOne, final TextView instructions, final TextView timer) {
        if (easy.isChecked()) {
            numrange = 100;
            min = 1;
            time = 30000;
            medium.setEnabled(false);
            hard.setEnabled(false);
            anotherOne.setEnabled(true);

        } else if (medium.isChecked()) {
            numrange = 200;
            min = 100;
            time = 45000;
            hard.setEnabled(false);
            easy.setEnabled(false);
            anotherOne.setEnabled(true);


        } else if (hard.isChecked()) {
            numrange = 300;
            min = 200;
            time = 60000;
            easy.setEnabled(false);
            medium.setEnabled(false);
            anotherOne.setEnabled(true);

        } else {
            instructions.setText(getString(R.string.setDifficulty));
        }

            if (numrange > 1) {
                final int randnumber = ThreadLocalRandom.current().nextInt(min, numrange + 1);
                instructions.setText(getString(R.string.your_number) + " " + randnumber);


                final TextView mTxt = findViewById(R.id.YouPressedText);
                final int[] a = {1};


                final CountDownTimer countDown = new CountDownTimer(time, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        mTxt.setText(getString(R.string.youLose));
                        anotherOne.setEnabled(false);
                        refresh.setEnabled(true);

                    }

                }.start();

                startGame.setEnabled(false);


                final int finalNumrange = numrange;
                final int finalMin = min;
                final int finalTime = time;
                anotherOne.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        int i = a[0]++;
                        if (i == randnumber) {
                            countDown.cancel();
                            anotherOne.setEnabled(false);
                            refresh.setEnabled(true);
                            gameSet(finalNumrange,
                                    finalMin,
                                    finalTime,
                                    easy,
                                    medium,
                                    hard,
                                    refresh,
                                    startGame,
                                    anotherOne,
                                    instructions,
                                    timer
                            );

                        } else {
                            mTxt.setText(String.valueOf(i));
                        }
                    }
                });

            } else {
                instructions.setText(getString(R.string.setDifficulty));

            }
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        }

    public void restart() {
        finish();
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

}