/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.repositories.BreedsRepository;
import com.futureworkshops.codetest.android.presentation.breeds.details.BreedDetailsFragment;
import com.futureworkshops.codetest.android.presentation.breeds.view.BreedsAdapter;
import com.futureworkshops.codetest.android.presentation.breeds.view.OnItemSelectedHandler;
import com.futureworkshops.codetest.android.presentation.notification.NotificationHelper;
import com.futureworkshops.codetest.android.viewmodel.breeds.list.BreedListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BreedsListFragment extends Fragment implements OnItemSelectedHandler<Breed> {
  public static final String RESOURCE_IS_NULL = "Resource is null";
  private BreedDetailsFragment breedDetailsFragment;

  private static final String BASE_URL = "BASE_URL";
  private BreedsAdapter adapter;

  @BindView(R.id.breed_list_recycler_view)
  RecyclerView breedListRecycler;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private BreedListViewModel viewModel;

  public static BreedsListFragment newInstance(String baseURL) {
    Bundle args = new Bundle();

    BreedsListFragment listFragment = new BreedsListFragment();
    listFragment.setArguments(args);

    return listFragment;
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.fragment_breed_list, container, false);

    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //breed's list recycler initialisation
    this.breedListRecycler.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
    this.adapter = new BreedsAdapter();
    this.adapter.addOnItemSelectedHandler(this);
    this.breedListRecycler.setAdapter(this.adapter);
    //toolbar initialisation
    ((AppCompatActivity) this.getActivity()).setSupportActionBar(this.toolbar);
    this.toolbar.setLogo(R.drawable.ic_dog);
    ((AppCompatActivity) this.getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    this.viewModel = ViewModelProviders.of(this).get(BreedListViewModel.class);
    this.viewModel.initialise(new BreedsRepository());
    this.viewModel.getBreeds().observe(this.getViewLifecycleOwner(), resource -> {
      if (resource != null) {
        switch (resource.getResult()) {
          case error:
            new NotificationHelper.Builder()
                .with(this.getContext())
                .from(resource.getThrowable())
                .show();
            break;
          case successful:
            this.adapter.updateWith(resource.getData());
            break;
        }
      } else {
        new NotificationHelper.Builder()
            .with(this.getContext())
            .from(new NullPointerException(RESOURCE_IS_NULL))
            .show();
      }
    });
  }

  @Override
  public void onItemSelected(ImageView sharedElement, Breed selectedBreed) {
    this.showBreedDetails(sharedElement, selectedBreed);
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