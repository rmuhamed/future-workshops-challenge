package com.futureworkshops.codetest.android.domain.repositories.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by romh on 2018-11-13
 */

@Entity
public class Favourite {
  @PrimaryKey
  private final long id;
  @ColumnInfo(name = "name")
  private final String name;

  @ColumnInfo(name = "description")
  private final String description;

  @ColumnInfo(name = "photo_url")
  private final String photoUrl;

  public Favourite(
      long id,
      String name,
      String description,
      String photoUrl) {
    this.id = id;
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    if (description == null) {
      throw new NullPointerException("Null description");
    }
    this.description = description;
    if (photoUrl == null) {
      throw new NullPointerException("Null photoUrl");
    }
    this.photoUrl = photoUrl;
  }


  public long id() {
    return id;
  }


  public String name() {
    return name;
  }


  public String description() {
    return description;
  }


  public String photoUrl() {
    return photoUrl;
  }


  public String toString() {
    return "Breed{"
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "description=" + description + ", "
        + "photoUrl=" + photoUrl
        + "}";
  }
}
