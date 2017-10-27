package com.faviotorres.acopiomx.home;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.faviotorres.acopiomx.Client;
import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.acopio.ActivityAcopio;
import com.faviotorres.acopiomx.base.BaseActivity;
import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.splash.ActivitySplash;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityHome extends BaseActivity implements HomeContract.View, OnMapReadyCallback {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.map_view) MapView mapView;
    @BindView(R.id.filter_tv) TextView filterTV;
    @BindView(R.id.filter_rl) RelativeLayout filterRL;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private int markerId;
    private Context context;
    private MapboxMap mapboxMap;
    private Presenter presenter;
    private List<Acopio> acopios;


    /* BASE ACTIVITY */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, Client.MAPBOX_TOKEN);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "Ayuda Sismo MX", false);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        initialize();
        initializeUI();
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
                showAyudaDialog();
                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }



    /* MAIN FUNCTIONS */

    @Override
    protected void initialize() {
        super.initialize();
        context = this;
        acopios = new ArrayList<>();
        presenter = new Presenter(this);
    }

    private void initializeUI() {
        filterRL.setVisibility(View.GONE);
    }

    private void getAcopios() {
        presenter.getAcopios();
    }



    /* DIALOGS */

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
                Intent intent = new Intent(context, ActivitySplash.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        fullscreenDialog.show();
    }

    private void showAyudaDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.DialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        final ViewGroup vg = null;
        final View view = inflater.inflate(R.layout.dialog_ayuda, vg);

        final EditText productoET = view.findViewById(R.id.dialog_ayuda_et);

        alert.setTitle("Quiero ayduar con...");

        alert.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String producto = getTextET(productoET);
                presenter.searchProduct(producto);
                filterTV.setText(producto);
            }
        });

        alert.setNegativeButton("Cancelar", null);

        alert.setView(view);
        alert.show();
    }



    /* MARKERS */

    private void displayMapMarkers(List<Acopio> acopios) {
        if (this.mapboxMap != null) {
            this.mapboxMap.clear();
            int pos = 0;
            for (final Acopio acopio : acopios) {
                addMarker(acopio, pos);
                pos += 1;
            }
        }
    }

    private void addMarker(Acopio acopio, int pos) {
        MarkerViewOptions options = new MarkerViewOptions()
                .icon(IconFactory.getInstance(this).fromResource(R.mipmap.circle))
                .position(new LatLng(acopio.getGeopos().getLat(),
                        acopio.getGeopos().getLng()))
                .title(acopio.getNombre())
                .snippet(acopio.getDireccion()+"\n\n"+ getString(R.string.see_more));
        options.getMarker().setId(pos);
        this.mapboxMap.addMarker(options);
    }



    /* ON CLICKS */

    @OnClick(R.id.filter_iv)
    public void onClickClose() {
        filterRL.setVisibility(View.GONE);
        displayMapMarkers(this.acopios);
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
        this.acopios = acopios;
        displayMapMarkers(this.acopios);
    }

    @Override
    public void setupProductos(List<Producto> productos) {
        if (mapboxMap != null) { mapboxMap.clear(); }
        filterRL.setVisibility(View.VISIBLE);
        for (Producto producto: productos) {
            Log.d("SETUP PRODUCTS", "---> producto: acopio id: "+producto.getAcopioId());
            presenter.getAcopio(producto.getAcopioId());
        }
    }

    @Override
    public void setupAcopio(Acopio acopio) {
        addMarker(acopio, markerId);
        markerId += 1;
    }


    /* MAP BOX CALLBACK */

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        this.mapboxMap.setOnInfoWindowClickListener(new MapboxMap.OnInfoWindowClickListener() {
            @Override
            public boolean onInfoWindowClick(@NonNull Marker marker) {
                for (Acopio acopio: acopios) {
                    if (acopio.getNombre().equals(marker.getTitle())) {
                        Intent intent = new Intent(context, ActivityAcopio.class);
                        intent.putExtra("acopio", acopio);
                        startActivity(intent);
                        break;
                    }
                }
                return true;
            }
        });
        getAcopios();
    }
}
