package com.faviotorres.acopiomx.acopio;

import android.content.Intent;

import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.retro.Retro;
import com.faviotorres.acopiomx.retro.RetroService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class Presenter implements AcopioContract.Presenter {

    private AcopioContract.View view;
    private RetroService retroService;

    Presenter(AcopioContract.View view) {
        this.view = view;
        this.retroService = Retro.getService();
    }

    @Override
    public void getAcopioExtra(Intent intent) {
        Acopio acopio = (Acopio) intent.getSerializableExtra("acopio");
        view.setupAcopio(acopio);
    }

    @Override
    public void getProductos(String acopioId) {
        view.showProgressBar();
        Observable<List<Producto>> productos = retroService.fetchProductos(acopioId);
        productos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Producto>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(List<Producto> productos) {
                        view.hideProgressBar();
                        if (productos == null) {
                            view.showToast("Could not fetch products");
                            return;
                        }
                        view.setupProductos(productos);
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
