package com.tesis.gchavez.appcobranzamg.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.fragment.SelectclienteFragment;

public class SelectclienteActivity extends AppCompatActivity {

    private EditText searchRUCInput;
    private String buscar;

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
        setContentView(R.layout.activity_selectcliente);

        setTitle("Seleccionar Cliente" );
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchRUCInput = findViewById(R.id.searchClient_input);
    }

    public void callSearchRUC(View view) {
        buscar = searchRUCInput.getText().toString();

        if(buscar.isEmpty()){
            Toast.makeText(this, "Completar el campo requerido", Toast.LENGTH_SHORT).show();
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameRUC, SelectclienteFragment.newInstance());
        transaction.commit();

    }
}
