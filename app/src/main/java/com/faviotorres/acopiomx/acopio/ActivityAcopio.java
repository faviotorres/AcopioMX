package com.faviotorres.acopiomx.acopio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityAcopio extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acopio);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "Centro de Acopio", true);
        Intent intent = getIntent();
        String id = intent.getStringExtra("acopio_id");
        Log.d("ACOPIO", "---> id: "+id);
    }
}
