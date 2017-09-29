package com.faviotorres.acopiomx.home;

import android.util.Log;

import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.retro.Retro;
import com.faviotorres.acopiomx.retro.RetroService;

import java.util.List;

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
        view.showProgressBar();
        if (retroService != null) {
            Log.d("ACOPIOS URL", "---> url: "+retroService.toString());
            Observable<List<Acopio>> acopios = retroService.fetchAcopios();
            if (acopios != null) {
                acopios.subscribeOn(Schedulers.newThread())
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
                            }

                            @Override
                            public void onComplete() { }
                        });
            }
        }
    }
}
