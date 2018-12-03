package com.tesis.gchavez.appcobranzamg.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.fragment.ClientDetailFragment;

public class ClientActivity extends AppCompatActivity {

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private EditText searchInput;
    private RadioGroup tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        setTitle("Cartelera de Cliente" );
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchInput = (EditText) findViewById(R.id.searchClient_input);
        tipo = (RadioGroup) findViewById(R.id.rbtn_tipo);
    }

    public void callSearch(View view) {
        String buscar = searchInput.getText().toString();
        if(buscar.isEmpty()){
            Toast.makeText(this, "Completar el campos requerido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tipo.getCheckedRadioButtonId() == R.id.rbtn_ruc){
            Toast.makeText(this, "Buscar por RUC " + buscar , Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Buscar por nombre completo " + buscar , Toast.LENGTH_SHORT).show();
        }
    }

}
