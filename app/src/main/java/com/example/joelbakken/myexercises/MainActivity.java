package com.example.joelbakken.myexercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.joelbakken.myexercises.R.id.locationEditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.logButton) Button mLogButton;
    @Bind(R.id.viewButton) Button mViewButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.findFitnessButton) Button mFindFitnessButton;
    @Bind(locationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });

        mViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });

        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        mFindFitnessButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, FitnessActivity.class);
            intent.putExtra("locationEditText", location);
            if (location.equals("")) {
                Toast.makeText(MainActivity.this, "Please fill out all fields", Toast.LENGTH_LONG).show();
            } else {
                startActivity(intent);
            }
        }
    }
