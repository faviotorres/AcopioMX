package com.faviotorres.acopiomx.home;

import com.faviotorres.acopiomx.model.Acopio;

import java.util.List;

class HomeContract {

    interface Presenter {
        void getAcopios();
    }

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void showError(String message);
        void setupAcopios(List<Acopio> acopios);
    }
}
