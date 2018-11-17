package com.futureworkshops.codetest.android.domain.repositories;

import com.futureworkshops.codetest.android.data.persistence.FavouriteDatabase;
import com.futureworkshops.codetest.android.data.persistence.RoomDB;
import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;
import com.futureworkshops.codetest.android.domain.repositories.utils.FavouritesConverter;

import java.util.List;

/**
 * Created by romh on 2018-11-16
 */
public class FavouritesRepository {

  private final FavouriteDatabase database;

  public FavouritesRepository() {
    this.database = RoomDB.getDefaultInstance();
  }

  public List<Breed> getAll() {
    List<Favourite> favourites = this.database.favouriteDAO().getAll();

    return FavouritesConverter.convertFrom(favourites);
  }

  public Favourite getBy(long favouriteId) {
    return this.database.favouriteDAO().findById(favouriteId);
  }

  public void save(Breed aBreed) {
    this.database.favouriteDAO().save(FavouritesConverter.map(aBreed));
  }

  public void delete(Favourite favourite) {
    this.database.favouriteDAO().remove(favourite);
  }
}
