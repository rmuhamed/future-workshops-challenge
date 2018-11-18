package com.futureworkshops.codetest.android.presentation.breeds;

import android.os.Bundle;
import android.os.Parcelable;

/**
 * Created by romh on 2018-11-18
 */
public class BundleUtils {

   public <T extends Parcelable> Bundle packagedExtras(String key, T object) {
    Bundle args = new Bundle();
    args.putParcelable(key, object);

    return args;
  }

  public <T extends Parcelable> T unpackExtras(String key, Bundle extras) {
     return (extras != null && extras.containsKey(key)) ? extras.getParcelable(key) : null;
  }
}
