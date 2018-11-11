package com.futureworkshops.codetest.android.domain.repositories;

import com.futureworkshops.codetest.android.data.network.dto.BreedDto;
import com.futureworkshops.codetest.android.domain.model.Breed;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by romh on 2018-11-11
 */
public class BreedsRepository  {
  private final NetworkRepository networkRepository;

  public BreedsRepository() {
    this.networkRepository = NetworkRepository.getInstance();
  }

  public void getBreeds(Consumer<List<Breed>> successConsumer, Consumer<Throwable> errorConsumer) {
    this.networkRepository.getRestManager().getBreeds()
        .doOnSuccess(breedDtoList -> Observable.fromIterable(breedDtoList).map(this::mapFrom)
            .toList()
            .doOnSuccess(successConsumer)
            .subscribe())
        .doOnError(errorConsumer)
        .subscribe();
  }

  private Breed mapFrom(BreedDto aBreedDto) {
    return Breed.builder()
        .id(aBreedDto.getId())
        .name(aBreedDto.getName())
        .photoUrl(aBreedDto.getPhotoUrl())
        .description(aBreedDto.getDescription())
        .build();
  }
}
