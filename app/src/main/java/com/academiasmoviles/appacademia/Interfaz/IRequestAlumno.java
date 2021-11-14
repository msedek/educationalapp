package com.academiasmoviles.appacademia.Interfaz;

import com.academiasmoviles.appacademia.Model.Alumno;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Juan on 29/12/2016.
 */

public interface IRequestAlumno {

    @GET("/getAlumnos")
    Call<ArrayList<Alumno>> getJSONAlumnos();

    @POST("/getAlumnos")
    Call<Alumno> addAlumno(@Body Alumno alumno);

    @PUT("/updateAlumno/{id}")
    Call<Alumno> updateAlumno(@Path("id") String alumnoId, @Body Alumno alumno);

    @DELETE("/getAlumnos/{id}")
    Call<Alumno> deleteAlumno(@Path("id") String alumnoId);
}
