package com.futureworkshops.codetest.android.data.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.futureworkshops.codetest.android.domain.repositories.dao.FavouritesDAO;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;

/**
 * Created by romh on 2018-11-13
 */

@Database(version = 1, entities = {Favourite.class})
public abstract class FavouritesDatabase extends RoomDatabase {

  // BookDao is a class annotated with @Dao.
  abstract public FavouritesDAO favouritesDAO();

  private static FavouritesDatabase INSTANCE;
  private static final String DB_NAME = "favourites.db";

  public static FavouritesDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (FavouritesDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              FavouritesDatabase.class, DB_NAME)
              .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
              .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                  super.onCreate(db);
                  Log.d("FavouritesDatabase", "populating db with data...");
                }
              })
              .build();
        }
      }
    }

    return INSTANCE;
  }
}
