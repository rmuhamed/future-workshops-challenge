package com.futureworkshops.codetest.android.domain.repositories;

import android.content.Context;

import com.futureworkshops.codetest.android.data.persistence.FavouritesDatabase;

/**
 * Created by romh on 2018-11-13
 */
public class PersistenceRepository {

  private static PersistenceRepository instance;
  private static FavouritesDatabase dbInstance;

  public static PersistenceRepository getInstance(Context context) {
    if (instance == null) {
      instance = new PersistenceRepository();

      dbInstance = FavouritesDatabase.getDatabase(context);
    }

    return instance;
  }

  public FavouritesDatabase getDbInstance() {
    return dbInstance;
  }
}
