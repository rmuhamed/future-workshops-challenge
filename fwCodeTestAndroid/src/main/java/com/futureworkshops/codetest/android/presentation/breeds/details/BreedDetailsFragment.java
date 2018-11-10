/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.futureworkshops.codetest.android.domain.model.Breed;


public class BreedDetailsFragment extends Fragment {

    private static final String ARG_BREED = "breed";

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


}
