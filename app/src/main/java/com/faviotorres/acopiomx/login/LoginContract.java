package com.faviotorres.acopiomx.login;

class LoginContract {

    interface Presenter {
        void login(String email, String username, String password);
    }

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void showError(String message);
        void setupId(String id);
    }
}
