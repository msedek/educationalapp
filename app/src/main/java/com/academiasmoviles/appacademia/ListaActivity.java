package com.academiasmoviles.appacademia;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.academiasmoviles.appacademia.Adapter.AlumnoAdapter;
import com.academiasmoviles.appacademia.Interfaz.IRequestAlumno;
import com.academiasmoviles.appacademia.Model.Alumno;
import com.academiasmoviles.appacademia.SQLite.DBHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaActivity extends AppCompatActivity {

    RecyclerView rec_lista;
    FloatingActionButton btn_nuevo;

    ArrayList<Alumno> lista_Alumno;
    private AlumnoAdapter mAlumnoAdapter;

    private static final int ACCION_CREAR = 1;
    private static final int ACCION_EDITAR = 2;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Cambiamos el titulo
        setTitle("Lista de Alumnos");

        // Inicializar la bd
        myDB = DBHelper.GetDBHelper(this);

        // Inicializar los controles
        rec_lista = (RecyclerView) findViewById(R.id.rec_lista);

        btn_nuevo = (FloatingActionButton) findViewById(R.id.btn_nuevo);

        btn_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CrearAlumnoActivity.class);

                startActivityForResult(intent, ACCION_CREAR);
            }
        });

        //inicializarLista();

        //setearAdaptador();

        configurarOrientacionLayout();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Validamos si hay internet
        if (isNetworkConnected())
        {
            // AsyncTask: Hacer la llamada
            //loadTaskAlumnos();

            // Retrofit: Hacer la llamada
            loadRetrofitAlumno();
        }
        else
        {
            // Recuperar los alumnos
            lista_Alumno = myDB.listAlumnos();

            // Actualizar recyclerview
            setearAdaptador();
        }

    }

    private void loadTaskAlumnos()
    {
        AlumnosTask task = new AlumnosTask();

        try {
            task.execute(new URL("https://murmuring-earth-44871.herokuapp.com/getAlumnos"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void loadRetrofitAlumno()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://murmuring-earth-44871.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRequestAlumno request = retrofit.create(IRequestAlumno.class);

        Call<ArrayList<Alumno>> call = request.getJSONAlumnos();

        call.enqueue(new Callback<ArrayList<Alumno>>() {
            @Override
            public void onResponse(Call<ArrayList<Alumno>> call, Response<ArrayList<Alumno>> response) {

                ArrayList<Alumno> lista = response.body();

                //lista_Alumno = lista;

                // Eliminar los alumnos
                myDB.deleteAllAlumno();

                // Insertar los alumnos
                for (Alumno alumno: lista)
                {
                    myDB.addAlumno(alumno);
                }

                // Recuperar los alumnos
                lista_Alumno = myDB.listAlumnos();

                // Actualizar recyclerview
                setearAdaptador();
            }

            @Override
            public void onFailure(Call<ArrayList<Alumno>> call, Throwable t) {

                mostrarMensaje("Error: " + t.getMessage());
            }
        });
    }

    private void inicializarLista()
    {
        lista_Alumno = new ArrayList<>();

        Alumno alumno1 = new Alumno();
        alumno1.set_id("1");
        alumno1.setNombres("Sandro");
        alumno1.setApellidos("Altamirano");
        lista_Alumno.add(alumno1);

        Alumno alumno2 = new Alumno();
        alumno2.set_id("2");
        alumno2.setNombres("Carlos");
        alumno2.setApellidos("Aquino");
        lista_Alumno.add(alumno2);

        Alumno alumno3 = new Alumno();
        alumno3.set_id("3");
        alumno3.setNombres("Miguel");
        alumno3.setApellidos("Montrone");
        lista_Alumno.add(alumno3);

        Alumno alumno4 = new Alumno();
        alumno4.set_id("4");
        alumno4.setNombres("Jesus");
        alumno4.setApellidos("Sanchez");
        lista_Alumno.add(alumno4);

        Alumno alumno5 = new Alumno();
        alumno5.set_id("5");
        alumno5.setNombres("Miguel");
        alumno5.setApellidos("Sedek");
        lista_Alumno.add(alumno5);

        Alumno alumno6 = new Alumno();
        alumno6.set_id("6");
        alumno6.setNombres("Ismael");
        alumno6.setApellidos("Carrera");
        lista_Alumno.add(alumno6);
    }

    private void setearAdaptador()
    {
        mAlumnoAdapter = new AlumnoAdapter(getApplicationContext(), lista_Alumno);

        mAlumnoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position;

                if (v.getId() == R.id.ibt_ubicacion)
                {
                    // Abrir pantalla de mapa
                    mostrarMensaje("Abrir mapa");

                    position = rec_lista.getChildPosition((View) v.getParent());

                    Alumno alumno = lista_Alumno.get(position);

                    Intent intent = new Intent(getApplicationContext(), MapaActivity.class);

                    intent.putExtra("ID", alumno.get_id());

                    startActivity(intent);
                }
                else
                {
                    position = rec_lista.getChildPosition(v);

                    Alumno alumno = lista_Alumno.get(position);

                    Intent intent = new Intent(getApplicationContext(), EditarAlumnoActivity.class);

                    intent.putExtra("ID", alumno.get_id());
                    intent.putExtra("NOMBRES", alumno.getNombres());
                    intent.putExtra("APELLIDOS", alumno.getApellidos());
                    intent.putExtra("FOTO", alumno.getFoto());
                    intent.putExtra("EMAIL", alumno.getEmail());
                    intent.putExtra("DIRECCION", alumno.getDireccion());
                    intent.putExtra("LATITUD", alumno.getLatitud());
                    intent.putExtra("LONGITUD", alumno.getLongitud());

                    startActivityForResult(intent, ACCION_EDITAR);
                }

                /*
                Toast.makeText(getApplicationContext(),
                        alumno.getNombres(), Toast.LENGTH_SHORT).show();

                mAlumnoAdapter.removeItem(position);
                */
            }
        });

        rec_lista.setAdapter(mAlumnoAdapter);

        configurarAnimacion();
    }

    private void configurarAnimacion()
    {
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);

        rec_lista.setItemAnimator(itemAnimator);
    }

    private void configurarOrientacionLayout()
    {
        rec_lista.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_lista, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.accion_informacion:

                Intent intentInformacion = new Intent(getApplicationContext(), InformacionActivity.class);

                startActivity(intentInformacion);

                return true;

            case R.id.accion_ayuda:

                Intent intentAyuda = new Intent(getApplicationContext(), TabsActivity.class);

                startActivity(intentAyuda);

                return true;

            case R.id.accion_cerrarsesion:

                guardarPreferencias("", "");

                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(intentLogin);

                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void guardarPreferencias(String usuario, String contrasenia) {
        SharedPreferences prefs = getSharedPreferences("MyPreferences1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("usuario", usuario);
        editor.putString("contrasenia", contrasenia);

        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED)
        {
            mostrarMensaje("El usuario ha cancelado la acción.");
        }
        else
        {
            switch (requestCode)
            {
                case ACCION_CREAR:

                    mostrarMensaje("Acción crear.");

                    break;

                case ACCION_EDITAR:

                    mostrarMensaje("Acción editar.");

                    break;
            }
        }
    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
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

                        mostrarAlerta("Editar Alumno", "El alumno ha sido editado con éxito.");
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


    // Crear Tarea Asincrona
    public class AlumnosTask extends AsyncTask<URL, Void, List<Alumno>>
    {
        @Override
        protected List<Alumno> doInBackground(URL... params) {

            List alumnos = null;
            HttpURLConnection con = null;

            try
            {
                // 1. Establecer la conexión
                con = (HttpURLConnection) params[0].openConnection();

                con.setConnectTimeout(15000);

                con.setReadTimeout(10000);


                // 2. Obtener el estado de la conexión
                int statusCode = con.getResponseCode();

                if (statusCode == 200) // OK
                {
                    // 3. Parsear JSON -> Alumno.class
                    InputStream inputStream = new BufferedInputStream(con.getInputStream());

                    Reader reader = new InputStreamReader(inputStream);

                    GsonBuilder gsonBuilder = new GsonBuilder();

                    Gson gson = gsonBuilder.create();

                    alumnos = Arrays.asList(gson.fromJson(reader, Alumno[].class));
                }
                else
                {
                    alumnos = new ArrayList();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                con.disconnect();
            }

            // 4. Devolver la información
            return alumnos;
        }

        @Override
        protected void onPostExecute(List<Alumno> alumnos)
        {
            // Asignar los objetos al adapter
            if (alumnos != null)
            {
                lista_Alumno = new ArrayList<>(alumnos);

                setearAdaptador();
            }
            else
            {
                mostrarMensaje("Ocurrió un error de parse JSON.");
            }

        }
    }
}
