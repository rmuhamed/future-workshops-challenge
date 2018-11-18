package com.futureworkshops.codetest.android.domain.repositories.utils;

import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by romh on 2018-11-17
 */
public class FavouritesConverter {

  public static Favourite map(Breed breed) {
    return new Favourite(breed.id(), breed.name(), breed.description(), breed.photoUrl());
  }

  public static List<Breed> convertFrom(List<Favourite> favourites) {
    List<Breed> breeds = new ArrayList<>(favourites.size());
    Observable.fromIterable(favourites).map(FavouritesConverter::toOne).toList()
        .doOnSuccess(breeds::addAll)
        .subscribe();

    return breeds;
  }

  public static Breed toOne(Favourite aFavourite) {
    return Breed.builder()
        .id(aFavourite.id())
        .name(aFavourite.name())
        .description(aFavourite.description())
        .photoUrl(aFavourite.photoUrl())
        .build();
  }
}
