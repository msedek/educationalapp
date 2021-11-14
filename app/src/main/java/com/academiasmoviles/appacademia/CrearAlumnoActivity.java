package com.academiasmoviles.appacademia;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.academiasmoviles.appacademia.Interfaz.IRequestAlumno;
import com.academiasmoviles.appacademia.Model.Alumno;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearAlumnoActivity extends AppCompatActivity {

    EditText edt_nombres;
    EditText edt_apellidos;
    EditText edt_foto;
    EditText edt_email;
    EditText edt_direccion;
    EditText edt_latitud;
    EditText edt_longitud;
    ImageView imv_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alumno);

        // Cambiamos el titulo
        setTitle("Crear Alumno");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializar controles
        inicializarControles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_crear_alumno, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:

                finish();

                return true;

            case R.id.accion_grabar:

                mostrarMensaje("Acción grabar");

                mostrarDialogo("Confirmación", "¿Seguro de crear el alumno?");

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void inicializarControles()
    {
        edt_nombres = (EditText) findViewById(R.id.edt_nombres);
        edt_apellidos = (EditText) findViewById(R.id.edt_apellidos);
        edt_foto = (EditText) findViewById(R.id.edt_foto);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_direccion = (EditText) findViewById(R.id.edt_direccion);
        edt_latitud = (EditText) findViewById(R.id.edt_latitud);
        edt_longitud = (EditText) findViewById(R.id.edt_longitud);
        imv_foto = (ImageView) findViewById(R.id.imv_foto);

        Bitmap bitmap = ((((BitmapDrawable) getResources().getDrawable(R.drawable.user_empty)).getBitmap()));
        bitmap = getRoundBitmap(bitmap);
        imv_foto.setImageBitmap(bitmap);
    }

    // Redondear imagen
    public static Bitmap getRoundBitmap(Bitmap bitmap) {

        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());

        Bitmap bitmapRounded = Bitmap.createBitmap(min, min, bitmap.getConfig());

        Canvas canvas = new Canvas(bitmapRounded);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0.0f, 0.0f, min, min)), min/2, min/2, paint);

        return bitmapRounded;
    }

    public void saveRetrofitAlumno()
    {
        String nombres = edt_nombres.getText().toString();
        String apellidos = edt_apellidos.getText().toString();
        String email = edt_email.getText().toString();
        String direccion = edt_direccion.getText().toString();
        String latitud = edt_latitud.getText().toString();
        String longitud = edt_longitud.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://murmuring-earth-44871.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRequestAlumno request = retrofit.create(IRequestAlumno.class);

        Alumno alumno = new Alumno();
        alumno.setNombres(nombres);
        alumno.setApellidos(apellidos);
        alumno.setEmail(email);
        alumno.setDireccion(direccion);
        alumno.setLatitud(latitud);
        alumno.setLongitud(longitud);

        Call<Alumno> call = request.addAlumno(alumno);

        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {

                Log.i("RETROFIT", "Termino POST");

                setResult(RESULT_OK);

                finish();
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {

                Log.d("Error agregar alumno " , t.getMessage().toString());
            }
        });
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
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setLargeIcon((((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setContentInfo("4")
                .setAutoCancel(true)
                .setTicker("Alerta!");

        // Establecer la acción
        Intent notIntent = new Intent(getApplicationContext(), ListaActivity.class);

        PendingIntent contIntent = PendingIntent.getActivity(getApplicationContext(), 0, notIntent, 0);

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

                        mostrarAlerta("Crear Alumno", "El alumno ha sido creado con éxito.");

                        saveRetrofitAlumno();
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
