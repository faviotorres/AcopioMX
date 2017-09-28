package com.faviotorres.acopiomx.register;

import android.util.Log;

import com.faviotorres.acopiomx.model.Register;
import com.faviotorres.acopiomx.retro.Retro;
import com.faviotorres.acopiomx.retro.RetroService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class Presenter implements RegisterContract.Presenter {

    private RetroService retroService;
    private RegisterContract.View view;

    Presenter(RegisterContract.View view) {
        this.view = view;
        this.retroService = Retro.getService();
    }

    @Override
    public void register(String email, String username, String password) {
        view.showProgressBar();
        if (retroService != null) {
            Observable<Register.Result> result = retroService
                    .register(new Register(email, username, password));
            if (result != null) {
                result.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Register.Result>() {
                            @Override
                            public void onSubscribe(Disposable d) { }

                            @Override
                            public void onNext(Register.Result s) {
                                view.hideProgressBar();
                                if (s == null) {
                                    view.showError("Could not login, please try again");
                                    return;
                                }
                                view.setupId(s.getId());
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgressBar();
                                view.showError(e.getLocalizedMessage());
                                Log.d("LOGIN", "---> error: "+e.getLocalizedMessage());
                            }

                            @Override
                            public void onComplete() { }
                        });
            }
        }
    }
}
