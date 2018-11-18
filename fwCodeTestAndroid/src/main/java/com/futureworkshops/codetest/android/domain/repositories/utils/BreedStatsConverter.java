package com.futureworkshops.codetest.android.domain.repositories.utils;

import com.futureworkshops.codetest.android.data.network.dto.BreedStatsDto;
import com.futureworkshops.codetest.android.domain.model.BreedStats;

/**
 * Created by romh on 2018-11-17
 */
public class BreedStatsConverter {

  public static BreedStats from(BreedStatsDto breedStatsDto) {
    return BreedStats.builder()
        .adaptability(breedStatsDto.getAdaptability())
        .exerciseNeeds(breedStatsDto.getExerciseNeeds())
        .trainability(breedStatsDto.getTrainability())
        .friendliness(breedStatsDto.getFriendliness())
        .grooming(breedStatsDto.getGrooming())
        .build();
  }
}
