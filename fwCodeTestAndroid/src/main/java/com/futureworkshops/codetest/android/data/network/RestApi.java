/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.data.network;


import com.futureworkshops.codetest.android.data.network.dto.BreedStatsDto;
import com.futureworkshops.codetest.android.data.network.dto.BreedsListDto;
import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi {
    
    @GET("breeds")
    Single<BreedsListDto> getBreeds();
    
    @GET("stats/{id}")
    Single<BreedStatsDto> getStats(@Path("id") long id);

    @POST("important_operation")
    Completable performImportantOperation();

}
