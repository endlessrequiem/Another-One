package austinwhite.tech.theapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mButton = findViewById(R.id.button);
        Button mrefresh = findViewById(R.id.refresh);
        final TextView mTxt = findViewById(R.id.text);
        final int[] a = {1};

        mButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int i = a[0]++;
                if (i == 1) {
                    mTxt.setText(getString(R.string.pressed) + " " + i + " " + getString(R.string.time));
                } else {
                    mTxt.setText(getString(R.string.pressed) + " " + i + " " + getString(R.string.times));
                }
            }
        });

        mrefresh.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
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