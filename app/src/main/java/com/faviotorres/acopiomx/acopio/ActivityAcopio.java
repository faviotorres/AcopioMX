package com.faviotorres.acopiomx.acopio;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.base.BaseActivity;
import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Producto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityAcopio extends BaseActivity implements AcopioContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.acopio_rv) RecyclerView rv;
    @BindView(R.id.acopio_nombre_tv) TextView nombreTV;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.acopio_direccion_tv) TextView direccionTV;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acopio);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "Centro de Acopio", true);
        initialize();
        getAcopioExtra();
    }

    @Override
    protected void initialize() {
        super.initialize();
        presenter = new Presenter(this);
    }

    private void getAcopioExtra() {
        presenter.getAcopioExtra(getIntent());
    }



    /** ACOPIO CONTRACT VIEW */

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
        direccionTV.setText(acopio.getDireccion());

        presenter.getProductos(acopio.getId());
    }

    @Override
    public void setupProductos(List<Producto> productos) {
        RecyclerAcopio rvAdapter = new RecyclerAcopio(this, productos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(rvAdapter);
    }
}
