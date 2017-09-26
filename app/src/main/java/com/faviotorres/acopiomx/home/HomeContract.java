package com.faviotorres.acopiomx.home;

import android.content.SharedPreferences;

import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.utils.PreferencesUtils;

import java.util.List;

class HomeContract {

    interface Presenter {
        void getAcopios();
        void checkIfUserIsLoggedIn(PreferencesUtils preferencesUtils, SharedPreferences shared);
    }

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void showError(String message);
        void setupAcopios(List<Acopio> acopios);
        void userIsLoggedIn();
        void userIsNotLoggedIn();
    }
}
