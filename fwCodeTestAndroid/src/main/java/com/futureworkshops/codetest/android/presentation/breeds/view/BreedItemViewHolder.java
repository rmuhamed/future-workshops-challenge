package com.futureworkshops.codetest.android.presentation.breeds.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.domain.model.Breed;
import com.futureworkshops.codetest.android.presentation.breeds.view.OnItemSelectedHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by romh on 2018-11-10
 */
public class BreedItemViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.breedName)
  TextView breedNameLabel;

  @BindView(R.id.breedImage)
  ImageView breedPhotoImage;

  private OnItemSelectedHandler<Breed> onItemSelectionHandler;

  public BreedItemViewHolder(View itemView) {
    super(itemView);

    ButterKnife.bind(this, itemView);
  }

  public void fillFrom(Breed breed) {
    this.breedNameLabel.setText(breed.name());
    Glide.with(this.itemView.getContext())
        .load(breed.photoUrl())
        .into(this.breedPhotoImage);

    this.itemView.setOnClickListener(v -> this.onItemSelectionHandler.onItemSelected(this.breedPhotoImage, breed));
  }

  public void addItemSelectionHandler(OnItemSelectedHandler<Breed> onItemSelectedHandler) {
    this.onItemSelectionHandler = onItemSelectedHandler;
  }
}
