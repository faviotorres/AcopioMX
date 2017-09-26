package com.faviotorres.acopiomx.login;


import android.util.Log;

import com.faviotorres.acopiomx.model.Login;
import com.faviotorres.acopiomx.retro.Retro;
import com.faviotorres.acopiomx.retro.RetroService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements LoginContract.Presenter {

    private RetroService retroService;
    private LoginContract.View view;

    Presenter(LoginContract.View view) {
        this.view = view;
        this.retroService = Retro.getService();
    }

    @Override
    public void login(String email, String username, String password) {
        view.showProgressBar();
        if (retroService != null) {
            Observable<Login.Result> result = retroService
                    .login(new Login(email, username, password));
            if (result != null) {
                result.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Login.Result>() {
                            @Override
                            public void onSubscribe(Disposable d) { }

                            @Override
                            public void onNext(Login.Result s) {
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
