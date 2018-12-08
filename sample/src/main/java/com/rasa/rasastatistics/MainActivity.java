package com.rasa.rasastatistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rasa.statistics.Statistics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Statistics(this,2).active("09126421759","123","456");

    }
}
