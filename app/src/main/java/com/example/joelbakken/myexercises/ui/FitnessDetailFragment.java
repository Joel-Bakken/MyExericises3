package com.example.joelbakken.myexercises.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.models.Fitness;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FitnessDetailFragment extends Fragment {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.fitnessImageView) ImageView mImageLabel;
    @Bind(R.id.fitnessNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveFitnessButton) TextView mSaveFitnessButton;

    private Fitness mFitness;

    public static FitnessDetailFragment newInstance(Fitness fitness) {
        FitnessDetailFragment fitnessDetailFragment = new FitnessDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("fitness", Parcels.wrap(fitness));
        fitnessDetailFragment.setArguments(args);
        return fitnessDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFitness = Parcels.unwrap(getArguments().getParcelable("fitness"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fitness_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mFitness.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mImageLabel);

        mNameLabel.setText(mFitness.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mFitness.getCategories()));
        mRatingLabel.setText(Double.toString(mFitness.getRating()) + "/5");
        mPhoneLabel.setText(mFitness.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mFitness.getAddress()));

        return view;
    }
}