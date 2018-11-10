/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.futureworkshops.codetest.android.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteBreedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteBreedsFragment extends Fragment {


    public static FavoriteBreedsFragment newInstance() {

        return new FavoriteBreedsFragment();
    }

    public FavoriteBreedsFragment() {
        // Required empty public constructor
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_favorite_breeds, container, false);
       
        ButterKnife.bind(this, view);
        
        return view;
    }
    
}
