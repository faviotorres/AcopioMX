package com.faviotorres.acopiomx.splash;

import android.content.SharedPreferences;

import com.faviotorres.acopiomx.utils.PreferencesUtils;

class Presenter implements SplashContract.Presenter {

    private SplashContract.View view;

    Presenter(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void checkIfUserIsLoggedIn(PreferencesUtils preferencesUtils, SharedPreferences shared) {
        String token = preferencesUtils.loadToken(shared);
        if (token == null) {
            view.userIsNotLoggedIn();
        } else {
            view.userIsLoggedIn();
        }
    }
}
