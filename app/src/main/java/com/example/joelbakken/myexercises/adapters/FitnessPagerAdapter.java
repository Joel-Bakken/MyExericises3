package com.example.joelbakken.myexercises.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.joelbakken.myexercises.models.Fitness;
import com.example.joelbakken.myexercises.ui.FitnessDetailFragment;

import java.util.ArrayList;

public class FitnessPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fitness> mFitnesss;

    public FitnessPagerAdapter(FragmentManager fm, ArrayList<Fitness> fitnesses) {
        super(fm);
        mFitnesss = fitnesses;
    }

    @Override
    public Fragment getItem(int position) {
        return FitnessDetailFragment.newInstance(mFitnesss.get(position));
    }

    @Override
    public int getCount() {
        return mFitnesss.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFitnesss.get(position).getName();
    }
}