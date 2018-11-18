/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.details;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.model.BreedStats;
import com.futureworkshops.codetest.android.domain.model.Resource;
import com.futureworkshops.codetest.android.domain.repositories.BreedsRepository;
import com.futureworkshops.codetest.android.domain.repositories.FavouritesRepository;
import com.futureworkshops.codetest.android.presentation.breeds.BundleUtils;
import com.futureworkshops.codetest.android.presentation.notification.NotificationHelper;
import com.futureworkshops.codetest.android.viewmodel.breeds.details.BreedDetailsViewModel;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BreedDetailsFragment extends Fragment {
  public static final String ARG_BREED = "breed";
  public static final String FORMAT = "%d";

  @BindView(R.id.title)
  TextView breedNameLabel;

  @BindView(R.id.description)
  TextView breedDescriptionLabel;

  @BindView(R.id.image)
  ImageView breedImage;

  @BindView(R.id.add_as_favourite)
  FloatingActionButton addAsFavouriteFab;

  @BindView(R.id.stats)
  FloatingActionButton statsButton;

  private BreedDetailsViewModel viewModel;
  private Breed breed;
  private boolean isFavourite;
  private BreedStats stats;

  public BreedDetailsFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment BreedDetailsFragment.
   */
  public static BreedDetailsFragment newInstance() {
    return new BreedDetailsFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_breed_details, container, false);

    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    this.breed = new BundleUtils().unpackExtras(ARG_BREED, this.getArguments());

    if (this.breed != null) {
      this.paintFrom(this.breed);

      this.favouriteButtonHandler();

      this.statsButtonHandler();
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    this.viewModel = ViewModelProviders.of(this).get(BreedDetailsViewModel.class);
    this.viewModel.initialise(new FavouritesRepository(), new BreedsRepository());

    this.viewModel.getBreedStats(this.breed)
        .observe(this.getViewLifecycleOwner(), this::onStatsRetrieved);
    this.isFavourite = this.viewModel.alreadyAFavourite(this.breed);
    //Update FAB based on Favourite existence status
    this.updateFABimage();
  }

  private void addFavourite(View view) {
    this.viewModel.saveAsFavourite((Breed) view.getTag(R.id.breed));
    this.isFavourite = true;

    Snackbar.make(view, R.string.favourite_saved, Toast.LENGTH_SHORT).show();
  }

  private void removeFavourite(View view) {
    this.viewModel.eraseAsFavourite((Breed) view.getTag(R.id.breed));
    this.isFavourite = false;

    Snackbar.make(view, R.string.favourite_deleted, Toast.LENGTH_SHORT).show();
  }

  private void paintFrom(Breed breed) {
    this.breedNameLabel.setText(breed.name());
    this.breedDescriptionLabel.setText(breed.description());

    Glide.with(this.breedImage.getContext())
        .load(breed.photoUrl())
        .into(this.breedImage);
  }

  private void favouriteButtonHandler() {
    this.addAsFavouriteFab.setTag(R.id.breed, this.breed);
    this.addAsFavouriteFab.setOnClickListener(v -> {
      if(this.isFavourite) {
        this.removeFavourite(v);
      } else{
        this.addFavourite(v);
      }

      this.animateFAB();
      this.updateFABimage();
    });
  }

  private void animateFAB() {
    @AnimRes int animResource = this.isFavourite ? R.anim.scale_down : R.anim.scale_up;

    this.addAsFavouriteFab.startAnimation(AnimationUtils.loadAnimation(this.getContext(), animResource));
  }

  private void updateFABimage() {
    this.addAsFavouriteFab.setImageResource(
        this.isFavourite
            ? R.drawable.ic_heart_broken
            : R.drawable.ic_heart_white);
  }

  private void onStatsRetrieved(Resource<BreedStats, Throwable> resource) {
    if (resource != null) {
      switch (resource.getResult()) {
        case successful:
          this.stats = resource.getData();
          break;

        case error:
          //Nothing to do
          break;
      }
    }
  }

  private void statsButtonHandler() {
    this.statsButton.setOnClickListener(v -> {
      if (this.stats != null) {
        this.presentStats(this.stats);
      } else {
        new NotificationHelper.Builder<NullPointerException>()
            .with(this.getContext())
            .from(new NullPointerException("No Stats info"))
            .show();
      }
    });
  }

  private void presentStats(BreedStats stats) {

    View dialogView = LayoutInflater.from(this.getContext())
        .inflate(R.layout.dialog_stats, (ViewGroup) this.getView(), false);

    ((TextView) dialogView.findViewById(R.id.trainability_label))
        .setText(String.format(Locale.getDefault(),FORMAT, stats.trainability()));
    ((TextView) dialogView.findViewById(R.id.adaptability_label))
        .setText(String.format(Locale.getDefault(),FORMAT, stats.adaptability()));
    ((TextView) dialogView.findViewById(R.id.friendliness_label))
        .setText(String.format(Locale.getDefault(),FORMAT, stats.friendliness()));
    ((TextView) dialogView.findViewById(R.id.grooming_label))
        .setText(String.format(Locale.getDefault(),FORMAT, stats.grooming()));
    ((TextView) dialogView.findViewById(R.id.excerise_needs_label))
        .setText(String.format(Locale.getDefault(), FORMAT, stats.exerciseNeeds()));

    new AlertDialog.Builder(this.getContext())
        .setView(dialogView)
        .setCancelable(true)
        .create()
        .show();
  }
}
