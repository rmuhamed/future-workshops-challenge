/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.breeds.list.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.data.network.RestManager;
import com.futureworkshops.codetest.android.data.network.dto.BreedDto;
import com.futureworkshops.codetest.android.data.network.rx.scheduler.WorkerSchedulerProvider;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class BreedsListFragment extends Fragment {


    private static final String BASE_URL = "BASE_URL";
    private String baseURL;
    private BreedsListAdapter adapter;
    private RecyclerView breedListRecycler;

    public static BreedsListFragment newInstance(String baseURL) {
        Bundle args = new Bundle();
        args.putString(BASE_URL, baseURL);

        BreedsListFragment listFragment = new BreedsListFragment();
        listFragment.setArguments(args);

        return listFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.baseURL = this.getArguments().getString(BASE_URL, null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_breed_list, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.breedListRecycler = view.findViewById(R.id.breed_list_recycler_view);
        this.breedListRecycler.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        this.adapter = new BreedsListAdapter();
        this.breedListRecycler.setAdapter(this.adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WorkerSchedulerProvider provider = new WorkerSchedulerProvider();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(this.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

        RestManager manager = new RestManager(provider, retrofit);
        manager.getBreeds().subscribe(breedDtos -> {
            this.adapter.updateWith(breedDtos);
        });

    }
}