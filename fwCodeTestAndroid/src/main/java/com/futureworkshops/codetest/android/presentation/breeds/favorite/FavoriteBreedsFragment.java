/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import com.futureworkshops.codetest.android.presentation.breeds.details.BreedDetailsFragment;
import com.futureworkshops.codetest.android.presentation.breeds.view.BreedsAdapter;
import com.futureworkshops.codetest.android.presentation.breeds.view.OnItemSelectedHandler;

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

    private BreedsAdapter adapter;
    private BreedDetailsFragment breedDetailsFragment;

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
        this.favouritesListRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.adapter = new BreedsAdapter();
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

        FavouritesRepository repository = new FavouritesRepository();

        this.adapter.updateWith(repository.getAll());
    }

    @Override
    public void onItemSelected(ImageView breedPhotoImage, Breed item) {
        this.showBreedDetails(breedPhotoImage, item);
    }

    private void showBreedDetails(ImageView sharedElement, Breed selectedBreed) {
        if (this.breedDetailsFragment == null) {
            this.breedDetailsFragment = BreedDetailsFragment.newInstance(selectedBreed);
        }

        Transition changeTransform = TransitionInflater.from(this.getContext()).
            inflateTransition(R.transition.change_image_transform);
        Transition slideBottomTransform = TransitionInflater.from(this.getContext()).
            inflateTransition(android.R.transition.slide_bottom);

        Transition slideTopTransform = TransitionInflater.from(this.getContext()).
            inflateTransition(android.R.transition.slide_top);

        this.setSharedElementReturnTransition(changeTransform);
        this.setExitTransition(slideTopTransform);

        this.breedDetailsFragment.setSharedElementEnterTransition(changeTransform);
        this.breedDetailsFragment.setEnterTransition(slideBottomTransform);

        // Add second fragment by replacing first
        FragmentTransaction ft = this.getFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, this.breedDetailsFragment)
            .addToBackStack("transaction")
            .addSharedElement(sharedElement, "breeds");
        // Apply the transaction
        ft.commit();

    }
}
