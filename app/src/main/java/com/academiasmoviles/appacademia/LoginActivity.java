package com.academiasmoviles.appacademia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edt_usuario;
    EditText edt_contrasenia;
    CheckBox chb_recordarme;
    Button btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Cambiamos el titulo
        setTitle("Iniciar sesión");

        inicializarControles();

        validarPreferencias();
    }

    private void inicializarControles()
    {
        edt_usuario = (EditText) findViewById(R.id.edt_usuario);

        edt_contrasenia = (EditText) findViewById(R.id.edt_contrasenia);

        chb_recordarme = (CheckBox) findViewById(R.id.chb_recordarme);

        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chb_recordarme.isChecked())
                {
                    guardarPreferencias(edt_usuario.getText().toString(),
                            edt_contrasenia.getText().toString());

                    ingresar();
                }
                else
                {
                    guardarPreferencias("", "");

                    ingresar();
                }
            }
        });
    }

    private void guardarPreferencias(String usuario, String contrasenia) {
        SharedPreferences prefs = getSharedPreferences("MyPreferences1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("usuario", usuario);
        editor.putString("contrasenia", contrasenia);

        editor.commit();
    }

    private void validarPreferencias()
    {
        SharedPreferences prefs = getSharedPreferences("MyPreferences1", Context.MODE_PRIVATE);

        String usuario = prefs.getString("usuario", "");

        if (usuario != "")
        {
            ingresar();
        }
    }

    private void ingresar()
    {
        Toast.makeText(getApplicationContext(), "Método ingresar", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), ListaActivity.class);

        startActivity(intent);

        finish();
    }
}
