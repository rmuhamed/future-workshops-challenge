/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.details;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.domain.model.Breed;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BreedDetailsFragment extends Fragment {

    private static final String ARG_BREED = "breed";

    @BindView(R.id.title)
    TextView breedNameLabel;

    @BindView(R.id.description)
    TextView breedDescriptionLabel;

    @BindView(R.id.image)
    ImageView breedImage;

    public BreedDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param breed Parameter 1.
     * @return A new instance of fragment BreedDetailsFragment.
     */
    public static BreedDetailsFragment newInstance(Breed breed) {
        BreedDetailsFragment fragment = new BreedDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BREED, breed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (this.getArguments() != null) {
            Breed breed = this.getArguments().getParcelable(ARG_BREED);
            this.breedNameLabel.setText(breed.name());
            this.breedDescriptionLabel.setText(breed.description());

            Glide.with(this.breedImage.getContext())
                .load(breed.photoUrl())
                .into(this.breedImage);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_breed_details, container, false);

        ButterKnife.bind(this, view);

        return view;
    }
}
