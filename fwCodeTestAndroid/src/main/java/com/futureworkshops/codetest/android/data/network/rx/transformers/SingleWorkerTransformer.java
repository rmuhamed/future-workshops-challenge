/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.data.network.rx.transformers;


import com.futureworkshops.codetest.android.data.network.rx.scheduler.SchedulersProvider;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;


public class SingleWorkerTransformer<T> implements SingleTransformer<T, T> {
    
    private final SchedulersProvider schedulersProvider;
    
    public SingleWorkerTransformer(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }
    
    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        // subscribeOn will cause all upstream calls to run on an io thread.
        // observeOn will cause all the downstream calls to run on the main thread.
        return upstream.subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui());
    }
}
