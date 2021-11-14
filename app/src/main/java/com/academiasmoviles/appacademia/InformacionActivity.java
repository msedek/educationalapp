package com.academiasmoviles.appacademia;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.academiasmoviles.appacademia.Fragment.AcercadeFragment;
import com.academiasmoviles.appacademia.Fragment.MisionFragment;
import com.academiasmoviles.appacademia.Fragment.VisionFragment;

import java.util.Objects;

public class InformacionActivity extends AppCompatActivity {

    Button btn_acercade;
    Button btn_mision;
    Button btn_vision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        // ActionBar: Cambiamos el titulo
        setTitle("Información");

        // ActionBar: Habilitamos el botón atrás
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        inicializarControles();
    }

    private void inicializarControles()
    {
        btn_acercade = findViewById(R.id.btn_acercade);
        btn_acercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeFragment(new AcercadeFragment());
            }
        });

        btn_mision = findViewById(R.id.btn_mision);
        btn_mision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeFragment(new MisionFragment());
            }
        });

        btn_vision = findViewById(R.id.btn_vision);
        btn_vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeFragment(new VisionFragment());
            }
        });
    }

    private void changeFragment(Fragment fragment)
    {
        // Paso 1: Obtener la instancia del administrador de fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Paso 2: Crear una nueva transacción
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Paso 3: Reemplazar un fragmento
        fragmentTransaction.replace(R.id.frm_output, fragment);

        // Paso 4: Confirmar el cambio
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:

                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
