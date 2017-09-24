package com.example.joelbakken.myexercises.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joelbakken.myexercises.Constants;
import com.example.joelbakken.myexercises.R;
import com.example.joelbakken.myexercises.adapters.FirebaseFitnessViewHolder;
import com.example.joelbakken.myexercises.models.Fitness;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedFitnessListActivity extends AppCompatActivity {
    private DatabaseReference mFitnessReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fitness);
        ButterKnife.bind(this);

        mFitnessReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FITNESS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Fitness, FirebaseFitnessViewHolder>
                (Fitness.class, R.layout.fitness_list_item, FirebaseFitnessViewHolder.class, mFitnessReference) {

            @Override
            protected void populateViewHolder(FirebaseFitnessViewHolder viewHolder, Fitness model, int position) {
                viewHolder.bindFitness(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
