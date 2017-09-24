package com.example.joelbakken.myexercises.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joelbakken.myexercises.Constants;
import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.models.Fitness;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FitnessDetailFragment extends Fragment implements View.OnClickListener {
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
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mFitness.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mFitness.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mFitness.getLatitude()
                            + "," + mFitness.getLongitude()
                            + "?q=(" + mFitness.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveFitnessButton) {
            DatabaseReference fitnessRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_FITNESS);
            fitnessRef.push().setValue(mFitness);
            Toast.makeText(getContext(), "Location Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
