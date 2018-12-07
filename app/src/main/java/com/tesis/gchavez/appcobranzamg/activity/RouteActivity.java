package com.tesis.gchavez.appcobranzamg.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;

import com.tesis.gchavez.appcobranzamg.R;

public class RouteActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        setTitle("Armar ruta" );
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
