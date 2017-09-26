package com.faviotorres.acopiomx.login;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.base.BaseActivity;
import com.faviotorres.acopiomx.home.ActivityHome;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityLogin extends BaseActivity implements LoginContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.email_et) EditText emailET;
    @BindView(R.id.password_et) EditText passwordET;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private Presenter presenter;


    /* BASE ACTIVITY */

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void create() {
        super.create();
        initialize();
        initializeUI();
    }

    private void initialize() {
        presenter = new Presenter(this);
    }

    private void initializeUI() {
        setupToolbar(toolbar, "Login", false);
        hideProgressBar();
    }


    /* ON CLICKS */

    @OnClick(R.id.login_b)
    public void onClickLogin() {
        presenter.login(getTextET(emailET), getTextET(emailET), getTextET(passwordET));
    }



    /* LOGIN CONTRACT */

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        longToast(this, message);
    }

    @Override
    public void setupId(String id) {
        if (id != null) {
            preferencesUtils.saveToken(getSharedPreferences(this), "LOGIN", id);
            Intent intent = new Intent(this, ActivityHome.class);
            startActivity(intent);
            finish();
        }
    }
}
