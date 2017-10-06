package com.faviotorres.acopiomx.ayuda;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityAyuda extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "Quiero ayudar con...", true);
    }
}
