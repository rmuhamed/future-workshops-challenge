package com.futureworkshops.codetest.android.presentation.breeds.list.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.domain.model.Breed;

import java.util.List;

/**
 * Created by romh on 2018-11-10
 */
public class BreedsListAdapter extends RecyclerView.Adapter<BreedItemViewHolder> {
  private List<Breed> list;
  private OnItemSelectedHandler<Breed> onItemSelectedHandler;

  @NonNull
  @Override
  public BreedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_breed, parent, false);
    return new BreedItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BreedItemViewHolder holder, int position) {
    Breed item = this.getItem(position);
    if (item != null) {
      holder.fillFrom(item);
      holder.addItemSelectionHandler(this.onItemSelectedHandler);
    }
  }

  @Override
  public int getItemCount() {
    return this.list != null ? this.list.size() : 0;
  }

  public void updateWith(List<Breed> list) {
    this.list = list;

    this.notifyDataSetChanged();
  }

  private Breed getItem(int position) {
    return this.list != null && !this.list.isEmpty() ? this.list.get(position) : null;
  }

  public void addOnItemSelectedHandler(OnItemSelectedHandler<Breed> onItemSelectedHandler) {
    this.onItemSelectedHandler = onItemSelectedHandler;
  }
}
