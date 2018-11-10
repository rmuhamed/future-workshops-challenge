package com.futureworkshops.codetest.android.presentation.breeds.list.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.data.network.dto.BreedDto;

import java.util.List;

/**
 * Created by romh on 2018-11-10
 */
public class BreedsListAdapter extends RecyclerView.Adapter<BreedItemViewHolder> {
  private List<BreedDto> list;

  @NonNull
  @Override
  public BreedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_breed, parent, false);
    return new BreedItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BreedItemViewHolder holder, int position) {
    BreedDto item = this.getItem(position);
    if (item != null) {
      holder.fillFrom(item);
    }
  }

  @Override
  public int getItemCount() {
    return this.list != null ? this.list.size() : 0;
  }

  public void updateWith(List<BreedDto> list) {
    this.list = list;

    this.notifyDataSetChanged();
  }

  private BreedDto getItem(int position) {
    return this.list != null && !this.list.isEmpty() ? this.list.get(position) : null;
  }
}
