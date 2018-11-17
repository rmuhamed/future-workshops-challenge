package com.futureworkshops.codetest.android.domain.repositories.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;

import java.util.List;

/**
 * Created by romh on 2018-11-13
 */

@Dao
public interface FavouriteDAO {
  @Query("SELECT * FROM Favourite")
  List<Favourite> getAll();

  @Query("SELECT * FROM Favourite WHERE id=id")
  Favourite findById(long id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(Favourite aFavourite);

  @Delete
  void remove(Favourite aFavouriteToBeRemoved);
}
