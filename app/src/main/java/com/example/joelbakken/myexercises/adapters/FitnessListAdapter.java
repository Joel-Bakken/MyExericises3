package com.example.joelbakken.myexercises.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.models.Fitness;
import com.example.joelbakken.myexercises.ui.FitnessDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class FitnessListAdapter extends RecyclerView.Adapter<FitnessListAdapter.FitnessViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Fitness> mFitness = new ArrayList<>();
    private Context mContext;

    public FitnessListAdapter(Context context, ArrayList<Fitness> fitnesses) {
        mContext = context;
        mFitness = fitnesses;
    }
    @Override
    public FitnessListAdapter.FitnessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG, "I'm in the fitness list adapter");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fitness_list_item, parent, false);
        FitnessViewHolder viewHolder = new FitnessViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FitnessListAdapter.FitnessViewHolder holder, int position) {
        Log.v(TAG, "BindViewHolder Activated");
        holder.bindFitness(mFitness.get(position));
    }

    @Override
    public int getItemCount() {
        return mFitness.size();
    }

    public class FitnessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.fitnessImageView) ImageView mFitnessImageView;
        @Bind(R.id.fitnessNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public FitnessViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindFitness(Fitness fitness) {
            Picasso.with(mContext).load(fitness.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mFitnessImageView);
            mNameTextView.setText(fitness.getName());
            mCategoryTextView.setText(fitness.getCategories().get(0));
            mRatingTextView.setText("Rating: " + fitness.getRating() + "/5");
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, FitnessDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("fitnesses", Parcels.wrap(mFitness));
            mContext.startActivity(intent);
        }
    }
}
