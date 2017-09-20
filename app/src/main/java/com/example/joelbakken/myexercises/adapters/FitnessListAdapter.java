package com.example.joelbakken.myexercises.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.models.Fitness;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FitnessListAdapter extends RecyclerView.Adapter<FitnessListAdapter.FitnessViewHolder> {
    private ArrayList<Fitness> mFitness = new ArrayList<>();
    private Context mContext;

    public FitnessListAdapter(Context context, ArrayList<Fitness> fitnesses) {
        mContext = context;
        mFitness = fitnesses;
    }
    @Override
    public FitnessListAdapter.FitnessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fitness_list_item, parent, false);
        FitnessViewHolder viewHolder = new FitnessViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FitnessListAdapter.FitnessViewHolder holder, int position) {
        holder.bindFitness(mFitness.get(position));
    }

    @Override
    public int getItemCount() {
        return mFitness.size();
    }

    public class FitnessViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.fitnessImageView) ImageView mFitnessImageView;
        @Bind(R.id.fitnessNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public FitnessViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindFitness(Fitness fitness) {
            mNameTextView.setText(fitness.getName());
            mCategoryTextView.setText(fitness.getCategories().get(0));
            mRatingTextView.setText("Rating: " + fitness.getRating() + "/5");
        }
    }
}
