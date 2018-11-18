package com.futureworkshops.codetest.android.domain.repositories;

import com.futureworkshops.codetest.android.data.network.RestManager;
import com.futureworkshops.codetest.android.data.network.interceptor.EndOfWorldInterceptor;
import com.futureworkshops.codetest.android.data.network.rx.scheduler.WorkerSchedulerProvider;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient client =
        new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(new EndOfWorldInterceptor())
        .build();

    WorkerSchedulerProvider provider = new WorkerSchedulerProvider();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build();

    this.manager = new RestManager(provider, retrofit);
  }

  RestManager getRestManager() {
    return manager;
  }

  public void getImportAction(Consumer<Throwable> c) {
    this.manager.performImportantOperation()
        .subscribe(new CompletableObserver() {
          @Override
          public void onSubscribe(Disposable d) {}

          @Override
          public void onComplete() {}

          @Override
          public void onError(Throwable e) {
            try {
              c.accept(e);
            } catch (Exception e1) {
              e1.printStackTrace();
            }
          }
        });
  }
}
