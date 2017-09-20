package com.example.joelbakken.myexercises.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.adapters.FitnessListAdapter;
import com.example.joelbakken.myexercises.models.Fitness;
import com.example.joelbakken.myexercises.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FitnessListActivity extends AppCompatActivity {
    public static final String TAG = FitnessListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private FitnessListAdapter mAdapter;

    public ArrayList<Fitness> mFitness = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

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

                FitnessListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new FitnessListAdapter(getApplicationContext(), mFitness);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FitnessListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}