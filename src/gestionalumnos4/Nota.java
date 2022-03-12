/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos4;

/**
 *
 * @author eduar
 */
public class Nota {
    Asignatura asig;
    float nota;
    String convocatoria;
    
    public Nota() {
    Asignatura asig = new Asignatura();
    nota = (float) 0;
    convocatoria = "";
    
    }
    public Nota(Asignatura asignatura, float n, String convocatoria) {
        this.asig = asignatura;
        this.nota = (float) n;
        this.convocatoria = convocatoria;
    
    }
    // Getters:
    public float getNota(){
        return this.nota;
    } 
    public String getConvocatoria(){
        return this.convocatoria; 
    }
    
}
