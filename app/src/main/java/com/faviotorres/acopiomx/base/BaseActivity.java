package com.faviotorres.acopiomx.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.faviotorres.acopiomx.utils.PreferencesUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public PreferencesUtils preferencesUtils;

    @LayoutRes
    protected abstract int getLayoutId();

    protected void create() {
        ButterKnife.bind(this);
        preferencesUtils = PreferencesUtils.getInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupToolbar(Toolbar toolbar, String title, boolean home){
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.setTitle(title);
            if(home){
                bar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    public void longToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
    }

    public String getTextET(EditText et) {
        return et.getText().toString();
    }
}
