package com.futureworkshops.codetest.android.domain.repositories;

import com.futureworkshops.codetest.android.data.network.RestManager;
import com.futureworkshops.codetest.android.data.network.rx.scheduler.WorkerSchedulerProvider;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by romh on 2018-11-11
 */
public class NetworkRepository {

  private static NetworkRepository instance;
  private final RestManager manager;

  public static void initialize(String baseURL) {
    if (instance == null) {
      instance = new NetworkRepository(baseURL);
    }
  }

  public static NetworkRepository getInstance() {
    return instance;
  }

  private NetworkRepository(String baseURL) {
    WorkerSchedulerProvider provider = new WorkerSchedulerProvider();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();

    this.manager = new RestManager(provider, retrofit);
  }

  public RestManager getRestManager() {
    return manager;
  }
}
