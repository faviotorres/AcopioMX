package com.faviotorres.acopiomx.acopio.agregar;

import android.content.Intent;

import com.faviotorres.acopiomx.model.Acopio;

class AgregarContract {

    interface Presenter {
        void getAcopioExtra(Intent intent);
    }

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void showToast(String message);
        void setupAcopio(Acopio acopio);
    }
}
