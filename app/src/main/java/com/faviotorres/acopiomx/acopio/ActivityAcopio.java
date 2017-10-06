package com.faviotorres.acopiomx.acopio;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import butterknife.OnClick;

public class ActivityAcopio extends BaseActivity implements AcopioContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.acopio_rv) RecyclerView rv;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.acopio_nombre_tv) TextView nombreTV;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.acopio_contacto_tv) TextView contactoTV;
    @BindView(R.id.acopio_direccion_tv) TextView direccionTV;

    private Presenter presenter;
    private StringBuilder phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acopio);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "Centro de Acopio", true);
        initialize();
        initializeUI();
        getAcopioExtra();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length != 0) {
                    if (grantResults[0] == PERMISSION_GRANTED) {
                        callPhone();
                    }
                }
        }
        if(requestCode == 10 && grantResults.length != 0) {
            if (grantResults[0] != -1) {
                callPhone();
            } else {
                longToast(this, "Please enabled call permissions");
            }
        }
    }

    /** MAIN FUNCTIONS */

    @Override
    protected void initialize() {
        super.initialize();
        phones = new StringBuilder();
        presenter = new Presenter(this);
    }

    private void initializeUI() {
        fab.setVisibility(View.GONE);
    }

    private void getAcopioExtra() {
        presenter.getAcopioExtra(getIntent());
    }

    @SuppressWarnings({"MissingPermission", "ResourceType"})
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phones.toString()));
        startActivity(intent);
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(CALL_PHONE_PERMISSION) == PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        10);
                return false;
            }
        } else {
            return true;
        }
    }


    /** ON CLICKS */

    @OnClick(R.id.fab)
    public void onClickPhone() {
        if (isPermissionGranted()) {
            callPhone();
        }
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
        presenter.getContactos(acopio.getId());
    }

    @Override
    public void setupProductos(List<Producto> productos) {
        RecyclerAcopio rvAdapter = new RecyclerAcopio(this, productos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(rvAdapter);
    }

    @Override
    public void setupContacto(List<String> telefonos) {
        if (!telefonos.isEmpty()) {
            fab.setVisibility(View.VISIBLE);
        }
        for (String telefono: telefonos) {
            Log.d("ACOPIO", "---> contact telefono: "+telefono);
            phones.append(telefono).append(" ");
        }
        contactoTV.setText(phones.toString());
        if (phones.toString().equals("") || phones.toString().equals(" ") || telefonos.isEmpty()) {
            contactoTV.setVisibility(View.GONE);
        }
    }
}
