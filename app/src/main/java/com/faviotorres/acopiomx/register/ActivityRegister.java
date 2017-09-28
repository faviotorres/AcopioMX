package com.faviotorres.acopiomx.register;

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

public class ActivityRegister extends BaseActivity implements RegisterContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.email_et) EditText emailET;
    @BindView(R.id.password_et) EditText passwordET;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void create() {
        super.create();
        initialize();
        initializeUI();

    }

    /* MAIN FUNCTIONS */

    private void initialize() {
        presenter = new Presenter(this);
    }

    private void initializeUI() {
        setupToolbar(toolbar, "Register", true);
        hideProgressBar();
    }



    /* ON CLICKS */

    @OnClick(R.id.register_b)
    public void onClickRegister() {
        presenter.register(getTextET(emailET), getTextET(emailET), getTextET(passwordET));
    }



    /* REGISTER CONTRACT VIEW */

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
            saveToken(this, "REGISTER", id);
            Intent intent = new Intent(this, ActivityHome.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
