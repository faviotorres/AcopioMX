package com.faviotorres.acopiomx.home;

import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Producto;

import java.util.List;

class HomeContract {

    interface Presenter {
        void getAcopios();
        void getAcopio(String acopioId);
        void searchProduct(String product);
    }

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void showError(String message);
        void setupAcopios(List<Acopio> acopios);
        void setupProductos(List<Producto> productos);
        void setupAcopio(Acopio acopio);
    }
}
