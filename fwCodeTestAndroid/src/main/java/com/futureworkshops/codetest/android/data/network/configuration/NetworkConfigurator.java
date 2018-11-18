package com.futureworkshops.codetest.android.data.network.configuration;

import com.futureworkshops.codetest.android.data.network.converter.XmlOrJsonConverterFactory;
import com.futureworkshops.codetest.android.data.network.interceptor.EndOfWorldInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by romh on 2018-11-18
 */
public class NetworkConfigurator {

  private static NetworkConfigurator configurator;
  private final Retrofit retrofit;

  private NetworkConfigurator(String serverBaseURL) {
    OkHttpClient client = this.buildHttpClient();

    this.retrofit = this.configureRetrofitWith(serverBaseURL, client);
  }

  private Retrofit configureRetrofitWith(String serverBaseURL, OkHttpClient client) {
    return new Retrofit.Builder()
        .baseUrl(serverBaseURL)
        .addConverterFactory(XmlOrJsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build();
  }

  private OkHttpClient buildHttpClient() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    return new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(new EndOfWorldInterceptor())
        .build();
  }

  public static NetworkConfigurator from(String serverBaseUrl) {
    if (configurator == null) {
     configurator = new NetworkConfigurator(serverBaseUrl);
    }

    return configurator;
  }

  public Retrofit getRetrofitConfigured() {
    return this.retrofit;
  }
}
