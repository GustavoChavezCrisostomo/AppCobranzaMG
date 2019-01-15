package com.tesis.gchavez.appcobranzamg.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.models.Banco;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformeActivity extends AppCompatActivity {

    private static final String TAG = InformeActivity.class.getSimpleName();
    private Spinner doc, pago, banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
        setTitle("Informe" );

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    delete();
                    break;
                case R.id.save:
                    save();
                    break;
            }

            return true;
            }
        });

        //Spinner Tipo de Doc
        doc = findViewById(R.id.spinner_doc);
        List<String> listDoc = Arrays.asList(getResources().getStringArray(R.array.TDoc));
        ArrayAdapter<String> spinnerAdapterDoc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDoc);
        spinnerAdapterDoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doc.setAdapter(spinnerAdapterDoc);

        //Spinner Tipo de Pago
        pago = findViewById(R.id.spinner_tpago);
        List<String> listPago = Arrays.asList(getResources().getStringArray(R.array.TPago));
        ArrayAdapter<String> spinnerAdapterPago = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPago);
        spinnerAdapterPago.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pago.setAdapter(spinnerAdapterPago);

        //Spinner Banco
        banco = findViewById(R.id.spinner_bank);
        iniBank();

    }

    public void delete(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("¿Estas seguro de eliminar el informe?");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(InformeActivity.this,"Continua con el informe",Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void save(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("¿Seguro de guardar el informe?");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            //corregir
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                saveDB();
                Toast.makeText(InformeActivity.this,"Se guardo conforme",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(InformeActivity.this,"Verifica el informe antes de guardar",Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void iniBank(){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<List<Banco>> call = service.getBanco();

        call.enqueue(new Callback<List<Banco>>() {
            @Override
            public void onResponse(Call<List<Banco>> call, Response<List<Banco>> response) {

                List<Banco> bancos = response.body();
                Log.d(TAG, "Tecnicos: " + bancos);

                List<String> list_bank = new ArrayList<>();
                for (Banco banco : bancos) {
                    list_bank.add(banco.getNombre());
                }

                ArrayAdapter<String> spinnerAdapter_bank = new ArrayAdapter<>(InformeActivity.this, android.R.layout.simple_spinner_item, list_bank);
                spinnerAdapter_bank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                banco.setAdapter(spinnerAdapter_bank);
            }

            @Override
            public void onFailure(Call<List<Banco>> call, Throwable t) {

            }
        });
    }

    public void saveDB(){

    }

}
