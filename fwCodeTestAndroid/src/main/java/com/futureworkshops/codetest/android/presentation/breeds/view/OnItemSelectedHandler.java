package com.futureworkshops.codetest.android.presentation.breeds.view;

import android.widget.ImageView;

/**
 * Created by romh on 2018-11-11
 */
public interface OnItemSelectedHandler<T> {
  void onItemSelected(ImageView breedPhotoImage, T item);
}
