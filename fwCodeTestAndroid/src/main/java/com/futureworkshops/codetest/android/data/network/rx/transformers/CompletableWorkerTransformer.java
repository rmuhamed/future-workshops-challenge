/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.data.network.rx.transformers;


import com.futureworkshops.codetest.android.data.network.rx.scheduler.SchedulersProvider;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;

/**
 * Created by stelian on 04/02/2018.
 */

public class CompletableWorkerTransformer implements CompletableTransformer {
    
    private final SchedulersProvider schedulersProvider;
    
    public CompletableWorkerTransformer(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }
    
    @Override
    public CompletableSource apply(Completable upstream) {
        // subscribeOn will cause all upstream calls to run on an io thread.
        // observeOn will cause all the downstream calls to run on the main thread.
        return upstream.subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui());
    }
}
