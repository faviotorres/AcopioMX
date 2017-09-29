package com.faviotorres.acopiomx.splash;

import android.content.SharedPreferences;

import com.faviotorres.acopiomx.utils.PreferencesUtils;

class SplashContract {

    interface Presenter {
        void checkIfUserIsLoggedIn(PreferencesUtils preferencesUtils, SharedPreferences shared);
    }

    interface View {
        void userIsLoggedIn();
        void userIsNotLoggedIn();
    }
}
