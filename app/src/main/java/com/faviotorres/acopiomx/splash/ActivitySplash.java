package com.faviotorres.acopiomx.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.base.BaseActivity;
import com.faviotorres.acopiomx.home.ActivityHome;
import com.faviotorres.acopiomx.login.ActivityLogin;

import butterknife.ButterKnife;

public class ActivitySplash extends BaseActivity implements SplashContract.View {

    private Context context;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initialize();
        checkUser();
    }

    @Override
    protected void initialize() {
        super.initialize();
        context = this;
        presenter = new Presenter(this);
    }



    /* MAIN FUNCTIONS */

    private void checkUser() {
        presenter.checkIfUserIsLoggedIn(preferencesUtils, getSharedPreferences(this));
    }



    /* SPLASH CONTRACT */

    @Override
    public void userIsLoggedIn() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, ActivityHome.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        }, 600);
    }

    @Override
    public void userIsNotLoggedIn() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, ActivityLogin.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        }, 600);
    }
}
