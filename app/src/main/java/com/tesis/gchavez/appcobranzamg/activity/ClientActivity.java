package com.tesis.gchavez.appcobranzamg.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.fragment.ClientDetailFragment;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;

public class ClientActivity extends AppCompatActivity {

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String TAG = ClientActivity.class.getSimpleName();

    private EditText searchInput;
    private RadioGroup tipo;
    private String buscar;
    private String idruc;
    private String idname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        setTitle("Cartelera de Cliente" );
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchInput = findViewById(R.id.searchClient_input);
        tipo = findViewById(R.id.rbtn_tipo);
    }

    public void callSearch(View view) {
        buscar = searchInput.getText().toString();
        if(buscar.isEmpty()){
            Toast.makeText(this, "Completar el campo requerido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tipo.getCheckedRadioButtonId() == R.id.rbtn_ruc){
            RadioButton id = findViewById(R.id.rbtn_ruc);
            idruc = id.getText().toString();
            rptbuscarruc(idruc,buscar);
        }else {
            RadioButton id = findViewById(R.id.rbtn_name);
            idname = id.getText().toString();
            rptbuscarname(idname,buscar);

        }
    }

    private void rptbuscarruc(String truc, String bruc) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, ClientDetailFragment.newInstance(truc,bruc));
        transaction.commit();
    }

    private void rptbuscarname(String tname, String bname) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, ClientDetailFragment.newInstance(tname,bname));
        transaction.commit();
    }

}
