package com.faviotorres.acopiomx.acopio;

import android.content.Intent;
import android.util.Log;

import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Contacto;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.retro.Retro;
import com.faviotorres.acopiomx.retro.RetroService;

import java.util.ArrayList;
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
        Log.d("ACOPIO", "---> id: "+acopio.getId());
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

    @Override
    public void getContactos(String acopioId) {
        final Observable<List<Contacto>> contacto = retroService.fetchContacto(acopioId);
        contacto.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Contacto>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(List<Contacto> contactos) {
                        view.hideProgressBar();
                        if (contactos == null) {
                            view.showToast("Could not fetch products");
                            return;
                        }
                        List<String> telefonos = new ArrayList<>();
                        for (Contacto contacto: contactos) {
                            telefonos.add(contacto.getTelefono());
                        }
                        view.setupContacto(telefonos);
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
