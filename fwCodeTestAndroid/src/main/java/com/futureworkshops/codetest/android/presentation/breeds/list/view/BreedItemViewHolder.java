package com.futureworkshops.codetest.android.presentation.breeds.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.data.network.dto.BreedDto;

/**
 * Created by romh on 2018-11-10
 */
public class BreedItemViewHolder extends RecyclerView.ViewHolder {
  public BreedItemViewHolder(View itemView) {
    super(itemView);
  }

  public void fillFrom(BreedDto item) {
    TextView breedNameLabel = this.itemView.findViewById(R.id.breedName);
    ImageView breedImage = this.itemView.findViewById(R.id.breedImage);

    breedNameLabel.setText(item.getName());
    Glide.with(this.itemView.getContext())
        .load(item.getPhotoUrl())
        .into(breedImage);
  }
}
