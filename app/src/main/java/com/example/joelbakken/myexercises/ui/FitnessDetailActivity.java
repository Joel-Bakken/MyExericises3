package com.example.joelbakken.myexercises.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.adapters.FitnessPagerAdapter;
import com.example.joelbakken.myexercises.models.Fitness;



import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FitnessDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private FitnessPagerAdapter adapterViewPager;
    ArrayList<Fitness> mFitnesss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_detail);
        ButterKnife.bind(this);

        mFitnesss = Parcels.unwrap(getIntent().getParcelableExtra("fitnesses"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new FitnessPagerAdapter(getSupportFragmentManager(), mFitnesss);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}