package com.faviotorres.acopiomx.acopio.agregar;

import android.content.Intent;

import com.faviotorres.acopiomx.model.Aceptan;
import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.retro.Retro;
import com.faviotorres.acopiomx.retro.RetroService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class Presenter implements AgregarContract.Presenter {

    private RetroService retroService;
    private AgregarContract.View view;

    public Presenter(AgregarContract.View view) {
        this.view = view;
        this.retroService = Retro.getService();
    }

    public void getAcopioExtra(Intent intent) {
        Acopio acopio = (Acopio) intent.getSerializableExtra("acopio");
        view.setupAcopio(acopio);
    }

    @Override
    public void saveProducto(String acopioId, String producto, String accessToken) {
        view.showProgressBar();
        if (acopioId == null  || acopioId.equals("")) {
            view.hideProgressBar();
            view.showToast("Could not add product");
            return;
        }

        if (producto == null  ||  producto.equals("")) {
            view.hideProgressBar();
            view.showToast("Please enter a valid product");
            return;
        }

        if (accessToken == null  ||  accessToken.equals("")) {
            view.hideProgressBar();
            view.showToast("Authorization failed");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        Observable<Producto> observable = retroService
                .addProducto(acopioId, params, new Aceptan(producto));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Producto>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(Producto producto) {
                        view.hideProgressBar();
                        if (producto == null) {
                            view.showToast("Could not add product, please try again");
                            return;
                        }
                        view.showToast("Success");
                        view.success();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressBar();
                        view.showToast(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }
}
