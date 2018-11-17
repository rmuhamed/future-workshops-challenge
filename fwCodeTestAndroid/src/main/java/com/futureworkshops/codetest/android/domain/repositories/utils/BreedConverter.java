package com.futureworkshops.codetest.android.domain.repositories.utils;

import com.futureworkshops.codetest.android.data.network.dto.BreedDto;
import com.futureworkshops.codetest.android.domain.model.Breed;

/**
 * Created by romh on 2018-11-17
 */
public class BreedConverter {

  public static Breed from(BreedDto aBreedDto) {
    return Breed.builder()
        .id(aBreedDto.getId())
        .name(aBreedDto.getName())
        .photoUrl(aBreedDto.getPhotoUrl())
        .description(aBreedDto.getDescription())
        .build();
  }
}
