package com.futureworkshops.codetest.android.data.persistence;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.futureworkshops.codetest.android.domain.repositories.dao.FavouriteDAO;
import com.futureworkshops.codetest.android.domain.repositories.entity.Favourite;

/**
 * Created by romh on 2018-11-13
 */

@Database(version = 1, entities = {Favourite.class})
public abstract class FavouriteDatabase extends RoomDatabase {

  abstract public FavouriteDAO favouriteDAO();
}
