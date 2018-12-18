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
            rptbuscarruc();
        }else {

            ApiService service = ApiServiceGenerator.createService(ApiService.class);

            Call<Cliente> call = service.showName(buscar);

            call.enqueue(new Callback<Cliente>() {
                @Override
                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                    try {

                        int statusCode = response.code();
                        Log.d(TAG, "HTTP status code: " + statusCode);

                        if (response.isSuccessful()) {

                            Cliente cliente = response.body();
                            Log.d(TAG, "cliente: " + cliente);

                            rptbuscarname();

                        } else {
                            Log.e(TAG, "onError: " + response.errorBody().string());
                            throw new Exception("Error en el servicio");
                        }

                    } catch (Throwable t) {
                        try {
                            Log.e(TAG, "onThrowable: " + t.toString(), t);
                            Toast.makeText(ClientActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }catch (Throwable x){}
                    }
                }

                @Override
                public void onFailure(Call<Cliente> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.toString());
                    Toast.makeText(ClientActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private void rptbuscarruc() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, ClientDetailFragment.newInstance());
        transaction.commit();
    }

    private void rptbuscarname() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, ClientDetailFragment.newInstance());
        transaction.commit();
    }

}
