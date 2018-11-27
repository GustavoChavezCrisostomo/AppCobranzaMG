package com.tesis.gchavez.appcobranzamg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.util.PreferencesManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MAIN_FORM_REQUEST = 100;

    private EditText loginInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferencesManager.getInstance(this);

        loginInput = (EditText) findViewById(R.id.login_input);
        passwordInput = (EditText)findViewById(R.id.password_input);

        // Verificar si ya está LOGUEADO
        if(PreferencesManager.getInstance().get(PreferencesManager.PREF_ISLOGGED) != null){
            startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
            finish();
        }
    }

    public void callLogin(View view) {
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(login.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Completar los campos requeridos", Toast.LENGTH_SHORT).show();
            return;
        }


    }

}
