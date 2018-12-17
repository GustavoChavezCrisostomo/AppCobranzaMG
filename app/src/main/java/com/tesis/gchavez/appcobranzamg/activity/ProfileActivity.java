package com.tesis.gchavez.appcobranzamg.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.models.Usuario;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;
import com.tesis.gchavez.appcobranzamg.util.PreferencesManager;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String TAG = ProfileActivity.class.getSimpleName();

    private int id;

    public ProgressBar progressBar;
    public TextView userText;
    public TextView telfText;
    public TextView emailText;
    public TextView zoneText;
    public TextView clienAsigText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Perfil" );
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ProgressBar Gone
        //ProfileActivity.this.findViewById(R.id.main_progress).setVisibility(View.GONE);

        id = Integer.parseInt(PreferencesManager.getInstance().get(PreferencesManager.PREF_ID));

        progressBar = findViewById(R.id.main_progress);
        userText = findViewById(R.id.name_text);
        telfText = findViewById(R.id.phone_text);
        emailText = findViewById(R.id.email_text);
        zoneText = findViewById(R.id.zone_text);
        clienAsigText = findViewById(R.id.clients_txt);

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Usuario> call = service.showUsuario(id);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Usuario usuario = response.body();
                        Log.d(TAG, "usuario: " + usuario);

                        userText.setText(usuario.getNombre());
                        telfText.setText(usuario.getTelefono());
                        emailText.setText(usuario.getCorreo());
                        zoneText.setText(usuario.getZona());
                        clienAsigText.setText(Integer.toString(usuario.getClienAsig()));

                        // ProgressBar Gone
                        ProfileActivity.this.findViewById(R.id.main_progress).setVisibility(View.GONE);

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                // ProgressBar Gone
                ProfileActivity.this.findViewById(R.id.main_progress).setVisibility(View.GONE);
            }
        });

    }
}
