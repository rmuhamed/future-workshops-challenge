package com.futureworkshops.codetest.android.presentation.notification;

import android.content.Context;

/**
 * Created by romh on 2018-11-11
 */
public class NotificationHelper {

  private NotificationHelper(Context context, String message) {

  }

  public void show() {
    //TODO: RM - Add logic here
  }

  public static class Builder {
    private Context context;
    private String title;
    private String message;

    public NotificationHelper.Builder with(Context context) {
      this.context = context;
      return this;
    }

    public NotificationHelper.Builder from(Throwable throwable) {
      this.message = throwable.getMessage();
      return this;
    }

    public void show() {
      new NotificationHelper(this.context, this.message).show();
    }
  }

}
