/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */
package com.futureworkshops.codetest.android.domain.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Breed implements Parcelable {

    public abstract long id();

    public abstract String name();

    public abstract String description();

    public abstract String photoUrl();

    public static Builder builder() {
        return new AutoValue_Breed.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(long id);

        public abstract Builder name(String name);

        public abstract Builder description(String description);

        public abstract Builder photoUrl(String photoUrl);

        public abstract Breed build();
    }
}
