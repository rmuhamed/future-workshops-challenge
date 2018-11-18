package com.futureworkshops.codetest.android.domain.repositories;

import com.futureworkshops.codetest.android.data.network.configuration.NetworkConfigurator;
import com.futureworkshops.codetest.android.data.network.RestManager;
import com.futureworkshops.codetest.android.data.network.rx.scheduler.WorkerSchedulerProvider;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

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
    Retrofit retrofit = NetworkConfigurator.from(baseURL).getRetrofitConfigured();

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
