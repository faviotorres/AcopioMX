package com.faviotorres.acopiomx.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.base.BaseActivity;
import com.faviotorres.acopiomx.login.ActivityLogin;
import com.faviotorres.acopiomx.model.Acopio;

import java.util.List;

import butterknife.BindView;

public class ActivityHome extends BaseActivity implements HomeContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private Context context;
    private Presenter presenter;


    /* BASE ACTIVITY */

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void create() {
        super.create();
        setupToolbar(toolbar, getString(R.string.app_name), false);
        initialize();
        checkUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_info:
                showSupportersDialog();
                break;
            case R.id.action_help:

                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /* MAIN */

    private void initialize() {
        context = this;
        presenter = new Presenter(this);
    }

    private void checkUser() {
        presenter.checkIfUserIsLoggedIn(preferencesUtils, getSharedPreferences(this));
    }

    private void showSupportersDialog() {
        final Dialog fullscreenDialog = new Dialog(this, R.style.DialogFullscreen);
        fullscreenDialog.setContentView(R.layout.dialog_supporters);
        Button logoutB = fullscreenDialog.findViewById(R.id.logout_b);
        ImageView img_dialog_fullscreen_close = fullscreenDialog.findViewById(R.id.close_iv);
        TextView collaborationTV = fullscreenDialog.findViewById(R.id.info_collaboration_tv);
        collaborationTV.setText(Html.fromHtml(getString(R.string.collaboration)));
        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDialog.dismiss();
            }
        });
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToken(context, "HOME", null);
                Intent intent = new Intent(context, ActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        fullscreenDialog.show();
    }


    /* HOME CONTRACT VIEW */

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
    public void setupAcopios(List<Acopio> acopios) {
        Log.d("ACOPIOS", "---> acopios size: "+acopios.size());
        for (Acopio acopio: acopios) {
            Log.d("ACOPIOS", "---> acopios: " + acopio.toString());
        }
    }

    @Override
    public void userIsLoggedIn() {
        presenter.getAcopios();
    }

    @Override
    public void userIsNotLoggedIn() {
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
        finish();
    }
}
