/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos4;

import java.io.Serializable;

/**
 *
 * @author eduar
 */
public class Asignatura implements Serializable{
    String nombre;
    String curso;
    
    public Asignatura() {
        nombre = "";
        curso = "";
    }
    
    public Asignatura(String n, String c) {
        nombre = n;
        curso = c;
    }
    
    // Getters:
    public String getAsig(){
        return nombre;
    }
    
    public String getCurso(){
        return curso;
    }
    
    // Setters:
    public void setAsig(String nombre) {
        this.nombre = nombre;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
}
