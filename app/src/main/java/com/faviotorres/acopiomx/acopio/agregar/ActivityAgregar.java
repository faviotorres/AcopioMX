package com.faviotorres.acopiomx.acopio.agregar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.base.BaseActivity;
import com.faviotorres.acopiomx.model.Acopio;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityAgregar extends BaseActivity implements AgregarContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.agregar_nombre_tv) TextView nombreTV;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.agregar_producto_et) EditText productoET;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "Agregar Producto", true);
        initialize();
        initializeUI();
        showAvisoDialog();
        getExtras();
    }

    @Override
    protected void initialize() {
        super.initialize();
        presenter = new Presenter(this);
    }

    private void initializeUI() {
        progressBar.setVisibility(View.GONE);
    }

    private void getExtras() {
        presenter.getAcopioExtra(getIntent());
    }

    private void showAvisoDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.DialogTheme);
        alert.setTitle("Aviso");
        alert.setMessage("Estás a punto de agregar un producto que este centro de acopio" +
                " necesita, por favor introduce solamente información verídica y confirmada");
        alert.setPositiveButton("Aceptar", null);
        alert.show();
    }


    /* ON CLICKS */

    @OnClick(R.id.fab)
    public void onClickAdd() {
        longToast(this, "Adding: "+productoET.getText().toString());
        productoET.setText("");
    }


    /* AGREGAR CONTRACT VIEW */

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {
        longToast(this, message);
    }

    @Override
    public void setupAcopio(Acopio acopio) {
        nombreTV.setText(acopio.getNombre());
    }
}
