package com.academiasmoviles.appacademia;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.academiasmoviles.appacademia.Model.Alumno;

public class NotasActivity extends AppCompatActivity {

    CheckBox chb_asistio1;
    CheckBox chb_asistio2;
    CheckBox chb_asistio3;
    CheckBox chb_asistio4;
    CheckBox chb_asistio5;
    CheckBox chb_asistio6;
    CheckBox chb_asistio7;
    CheckBox chb_asistio8;
    EditText edt_nota1;
    EditText edt_nota2;
    EditText edt_nota3;
    EditText edt_nota4;
    EditText edt_nota5;
    EditText edt_nota6;
    EditText edt_nota7;
    EditText edt_nota8;
    EditText edt_promedio;
    Button btn_reiniciar;
    Button btn_calcular;

    Alumno objAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);



        // Crear instancia de la clase Alumno
        objAlumno = new Alumno();
        objAlumno.setNombres("Juan");
        objAlumno.setApellidos("Carlos");

        // Inicializando controles
        chb_asistio1 = (CheckBox) findViewById(R.id.chb_asistio1);
        chb_asistio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota1.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota1.requestFocus();
                }
                else
                {
                    edt_nota1.setText("");
                }
            }
        });

        chb_asistio2 = (CheckBox) findViewById(R.id.chb_asistio2);
        chb_asistio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota2.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota2.requestFocus();
                }
                else
                {
                    edt_nota2.setText("");
                }
            }
        });

        chb_asistio3 = (CheckBox) findViewById(R.id.chb_asistio3);
        chb_asistio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota3.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota3.requestFocus();
                }
                else
                {
                    edt_nota3.setText("");
                }
            }
        });

        chb_asistio4 = (CheckBox) findViewById(R.id.chb_asistio4);
        chb_asistio4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota4.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota4.requestFocus();
                }
                else
                {
                    edt_nota4.setText("");
                }
            }
        });

        chb_asistio5 = (CheckBox) findViewById(R.id.chb_asistio5);
        chb_asistio5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota5.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota5.requestFocus();
                }
                else
                {
                    edt_nota5.setText("");
                }
            }
        });

        chb_asistio6 = (CheckBox) findViewById(R.id.chb_asistio6);
        chb_asistio6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota6.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota6.requestFocus();
                }
                else
                {
                    edt_nota6.setText("");
                }
            }
        });

        chb_asistio7 = (CheckBox) findViewById(R.id.chb_asistio7);
        chb_asistio7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota7.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota7.requestFocus();
                }
                else
                {
                    edt_nota7.setText("");
                }
            }
        });

        chb_asistio8 = (CheckBox) findViewById(R.id.chb_asistio8);
        chb_asistio8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                edt_nota8.setEnabled(isChecked);

                if (isChecked)
                {
                    edt_nota8.requestFocus();
                }
                else
                {
                    edt_nota8.setText("");
                }
            }
        });

        edt_nota1 = (EditText) findViewById(R.id.edt_nota1);
        edt_nota2 = (EditText) findViewById(R.id.edt_nota2);
        edt_nota3 = (EditText) findViewById(R.id.edt_nota3);
        edt_nota4 = (EditText) findViewById(R.id.edt_nota4);
        edt_nota5 = (EditText) findViewById(R.id.edt_nota5);
        edt_nota6 = (EditText) findViewById(R.id.edt_nota6);
        edt_nota7 = (EditText) findViewById(R.id.edt_nota7);
        edt_nota8 = (EditText) findViewById(R.id.edt_nota8);
        edt_promedio = (EditText) findViewById(R.id.edt_promedio);

        btn_reiniciar = (Button) findViewById(R.id.btn_reiniciar);
        btn_reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reiniciarNotas();
            }
        });

        btn_calcular = (Button) findViewById(R.id.btn_calcular);
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Calculamos el promedio
                double nota1 = 0;
                double nota2 = 0;
                double nota3 = 0;
                double nota4 = 0;
                double nota5 = 0;
                double nota6 = 0;
                double nota7 = 0;
                double nota8 = 0;

                if (!edt_nota1.getText().toString().equals(""))
                {
                    nota1 = Double.parseDouble(edt_nota1.getText().toString());

                    if (nota1 > 20)
                    {
                        nota1 = 20;
                        edt_nota1.setText(nota1 + "");
                    }
                }

                if (!edt_nota2.getText().toString().equals(""))
                {
                    nota2 = Double.parseDouble(edt_nota2.getText().toString());

                    if (nota2 > 20)
                    {
                        nota2 = 20;
                        edt_nota2.setText(nota2 + "");
                    }
                }

                if (!edt_nota3.getText().toString().equals(""))
                {
                    nota3 = Double.parseDouble(edt_nota3.getText().toString());

                    if (nota3 > 20)
                    {
                        nota3 = 20;
                        edt_nota3.setText(nota3 + "");
                    }
                }

                if (!edt_nota4.getText().toString().equals(""))
                {
                    nota4 = Double.parseDouble(edt_nota4.getText().toString());

                    if (nota4 > 20)
                    {
                        nota4 = 20;
                        edt_nota4.setText(nota4 + "");
                    }
                }

                if (!edt_nota5.getText().toString().equals(""))
                {
                    nota5 = Double.parseDouble(edt_nota5.getText().toString());

                    if (nota5 > 20)
                    {
                        nota5 = 20;
                        edt_nota5.setText(nota5 + "");
                    }
                }

                if (!edt_nota6.getText().toString().equals(""))
                {
                    nota6 = Double.parseDouble(edt_nota6.getText().toString());

                    if (nota6 > 20)
                    {
                        nota6 = 20;
                        edt_nota6.setText(nota6 + "");
                    }
                }

                if (!edt_nota7.getText().toString().equals(""))
                {
                    nota7 = Double.parseDouble(edt_nota7.getText().toString());

                    if (nota7 > 20)
                    {
                        nota7 = 20;
                        edt_nota7.setText(nota7 + "");
                    }
                }

                if (!edt_nota8.getText().toString().equals(""))
                {
                    nota8 = Double.parseDouble(edt_nota8.getText().toString());

                    if (nota8 > 20)
                    {
                        nota8 = 20;
                        edt_nota8.setText(nota8 + "");
                    }
                }

                // Calcular el promedio.
                objAlumno.setNota1(nota1);
                objAlumno.setNota2(nota2);
                objAlumno.setNota3(nota3);
                objAlumno.setNota4(nota4);
                objAlumno.setNota5(nota5);
                objAlumno.setNota6(nota6);
                objAlumno.setNota7(nota7);
                objAlumno.setNota8(nota8);

                //double promedio = (nota1 + nota2 + nota3 + nota4) / 4;
                double promedio = objAlumno.calcularPromedio();
                String estado = objAlumno.obtenerEstado();

                edt_promedio.setText(promedio + " - " + estado);

                Toast.makeText(getApplicationContext(), promedio + " - " + estado, Toast.LENGTH_SHORT).show();

                Log.d("APP_ACADEMIA", "Promedio " + promedio + " - " + estado);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        imprimirLog("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        imprimirLog("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        imprimirLog("onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        imprimirLog("onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        imprimirLog("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        imprimirLog("onDestroy");
    }

    private void imprimirLog(String mensaje)
    {
        Log.d("CICLO DE VIDA", mensaje);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_notas, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.accion_reiniciar:

                mostrarMensaje("Acción reiniciar");

                reiniciarNotas();

                return true;

            case R.id.accion_grabar:

                mostrarMensaje("Acción grabar");

                mostrarDialogo("Confirmación", "¿Seguro de grabar las notas?");

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void reiniciarNotas()
    {
        chb_asistio1.setChecked(false);
        chb_asistio2.setChecked(false);
        chb_asistio3.setChecked(false);
        chb_asistio4.setChecked(false);
        chb_asistio5.setChecked(false);
        chb_asistio6.setChecked(false);
        chb_asistio7.setChecked(false);
        chb_asistio8.setChecked(false);

        edt_nota1.setText("");
        edt_nota2.setText("");
        edt_nota3.setText("");
        edt_nota4.setText("");
        edt_nota5.setText("");
        edt_nota6.setText("");
        edt_nota7.setText("");
        edt_nota8.setText("");
    }

    // 1. Notificaciones: Toast

    private void mostrarMensaje(String mensaje)
    {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    // 2. Notificaciones: Barra de estado

    private void mostrarAlerta(String titulo, String mensaje)
    {
        // Creamos la notificación
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(NotasActivity.this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setLargeIcon((((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setContentInfo("4")
                .setAutoCancel(true)
                .setTicker("Alerta!");

        // Establecer la acción
        Intent notIntent = new Intent(NotasActivity.this, CrearAlumnoActivity.class);

        PendingIntent contIntent = PendingIntent.getActivity(NotasActivity.this, 0, notIntent, 0);

        mBuilder.setContentIntent(contIntent);

        // Lanzamos la notificación
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1000, mBuilder.build());
    }

    // 3. Notificaciones: Diálogos

    private void mostrarDialogo(String titulo, String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(mensaje)
                .setTitle(titulo)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmación Aceptada.");
                        dialog.cancel();

                        mostrarAlerta("Grabar nota", "La nota ha sido grabada con éxito.");
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmación Cancelada.");
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
