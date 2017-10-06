package com.faviotorres.acopiomx.home;

import android.util.Log;

import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.retro.Retro;
import com.faviotorres.acopiomx.retro.RetroService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class Presenter implements HomeContract.Presenter {

    private RetroService retroService;
    private HomeContract.View view;

    Presenter(HomeContract.View view) {
        this.view = view;
        this.retroService = Retro.getService();
    }

    @Override
    public void getAcopios() {
        if (retroService != null) {
            view.showProgressBar();
            Observable<List<Acopio>> observable = retroService.fetchAcopios();
            if (observable != null) {
                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<Acopio>>() {
                            @Override
                            public void onSubscribe(Disposable d) { }

                            @Override
                            public void onNext(List<Acopio> acopios) {
                                view.hideProgressBar();
                                if (acopios == null) {
                                    view.showError("Could not fetch data");
                                    return;
                                }
                                view.setupAcopios(acopios);
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgressBar();
                                view.showError(e.getLocalizedMessage());
                                Log.e("FETCH ACOPIOS", e.getLocalizedMessage());
                            }

                            @Override
                            public void onComplete() { }
                        });
            }
        }
    }

    @Override
    public void getAcopio(String acopioId) {
        if (retroService != null) {
            view.showProgressBar();
            final Observable<Acopio> observable = retroService.fetchAcopio(acopioId);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Acopio>() {
                        @Override
                        public void onSubscribe(Disposable d) { }

                        @Override
                        public void onNext(Acopio acopio) {
                            view.hideProgressBar();
                            if (acopio == null) {
                                view.showError("Could not fetch acopio");
                                return;
                            }
                            Log.d("GET ACOPIO", "---> found: "+acopio.getNombre());
                            view.setupAcopio(acopio);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideProgressBar();
                            view.showError(e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() { }
                    });
        }
    }

    @Override
    public void searchProduct(String product) {
        final String filter = "{\"where\":{\"nombre\":{\"like\":\""+product.toLowerCase()+"\"}}}";
        Map<String, String> params = new HashMap<String, String>() {{
            put("filter", filter);
        }};
        final Observable<List<Producto>> observable = retroService.searchProducto(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Producto>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(List<Producto> productos) {
                        view.hideProgressBar();
                        if (productos == null) {
                            view.showError("Could not fetch products");
                            return;
                        }
                        view.setupProductos(productos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressBar();
                        view.showError(e.getLocalizedMessage());
                        Log.e("SEARCH PRODUCT", e.getLocalizedMessage());
                        Log.d("SEARCH PRODUCT", e.toString());
                    }

                    @Override
                    public void onComplete() { }
                });
    }
}
