package com.futureworkshops.codetest.android.domain.model;

import com.google.auto.value.AutoValue;

/**
 * Created by romh on 2018-11-18
 */

@AutoValue
public abstract class BreedStats {
  public abstract int adaptability();

  public abstract int friendliness();

  public abstract int grooming();

  public abstract int trainability();

  public abstract int exerciseNeeds();

  public static Builder builder() {
    return new AutoValue_BreedStats.Builder();
  }


  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder adaptability(int adaptability);

    public abstract Builder friendliness(int friendliness);

    public abstract Builder grooming(int grooming);

    public abstract Builder trainability(int trainability);

    public abstract Builder exerciseNeeds(int exerciseNeeds);

    public abstract BreedStats build();
  }
}
