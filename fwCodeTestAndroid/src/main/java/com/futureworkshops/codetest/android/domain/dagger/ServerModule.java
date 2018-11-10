/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.domain.dagger;

import android.content.Context;
import com.futureworkshops.codetest.android.data.network.server.MockServer;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ServerModule {


    @Singleton
    @Provides
    MockServer providesMockServer(Context context) {
        return new MockServer(context);
    }

    @Singleton
    @Provides
    @Named("endpoint")
    String providesEndpoint(MockServer mockServer) {
        return mockServer.getUrl();
    }

}
