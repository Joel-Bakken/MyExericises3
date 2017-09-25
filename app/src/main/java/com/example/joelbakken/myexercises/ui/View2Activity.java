package com.example.joelbakken.myexercises.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.joelbakken.myexercises.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class View2Activity extends AppCompatActivity {
    @Bind(R.id.exerciseLog) TextView mExerciseLog;
    @Bind(R.id.logButton) Button mLogButton;
    @Bind(R.id.aboutButton) Button mAboutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra("exerciseName");
        mExerciseLog.setText("You did the following exercise: " + exerciseName);

        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(View2Activity.this, LogActivity.class);
                startActivity(intent);
            }
        });

        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View2Activity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

    }
}
