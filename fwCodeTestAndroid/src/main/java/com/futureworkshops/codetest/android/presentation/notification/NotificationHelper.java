package com.futureworkshops.codetest.android.presentation.notification;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.data.exception.EndOfWorldException;

/**
 * Created by romh on 2018-11-11
 */
public class NotificationHelper {
  private String title;
  private String message;
  private Context context;

  private NotificationHelper(Context context, String title, String message) {
    this.context = context;
    this.title = title;
    this.message = message;
  }

  private void show() {
    new AlertDialog.Builder(this.context)
        .setTitle(this.title)
        .setMessage(this.message)
        .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss())
        .create()
        .show();
  }

  public static class Builder<E extends Throwable> {
    private Context context;
    private String title;
    private String message;

    public NotificationHelper.Builder with(Context context) {
      this.context = context;
      return this;
    }

    public NotificationHelper.Builder from(E throwable) {
      this.message = throwable.getMessage();
      this.title = throwable instanceof EndOfWorldException
          ? ((EndOfWorldException) throwable).getTitle()
          : "Some unknown exception";

      return this;
    }

    public void show() {
      new NotificationHelper(this.context, this.title, this.message).show();
    }
  }

}
