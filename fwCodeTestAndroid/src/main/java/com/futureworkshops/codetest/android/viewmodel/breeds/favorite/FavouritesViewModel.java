package com.futureworkshops.codetest.android.viewmodel.breeds.favorite;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.repositories.FavouritesRepository;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;
import com.futureworkshops.codetest.android.domain.repositories.utils.FavouritesConverter;

import java.util.List;

/**
 * Created by romh on 2018-11-17
 */
public class FavouritesViewModel extends ViewModel {

  private FavouritesRepository repository;
  private MutableLiveData<List<Breed>> liveData;

  public void initialise(FavouritesRepository repository) {
    this.repository = repository;
  }

  public LiveData<List<Breed>> getFavouriteBreeds() {
    if (this.liveData == null) {
      this.liveData = new MutableLiveData<>();
    }

    this.loadFavouritesBreeds();

    return this.liveData;
  }

  private void loadFavouritesBreeds() {
    List<Favourite> favourites = this.repository.getAll();

    this.liveData.postValue(FavouritesConverter.convertFrom(favourites));
  }


}
