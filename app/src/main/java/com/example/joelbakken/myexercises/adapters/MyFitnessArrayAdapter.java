package com.example.joelbakken.myexercises.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;


public class MyFitnessArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFitness;
    private String[] mCategory;

    public MyFitnessArrayAdapter(Context mContext, int resource, String[] mFitness, String[] mCategory) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFitness = mFitness;
        this.mCategory = mCategory;
    }

    @Override
    public Object getItem(int position) {
        String fitness = mFitness[position];
        String category = mCategory[position];
        return String.format("%s \n %s", fitness, category);
    }

    @Override
    public int getCount() {
        return mFitness.length;
    }
}
