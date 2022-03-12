/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos4;

import java.io.Serializable;
import java.util.Hashtable;

/**
 *
 * @author eduar
 */
public class Alumno implements Serializable{
    String nombre;
    String apellido;
    String DNI;
//    Hashtable<String, Float> notas;
    Hashtable<String, Float> notas = new Hashtable<>();

    public Alumno() {
        this.nombre = "";
        this.apellido = "";
        this.DNI = "";
//        this.notas = new Hashtable<>();
//        notas = new Hashtable<>();
//        this.notas.put("", 0F);
    }

    public Alumno(String nombre, String apellido, String DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
//        this.notas = new Hashtable<>();
//        this.notas.put("vacio", 0F);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Hashtable<String, Float> getNotas() {
        return notas;
    }

    public void setNotas(String asig, float nota) {
          this.notas.put(asig, nota);
//        this.notas = nuevasNotas;
    }
    
}
