/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.landing;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemReselectedListener;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.data.exception.EndOfWorldException;
import com.futureworkshops.codetest.android.domain.repositories.NetworkRepository;
import com.futureworkshops.codetest.android.presentation.breeds.favorite.FavoriteBreedsFragment;
import com.futureworkshops.codetest.android.presentation.breeds.list.BreedsListFragment;
import com.futureworkshops.codetest.android.presentation.notification.NotificationHelper;
import com.futureworkshops.codetest.android.viewmodel.landing.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements
    OnNavigationItemSelectedListener,
    OnNavigationItemReselectedListener {

  @BindView(R.id.bottomNavigation)
  BottomNavigationView bottomNavigationView;

  private BreedsListFragment breedsListFragment;
  private FavoriteBreedsFragment favoriteBreedsFragment;
  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    postponeEnterTransition();
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    this.viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    this.viewModel.initialise(NetworkRepository.getInstance());

    initBottomNavigation();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    this.getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.important_action:
        this.viewModel.getImportantAction().observe(this, resource -> {
          if (resource != null) {
            switch (resource.getResult()) {
              case endOfWorld:
                new NotificationHelper.Builder<EndOfWorldException>()
                    .with(this)
                    .from(resource.getThrowable())
                    .show();
                break;
            }
          }
        });
        break;

      default:
        return super.onOptionsItemSelected(item);
    }
    return false;
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_show_breeds:
        Timber.d("select breeds");
        showBreedsFragment();
        break;
      case R.id.action_show_favorites:
        Timber.d("select favorites");
        showFavoritesFragment();
        break;
    }

    return true;
  }

  @Override
  public void onNavigationItemReselected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_show_breeds:
        Timber.d("reselect breeds");
        break;
      case R.id.action_show_favorites:
        Timber.d("reselect favorites");
        break;
    }
  }

  private void showFavoritesFragment() {
    if (favoriteBreedsFragment == null) {
      favoriteBreedsFragment = FavoriteBreedsFragment.newInstance();
    }

    replaceFragment(favoriteBreedsFragment, "FAVORITES_ROOT");
  }

  private void showBreedsFragment() {
    if (breedsListFragment == null) {
      breedsListFragment = BreedsListFragment.newInstance();
    }

    replaceFragment(breedsListFragment, "BREEDS_ROOT");
  }

  private void replaceFragment(Fragment fragment, String tag) {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, fragment, tag)
        .commit();
  }

  private void initBottomNavigation() {
    final Menu menu = bottomNavigationView.getMenu();
    for (int i = 0; i < menu.size(); i++) {
      final MenuItem item = menu.getItem(i);

      if (item.getItemId() == R.id.action_show_breeds) {
        item.setChecked(true);
        bottomNavigationView.setSelectedItemId(item.getItemId());
        showBreedsFragment();
      }
    }

    bottomNavigationView.setOnNavigationItemSelectedListener(this);
    bottomNavigationView.setOnNavigationItemReselectedListener(this);
  }
}
