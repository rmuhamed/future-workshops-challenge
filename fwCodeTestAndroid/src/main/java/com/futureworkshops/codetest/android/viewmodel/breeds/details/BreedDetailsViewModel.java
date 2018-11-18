package com.futureworkshops.codetest.android.viewmodel.breeds.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.model.BreedStats;
import com.futureworkshops.codetest.android.domain.model.Resource;
import com.futureworkshops.codetest.android.domain.repositories.BreedsRepository;
import com.futureworkshops.codetest.android.domain.repositories.FavouritesRepository;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;
import com.futureworkshops.codetest.android.domain.repositories.utils.BreedStatsConverter;
import com.futureworkshops.codetest.android.domain.repositories.utils.FavouritesConverter;

/**
 * Created by romh on 2018-11-17
 */
public class BreedDetailsViewModel extends ViewModel {

  private FavouritesRepository favouritesRepository;
  private BreedsRepository breedsRepository;
  private MutableLiveData<Resource<BreedStats, Throwable>> liveData;

  public void initialise(FavouritesRepository favouritesRepository, BreedsRepository breedsRepository) {
    this.favouritesRepository = favouritesRepository;
    this.breedsRepository = breedsRepository;
  }

  public void saveAsFavourite(Breed breedToSaved) {
    this.favouritesRepository.save(FavouritesConverter.map(breedToSaved));
  }

  public void eraseAsFavourite(Breed breedToBeErased) {
    this.favouritesRepository.delete(FavouritesConverter.map(breedToBeErased));
  }

  public boolean alreadyAFavourite(Breed breed) {
    Favourite storedFavourite = this.favouritesRepository.getBy(breed.id());

    return storedFavourite != null
        && FavouritesConverter.toOne(storedFavourite).equals(breed);
  }

  public LiveData<Resource<BreedStats, Throwable>> getBreedStats(Breed aBreed) {
    if (this.liveData == null) {
      this.liveData = new MutableLiveData<>();
    }

    this.retrieveBreedStats(aBreed);

    return this.liveData;
  }

  private void retrieveBreedStats(Breed aBreed) {
    if (aBreed != null) {
      this.breedsRepository.getStatsFor(aBreed.id())
          .doOnSuccess(breedStatsDto -> this.notifySuccessful(BreedStatsConverter.from(breedStatsDto)))
          .doOnError(this::notifyError)
          .subscribe();
    }
  }

  private void notifyError(Throwable error) {
    this.liveData.postValue(this.buildResource(Resource.status.error, null, error));
  }

  private void notifySuccessful(BreedStats stats) {
    this.liveData.postValue(this.buildResource(Resource.status.successful, stats, null));
  }

  private Resource<BreedStats, Throwable> buildResource(Resource.status status, BreedStats stats, Throwable throwable) {
    return new Resource.Builder<>()
        .result(status)
        .throwable(throwable)
        .data(stats)
        .build();
  }
}
