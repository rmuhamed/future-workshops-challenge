/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.repositories.FavouritesRepository;
import com.futureworkshops.codetest.android.presentation.breeds.list.view.BreedsListAdapter;
import com.futureworkshops.codetest.android.presentation.breeds.list.view.OnItemSelectedHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteBreedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteBreedsFragment extends Fragment implements OnItemSelectedHandler<Breed> {

    @BindView(R.id.favourite_list_recycler_view)
    RecyclerView favouritesListRecycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BreedsListAdapter adapter;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //breed's list recycler initialisation
        this.favouritesListRecycler.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        this.adapter = new BreedsListAdapter();
        this.adapter.addOnItemSelectedHandler(this);
        this.favouritesListRecycler.setAdapter(this.adapter);
        //toolbar initialisation
        ((AppCompatActivity) this.getActivity()).setSupportActionBar(this.toolbar);
        this.toolbar.setLogo(R.drawable.ic_favorite);
        ((AppCompatActivity) this.getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FavouritesRepository repository = new FavouritesRepository(this.getContext());

        repository.getAll();
    }

    @Override
    public void onItemSelected(ImageView breedPhotoImage, Breed item) {
        //TODO: RM - Same as BreedList
    }
}
