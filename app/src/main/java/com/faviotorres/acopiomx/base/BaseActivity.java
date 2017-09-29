package com.faviotorres.acopiomx.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.faviotorres.acopiomx.utils.PreferencesUtils;

public abstract class BaseActivity extends AppCompatActivity {

    public PreferencesUtils preferencesUtils;

    protected void initialize() {
        preferencesUtils = PreferencesUtils.getInstance();
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


    public void saveToken(Context context, String from, String token) {
        preferencesUtils.saveToken(getSharedPreferences(context), from, token);
    }
    public String loadToken(Context context) {
        return preferencesUtils.loadToken(getSharedPreferences(context));
    }
}
