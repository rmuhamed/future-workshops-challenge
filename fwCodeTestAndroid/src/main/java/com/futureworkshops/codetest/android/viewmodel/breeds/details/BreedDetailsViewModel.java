package com.futureworkshops.codetest.android.viewmodel.breeds.details;

import android.arch.lifecycle.ViewModel;

import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.repositories.FavouritesRepository;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;
import com.futureworkshops.codetest.android.domain.repositories.utils.FavouritesConverter;

/**
 * Created by romh on 2018-11-17
 */
public class BreedDetailsViewModel extends ViewModel {

  private FavouritesRepository favouritesRepository;

  public void initialise(FavouritesRepository favouritesRepository) {
    this.favouritesRepository = favouritesRepository;
  }

  public void saveAsFavourite(Breed breedToSaved) {
    this.favouritesRepository.save(FavouritesConverter.map(breedToSaved));
  }

  public void eraseAsFavourite(Breed breedToBeErased) {
    this.favouritesRepository.delete(FavouritesConverter.map(breedToBeErased));
  }

  public boolean alreadyAFavourite(Breed breed) {
    Favourite storedFavourite = this.favouritesRepository.getBy(breed.id());

    boolean alreadyPresent = storedFavourite != null
        && FavouritesConverter.toOne(storedFavourite).equals(breed);

    return alreadyPresent;
  }
}
