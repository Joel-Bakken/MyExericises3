package com.example.joelbakken.myexercises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FitnessActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] fitness = new String[] {"Anytime Fitness", "24 Hour Fitness", "OFIT Gym", "Seattle Fitness", "Mode of Fitness"};
    private String[] category = new String[] {"Gyms, Trainers", "Trainers, Gyms", "Trainers, Gyms, Pilates", "Gyms, Trainers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fitness);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fitness = ((TextView)view).getText().toString();
                Toast.makeText(FitnessActivity.this, fitness, Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("locationEditText");

        mLocationTextView.setText("Here are all the fitness locations near: " + location);
    }
}

