package com.tesis.gchavez.appcobranzamg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.util.PreferencesManager;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.exit:
                logout();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        setTitle("Bienvenido " );
    }

    public void callGoals(View view) {

    }

    public void callProfile(View view) {
        startActivity(new Intent(PrincipalActivity.this, ProfileActivity.class));
    }

    public void callClient(View view) {

    }

    public void callRoute(View view) {

    }

    public void logout(){
        PreferencesManager.getInstance().remove(PreferencesManager.PREF_ISLOGGED);
        finish();
    }

}
