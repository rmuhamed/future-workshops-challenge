/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.data.network.dto;


import org.simpleframework.xml.Element;

public class BreedStatsDto {

    @Element(name = "adaptability")
    private int adaptability;

    @Element(name = "friendliness")
    private int friendliness;

    @Element(name = "grooming")
    private int grooming;

    @Element(name = "trainability")
    private int trainability;

    @Element(name = "exercise_needs")
    private int exerciseNeeds;

    public int getAdaptability() {
        return adaptability;
    }

    public int getFriendliness() {
        return friendliness;
    }

    public int getGrooming() {
        return grooming;
    }

    public int getTrainability() {
        return trainability;
    }

    public int getExerciseNeeds() {
        return exerciseNeeds;
    }
}
