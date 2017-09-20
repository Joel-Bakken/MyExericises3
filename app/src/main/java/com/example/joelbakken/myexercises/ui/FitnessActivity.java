package com.example.joelbakken.myexercises.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.models.Fitness;
import com.example.joelbakken.myexercises.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FitnessActivity extends AppCompatActivity {
    public static final String TAG = FitnessActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Fitness> mFitness = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mLocationTextView.setText("Here are all the fitness locations near: " + location);

        getFitness(location);
    }

    private void getFitness(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findFitness(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mFitness = yelpService.processResults(response);

                FitnessActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] fitnessNames = new String[mFitness.size()];
                        for (int i = 0; i < fitnessNames.length; i++) {
                            fitnessNames[i] = mFitness.get(i).getName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(FitnessActivity.this, android.R.layout.simple_list_item_1, fitnessNames);
                        mListView.setAdapter(adapter);

                        for (Fitness fitness : mFitness) {
                            Log.d(TAG, "Name: " + fitness.getName());
                            Log.d(TAG, "Phone: " + fitness.getPhone());
                            Log.d(TAG, "Website: " + fitness.getWebsite());
                            Log.d(TAG, "Image url: " + fitness.getImageUrl());
                            Log.d(TAG, "Rating: " + Double.toString(fitness.getRating()));
                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", fitness.getAddress()));
                            Log.d(TAG, "Categories: " + fitness.getCategories().toString());
                        }
                    }
                });
            }
        });
    }
}