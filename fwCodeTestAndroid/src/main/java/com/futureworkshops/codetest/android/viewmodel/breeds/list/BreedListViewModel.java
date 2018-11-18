package com.futureworkshops.codetest.android.viewmodel.breeds.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.model.Resource;
import com.futureworkshops.codetest.android.domain.repositories.BreedsRepository;
import com.futureworkshops.codetest.android.domain.repositories.utils.BreedConverter;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by romh on 2018-11-17
 */
public class BreedListViewModel extends ViewModel {

  private BreedsRepository repository;
  private MutableLiveData<Resource<List<Breed>, Throwable>> liveData;

  public void initialise(BreedsRepository repository) {
    this.repository = repository;
  }

  public LiveData<Resource<List<Breed>, Throwable>> getBreeds() {
    if (this.liveData == null) {
      this.liveData = new MutableLiveData<>();
    }

    this.retrieveBreeds();

    return this.liveData;
  }

  private void retrieveBreeds() {
    this.repository.getBreeds()
        .doOnSuccess(breedDtoList -> Observable.fromIterable(breedDtoList).map(BreedConverter::from)
            .toList()
            .doOnSuccess(this::notifySuccessful)
            .subscribe())
        .doOnError(this::notifyError)
        .subscribe();
  }

  private void notifyError(Throwable error) {
    this.liveData.postValue(this.buildResource(Resource.status.error, null, error));
  }

  private void notifySuccessful(List<Breed> breeds) {
    this.liveData.postValue(this.buildResource(Resource.status.successful, breeds, null));
  }

  private Resource<List<Breed>, Throwable> buildResource(Resource.status status, List<Breed> breeds, Throwable throwable) {
    return new Resource.Builder<>()
        .result(status)
        .throwable(throwable)
        .data(breeds)
        .build();
  }

}
