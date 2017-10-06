package com.faviotorres.acopiomx.acopio.agregar;

import android.content.Intent;

import com.faviotorres.acopiomx.model.Acopio;

class Presenter implements AgregarContract.Presenter {

    private AgregarContract.View view;

    public Presenter(AgregarContract.View view) {
        this.view = view;
    }

    public void getAcopioExtra(Intent intent) {
        Acopio acopio = (Acopio) intent.getSerializableExtra("acopio");
        view.setupAcopio(acopio);
    }
}
