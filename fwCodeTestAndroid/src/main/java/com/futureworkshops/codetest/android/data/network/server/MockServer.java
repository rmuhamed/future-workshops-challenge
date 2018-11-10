/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.data.network.server;

import android.content.Context;
import io.appflate.restmock.MockAnswer;
import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.RESTMockServerStarter;
import io.appflate.restmock.android.AndroidAssetsFileParser;
import io.appflate.restmock.android.AndroidLogger;
import okhttp3.mockwebserver.MockResponse;

import java.util.concurrent.TimeUnit;

import static io.appflate.restmock.utils.RequestMatchers.pathContains;


public class MockServer {

    private static final int MAX_BREEDS = 20;
    private static final long DELAY_SEC = 1;
    private final AndroidAssetsFileParser mocksFileParser;

    public MockServer(Context context) {
        mocksFileParser = new AndroidAssetsFileParser(context);
    }

    public void start() {
        RESTMockServerStarter.startSync(mocksFileParser,
                new AndroidLogger());

        RESTMockServer.whenGET(pathContains("breeds"))
                .thenReturnFile(200, "breeds.json")
                .delay(TimeUnit.SECONDS, DELAY_SEC);


        RESTMockServer.whenGET(pathContains("stats"))
                .thenAnswer((MockAnswer) request -> {
                    String path = request.getPath();
                    String id = path.substring(path.lastIndexOf("/") + 1);

                    try {
                        String body = mocksFileParser.readJsonFile(id + ".xml");

                        return new MockResponse()
                                .setResponseCode(200)
                                .setHeader("Content-type", "application/xml")
                                .setBody(body)
                                .setBodyDelay(DELAY_SEC, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("The mock server setup failed");
                    }
                });

        RESTMockServer.whenPOST(pathContains("important_operation"))
                .thenReturn(new MockResponse()
                        .setResponseCode(400)
                        .setBody("{\"error\": \"The world is ending, cannot process request\"}")
                        .setBodyDelay(DELAY_SEC, TimeUnit.SECONDS)
                );

    }


    public String getUrl() {
        return RESTMockServer.getUrl();
    }

}
