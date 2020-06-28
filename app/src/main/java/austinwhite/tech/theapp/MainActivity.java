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


        Button mrefresh = findViewById(R.id.refresh);
        final Button startGame = findViewById(R.id.startGame);
        final Button mButton = findViewById(R.id.button);
        mButton.setEnabled(false);


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

                if (easy.isChecked()) {
                    numrange = 100;
                    min = 1;
                    time = 30000;
                    medium.setEnabled(false);
                    hard.setEnabled(false);
                    mButton.setEnabled(true);

                } else if (medium.isChecked()) {
                    numrange = 500;
                    min = 100;
                    time = 45000;
                    hard.setEnabled(false);
                    easy.setEnabled(false);
                    mButton.setEnabled(true);


                } else if (hard.isChecked()) {
                    numrange = 1000;
                    min = 500;
                    time = 60000;
                    easy.setEnabled(false);
                    medium.setEnabled(false);
                    mButton.setEnabled(true);

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
                            mButton.setEnabled(false);

                        }

                    }.start();
                    startGame.setEnabled(false);


                    mButton.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View view) {
                            int i = a[0]++;
                            if (i == randnumber) {
                                mTxt.setText(getString(R.string.goalreached));
                                countDown.cancel();
                                mButton.setEnabled(false);
                            } else {
                                mTxt.setText(String.valueOf(i));
                            }
                        }
                    });

                } else {
                    instructions.setText(getString(R.string.setDifficulty));

                }

            }
        });

        mrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });


    }





}