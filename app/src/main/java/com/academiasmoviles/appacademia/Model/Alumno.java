package com.academiasmoviles.appacademia.Model;

import com.academiasmoviles.appacademia.R;

/**
 * Created by Juan on 13/12/2016.
 */

public class Alumno {

    // 1. Definir los atributos/propiedades.

    private String _id;
    private String nombres;
    private String apellidos;
    private String foto;
    private String direccion;
    private String latitud;
    private String longitud;
    private String estado;
    private String email;
    private double nota1;
    private double nota2;
    private double nota3;
    private double nota4;
    private double nota5;
    private double nota6;
    private double nota7;
    private double nota8;
    private double promedio;

    // 2. Métodos de acceso get/set.

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getEstado() {
        return estado;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getNota4() {
        return nota4;
    }

    public void setNota4(double nota4) {
        this.nota4 = nota4;
    }

    public double getNota5() {
        return nota5;
    }

    public void setNota5(double nota5) {
        this.nota5 = nota5;
    }

    public double getNota6() {
        return nota6;
    }

    public void setNota6(double nota6) {
        this.nota6 = nota6;
    }

    public double getNota7() {
        return nota7;
    }

    public void setNota7(double nota7) {
        this.nota7 = nota7;
    }

    public double getNota8() {
        return nota8;
    }

    public void setNota8(double nota8) {
        this.nota8 = nota8;
    }

    public double getPromedio() {
        return promedio;
    }

    // 3. Definir los métodos/comportamiento.

    public double calcularPromedio()
    {
        promedio = (nota1 + nota2 + nota3 + nota4 + nota5 + nota6 + nota7 + nota8) / 8;

        return promedio;
    }

    public String obtenerEstado()
    {
        if (promedio >= 13)
        {
            estado = "APROBADO";
        }
        else
        {
            estado = "DESAPROBADO";
        }

        return estado;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
