/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.data.network.dto;

import com.google.gson.annotations.SerializedName;

public class BreedDto {

    private long id;

    private String name;

    private String description;

    @SerializedName("photo")
    private String photoUrl;

    private String origin;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getOrigin() {
        return origin;
    }
}
