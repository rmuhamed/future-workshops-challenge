package com.futureworkshops.codetest.android.domain.repositories;

import com.futureworkshops.codetest.android.data.persistence.FavouriteDatabase;
import com.futureworkshops.codetest.android.data.persistence.RoomDB;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;

import java.util.List;

/**
 * Created by romh on 2018-11-16
 */
public class FavouritesRepository {

  private final FavouriteDatabase database;

  public FavouritesRepository() {
    this.database = RoomDB.getDefaultInstance();
  }

  public List<Favourite> getAll() {
    return this.database.favouriteDAO().getAll();
  }

  public Favourite getBy(long favouriteId) {
    return this.database.favouriteDAO().findById(favouriteId);
  }

  public void save(Favourite favourite) {
    this.database.favouriteDAO().save(favourite);
  }

  public void delete(Favourite favourite) {
    this.database.favouriteDAO().remove(favourite);
  }
}
