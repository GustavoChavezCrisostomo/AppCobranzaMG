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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.models.Banco;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.models.Cobranza;
import com.tesis.gchavez.appcobranzamg.models.Documento;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;
import com.tesis.gchavez.appcobranzamg.util.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class InformeActivity extends AppCompatActivity {

    private static final String TAG = InformeActivity.class.getSimpleName();
    private String cliente_id;

    private BottomNavigationView bottomNavigationView;
    private Spinner tDoc;
    private Spinner pago;
    private Spinner banco;
    private EditText docSeach;
    private EditText montoInput;
    private EditText chequeInput;
    private EditText opeInpt;
    private EditText obsInput;
    private TextView nameText;
    private TextView lugarTxt;
    private TextView rucTxt;

    private String numDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
        setTitle("Informe" );

        docSeach = findViewById(R.id.numDoc_input);
        montoInput = findViewById(R.id.monto_input);
        chequeInput = findViewById(R.id.numcheque_input);
        opeInpt = findViewById(R.id.numOpe_input);
        obsInput = findViewById(R.id.txt_obsc);
        nameText = findViewById(R.id.txt_name);
        lugarTxt = findViewById(R.id.txt_lugar);
        rucTxt = findViewById(R.id.txt_ruc);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

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
        tDoc = findViewById(R.id.spinner_doc);
        List<String> listDoc = Arrays.asList(getResources().getStringArray(R.array.TDoc));
        ArrayAdapter<String> spinnerAdapterDoc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDoc);
        spinnerAdapterDoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tDoc.setAdapter(spinnerAdapterDoc);

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

    public void callSearchDoc(View view){
        numDoc = docSeach.getText().toString();

        if(numDoc.isEmpty()){
            Toast.makeText(this, "Completar el num de documento", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Documento> call = service.show( Integer.parseInt(numDoc));

        call.enqueue(new Callback<Documento>() {
            @Override
            public void onResponse(Call<Documento> call, Response<Documento> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {
                        //datos del documento
                        Documento documento = response.body();
                        Log.d(TAG, "document: " + documento);

                        Menu miMenu= bottomNavigationView.getMenu();
                        miMenu.findItem(R.id.save).setEnabled(true);

                        //datos del cliente
                        List<Cliente> cli = new ArrayList<>();
                        cli.add(documento.getCliente_c());
                        Log.d(TAG, "client: " + cli);

                        cliente_id = cli.get(0).getId().toString();
                        nameText.setText( cli.get(0).getNombre());
                        lugarTxt.setText(cli.get(0).getDistrito());
                        rucTxt.setText(cli.get(0).getRuc());

                    } else {
                        docSeach.getText().clear();
                        nameText.setText("aaaaaaa");
                        lugarTxt.setText("aaaaaaa");
                        rucTxt.setText("545485451257");

                        Menu miMenu= bottomNavigationView.getMenu();
                        miMenu.findItem(R.id.save).setEnabled(false);

                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("N° documento no encontrado");
                    }

                } catch (Throwable t) {
                    try {
                        docSeach.getText().clear();
                        Menu miMenu= bottomNavigationView.getMenu();
                        miMenu.findItem(R.id.save).setEnabled(false);
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(InformeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Documento> call, Throwable t) {
                docSeach.getText().clear();
                Menu miMenu= bottomNavigationView.getMenu();
                miMenu.findItem(R.id.save).setEnabled(false);
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(InformeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

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

        String precio = montoInput.getText().toString();
        String nCheque = chequeInput.getText().toString();
        String nOpe = opeInpt.getText().toString();
        String obs = obsInput.getText().toString();
        if(precio.isEmpty()){
            precio = "0";
        }
        if (nCheque.isEmpty()){
            nCheque = "0";
        }
        if (nOpe.isEmpty()){
            nOpe = "0";
        }
        if (obs.isEmpty()){
            obs = "Sin observacion";
        }

        Date d=new Date();
        SimpleDateFormat fecc=new SimpleDateFormat("dd-MM-yyyy");
        final String fecha = fecc.format(d);

        final String usuario_id =PreferencesManager.getInstance().get(PreferencesManager.PREF_ID);
        final String serie = "123548";
        final String tipo = tDoc.getSelectedItem().toString();
        final String distritoCli = lugarTxt.getText().toString();
        final String rucCli = rucTxt.getText().toString();
        final String tipoPago = pago.getSelectedItem().toString();
        final double monto = Double.parseDouble(precio);
        final String numCheque = nCheque ;
        final int banco_id = banco.getSelectedItemPosition() + 1;//+1 porq en posicioon inicia en 0
        final String numOpe = nOpe;
        final String observaciones = obs;

        if(tipoPago.equals("--Seleccione--") || precio.equals("0")){
            Toast.makeText(this, "Completar los campos obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "create: " + usuario_id +"/"+ fecha +"/"+ serie +"/"+ numDoc +"/"+ tipo +"/"+ cliente_id +"/"+ distritoCli +"/"+ rucCli +"/"+ tipoPago +"/"+ monto +"/"+ numCheque +"/"+ banco_id +"/"+ numOpe +"/"+ observaciones);

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Cobranza> call = null;

        call = service.store(usuario_id, serie, fecha,tipo,numDoc,Integer.parseInt(cliente_id),distritoCli,rucCli,tipoPago,monto,numCheque,banco_id,numOpe,observaciones);

        call.enqueue(new Callback<Cobranza>() {
            @Override
            public void onResponse(Call<Cobranza> call, Response<Cobranza> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Cobranza responseMessage = response.body();
                        Log.d(TAG, "responseMessage: " + responseMessage);

                        Toast.makeText(InformeActivity.this,"Se guardo conforme",Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(InformeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Cobranza> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(InformeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }

}
