package com.futureworkshops.codetest.android.data.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.futureworkshops.codetest.android.presentation.FwTestApp;


/**
 * Created by romh on 2018-11-16
 */
public class RoomDB {
  private static final String DATABASE_NAME = "favourites.db";

  private static RoomDB ourInstance;
  private FavouriteDatabase appDatabase;

  public static FavouriteDatabase getDefaultInstance() {
    if (ourInstance == null) {
      ourInstance = new RoomDB();
    }
    return ourInstance.getAppDatabase();
  }

  private RoomDB() {
    Context context = FwTestApp.getContext();
    //Initialize the room database with database name
    this.appDatabase = Room.databaseBuilder(context, FavouriteDatabase.class, DATABASE_NAME)
        .allowMainThreadQueries() //Should not be used in a real app
        .fallbackToDestructiveMigration()
        .addCallback(new RoomDatabase.Callback() {
          @Override
          public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("FavouritesDatabase", "creating db ...");
          }
        })
        .build();
  }

  private FavouriteDatabase getAppDatabase() {
    return appDatabase;
  }
}
