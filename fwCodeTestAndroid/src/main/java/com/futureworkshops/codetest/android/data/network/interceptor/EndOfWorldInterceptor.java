package com.futureworkshops.codetest.android.data.network.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by romh on 2018-11-18
 */
public class EndOfWorldInterceptor implements Interceptor {

  public static final int BAD_REQUEST = 400;

  @Override
  public Response intercept(Chain chain) throws IOException {
    Response response = chain.proceed(chain.request());
    if (response.code() == BAD_REQUEST) {
      Log.e(EndOfWorldInterceptor.class.getSimpleName(),
          "End of World is coming and nobody could stop it");
    }
    return response;
  }
}
