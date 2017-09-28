package com.faviotorres.acopiomx.register;

class RegisterContract {

    interface Presenter {
        void register(String email, String username, String password);
    }

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void showError(String message);
        void setupId(String id);
    }
}
