package com.futureworkshops.codetest.android.domain.repositories;

import com.futureworkshops.codetest.android.data.network.dto.BreedDto;
import com.futureworkshops.codetest.android.data.network.dto.BreedStatsDto;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by romh on 2018-11-11
 */
public class BreedsRepository  {
  private final NetworkRepository networkRepository;

  public BreedsRepository() {
    this.networkRepository = NetworkRepository.getInstance();
  }

  public Single<List<BreedDto>> getBreeds() {
    return this.networkRepository.getRestManager().getBreeds();
  }

  public Single<BreedStatsDto> getStatsFor(long breedId) {
    return this.networkRepository.getRestManager().getStats(breedId);
  }
}
