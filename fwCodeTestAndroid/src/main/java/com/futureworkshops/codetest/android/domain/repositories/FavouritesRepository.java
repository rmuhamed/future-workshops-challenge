package com.futureworkshops.codetest.android.domain.repositories;

import android.content.Context;

import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;

import java.util.List;

/**
 * Created by romh on 2018-11-16
 */
public class FavouritesRepository {

  private final PersistenceRepository persistenceRepository;

  public FavouritesRepository(Context context) {
    this.persistenceRepository = PersistenceRepository.getInstance(context);
  }

  public List<Favourite> getAll() {
    return this.persistenceRepository.getDbInstance().favouritesDAO().getAll();
  }

  public Favourite getBy(long favouriteId) {
    return this.persistenceRepository.getDbInstance().favouritesDAO().findById(favouriteId);
  }

  public void save(Favourite favourite) {
    this.persistenceRepository.getDbInstance().favouritesDAO().save(favourite);
  }

  public void delete(Favourite favourite) {
    this.persistenceRepository.getDbInstance().favouritesDAO().remove(favourite);
  }
}
