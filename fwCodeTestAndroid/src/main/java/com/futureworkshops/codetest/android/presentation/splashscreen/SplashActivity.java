/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.codetest.android.presentation.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.futureworkshops.codetest.android.R;
import com.futureworkshops.codetest.android.presentation.landing.MainActivity;
import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import timber.log.Timber;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.fillableLoader)
    FillableLoader fillableLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        setupFillableLoader();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fillableLoader.postDelayed(() -> fillableLoader.start(), 200);
    }

    private void setupFillableLoader() {
        fillableLoader.setSvgPath(getString(R.string.splash_svg_path));

        fillableLoader.setOnStateChangeListener(state -> {
            switch (state) {
                case State.NOT_STARTED:
                    Timber.d("fillable state: NOT STARTED ");
                    break;
                case State.STROKE_STARTED:
                    Timber.d("fillable state: STROKE STARTED ");
                    break;
                case State.FILL_STARTED:
                    Timber.d("fillable state: FILL STARTED ");
                    break;
                case State.FINISHED:
                    this.startActivity(new Intent(this, MainActivity.class));
                    Timber.d("fillable state: FINISHED ");
                    break;
            }
        });
    }

}
