package com.faviotorres.acopiomx.acopio;

import android.content.Intent;

import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Producto;

import java.util.List;

class AcopioContract {

    interface Presenter {
        void getAcopioExtra(Intent intent);
        void getProductos(String acopioId);
        void getContactos(String acopioId);
    }

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void showToast(String message);
        void setupAcopio(Acopio acopio);
        void setupProductos(List<Producto> productos);
        void setupContacto(List<String> telefonos);
    }
}
