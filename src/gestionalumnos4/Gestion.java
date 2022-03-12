/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author eduar
 */
public class Gestion {
    Hashtable susNotas = new Hashtable(); 
    
    // GESTION DE ALUMNOS:
    public void leerAlumno() throws FileNotFoundException, IOException{
        ArrayList <Alumno> estudiantes =  new ArrayList();
        File fichero = new File("alumnos.dat");//declara el fichero
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
        try {
            FileInputStream fis = new FileInputStream(fichero);
            dataIS = new ObjectInputStream(fis);
            estudiantes = (ArrayList) dataIS.readObject();
            System.out.println(estudiantes.size()); 
            
            for (int j = 0; j < estudiantes.size(); j++) {
                Alumno muestra = estudiantes.get(j);
                System.out.printf("Nombre: %s, Apellido: %s, DNI: %s %n",muestra.getNombre(), muestra.getApellido(), muestra.getDNI());
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error:" + e); 
        }
        dataIS.close(); // cerrar stream de entrada}
    }
    
    public void insertarAlumno(Alumno nuevoAlumno) throws FileNotFoundException, IOException{
    
        boolean seRepite = false; // Controla si el alumno ya existe o no
        
        ArrayList <Alumno> estudiantes =  new ArrayList();
        File archivo = new File("alumnos.dat");
        
        if(archivo.exists()){ // Comprobamos si el archivo existe
            try {
                estudiantes = cargarArchivoAlumnos(); // Cargamos el archivo de alumnos que ya existen

                for (int i = 0; i < estudiantes.size(); i++) { // Recorremos la lista de estudiantes
                    Alumno aInscrito = estudiantes.get(i); // Seleccionamos uno`por uno a los alumnos
                    if (nuevoAlumno.getDNI().equals(aInscrito.getDNI())) { // Si existe el dni en alguno de los alumnos, no se inserta
                        seRepite = true; // Si se repite, no se añade
                        System.out.println("El DNI existe");
                    }
                }
                if (!seRepite) {
                    estudiantes.add(nuevoAlumno); // Añadimos el alumno si no existe
                }
                archivo.delete(); // Borro el archivo para crear uno nuevo con los datos nuevos
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ha ocurrido una excepcion: "+e);
            }
        }else{ // Si no existe el archivo, añado el alumno nuevo a la lista y creo el archivo
            estudiantes.add(nuevoAlumno);
        }        
        FileOutputStream archivoAlumnos = new FileOutputStream(archivo, false); // al ponerlo en true se corrompe el archivo
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAlumnos);  
            escribeFichero.writeObject(estudiantes); // Insetamos la lista de estudiantes
        }catch(IOException ex){
            System.out.println("error al guardar al archivo");
            System.out.println(ex);
        }finally{
            archivoAlumnos.close();
        }    
    }
    
    public void modAlumno(Alumno alumnoBase, String nuevoNombre, String nuevoApe, String nuevoDNI) throws FileNotFoundException, IOException{
        boolean seRepite = false; // Controla si el alumno ya existe o no
        
        ArrayList <Alumno> estudiantes =  new ArrayList();
        File archivo = new File("alumnos.dat");
        
        if(archivo.exists()){ // Comprobamos si el archivo existe
            try {
                estudiantes = cargarArchivoAlumnos(); // Cargamos el archivo de alumnos que ya existen

                for (int i = 0; i < estudiantes.size(); i++) { // Recorremos la lista de estudiantes
                    Alumno aInscrito = estudiantes.get(i); // Seleccionamos uno por uno a los alumnos
                    if (alumnoBase.getDNI().equals(aInscrito.getDNI())) { // Si coincide el DNI, es el alumno que quermemos modificar
                        seRepite = true; // Si se repite, no se añade
                        
                        // Cambiamos los datos del alumno:
                        aInscrito.setNombre(nuevoNombre);
                        aInscrito.setApellido(nuevoApe);
                        aInscrito.setDNI(nuevoDNI);
                        System.out.println("Modificacion con exito");
                        
                    }
                }
                if (!seRepite) {
                    estudiantes.add(alumnoBase); // Añadimos el alumno si no existe
                }
                archivo.delete(); // Borro el archivo para crear uno nuevo con los datos nuevos
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ha ocurrido una excepcion: "+e);
            }
        }else{ // Si no existe el archivo, añado el alumno nuevo a la lista y creo el archivo
            estudiantes.add(alumnoBase);
        }        
        FileOutputStream archivoAlumnos = new FileOutputStream(archivo, false); // al ponerlo en true se corrompe el archivo
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAlumnos);  
            escribeFichero.writeObject(estudiantes); // Insetamos la lista de estudiantes
        }catch(IOException ex){
            System.out.println("error al guardar al archivo");
            System.out.println(ex);
        }finally{
            archivoAlumnos.close();
        }    
    }
    
    public void eliminarAlumno(Alumno borrarAlumno) throws IOException{
       
        ArrayList <Alumno> estudiantes =  new ArrayList();
        ArrayList <Alumno> estudianteTarget =  new ArrayList(); // Lista con los alumnos que NO he borrado
        
        File archivo = new File("alumnos.dat");
        
        if(archivo.exists()){ // Comprobamos si el archivo existe
            try {
                estudiantes = cargarArchivoAlumnos(); // Cargamos el archivo de alumnos que ya existen
                for (int i = 0; i < estudiantes.size(); i++) { // Recorremos la lista de estudiantes
                    Alumno aInscrito = estudiantes.get(i); // Seleccionamos uno`por uno a los alumnos
                    if (!borrarAlumno.getDNI().equals(aInscrito.getDNI())) { // Si existe el dni en alguno de los alumnos, no se inserta
                        estudianteTarget.add(aInscrito); // Añadimos el alumno si no existe
                    }
                }
                archivo.delete(); // Borro el archivo para crear uno nuevo con los datos nuevos
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ha ocurrido una excepcion: "+e);
            }
        }else{ // Si no existe el archivo, añado el alumno nuevo a la lista y creo el archivo
            System.out.println("No se encuentra el archivo");
        }        
        
        FileOutputStream archivoAlumnos = new FileOutputStream(archivo, false); // al ponerlo en true se corrompe el archivo
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAlumnos);  
            escribeFichero.writeObject(estudianteTarget); // Insetamos la lista de estudiantes
        }catch(IOException ex){
            System.out.println("error al borrar del archivo");
            System.out.println(ex);
        } 
        archivoAlumnos.close(); 
    }
    
    public ArrayList <Alumno> cargarArchivoAlumnos() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList <Alumno> estudiantes =  new ArrayList();
        ObjectInputStream fichero = null; 
        try{ 
            
            //Leo los alumnos:
            File archivo = new File("alumnos.dat");   
            FileInputStream fis = new FileInputStream(archivo);
            fichero = new ObjectInputStream(fis);
            estudiantes = (ArrayList) fichero.readObject();
                    
            return estudiantes; // Me devuelve la lista de alumnos
            
        }catch(IOException e){
            System.out.println("error al cargar el archivo");
            System.out.println(e);
        }
        fichero.close();
        return null; // Me devuelve un null si el archivo no existe
    }
    
    public DefaultListModel mostrarListaAlumnos() throws IOException, FileNotFoundException, ClassNotFoundException{
        DefaultListModel modelo = new DefaultListModel(); // Creamos el modelo
        ArrayList <Alumno> estudiantes =  new ArrayList(); // Creamos el ArrayList
        estudiantes = cargarArchivoAlumnos(); // Cargamos a los alumnos
        
        // Insertamos a los alumnos en el modelo:
        for(int i = 0; i< estudiantes.size(); i++){
            Alumno alumno = estudiantes.get(i);
            String nombre = alumno.getApellido()+", "+alumno.getNombre();
            modelo.addElement(nombre);
        }
        return modelo;
    }
    
    //GESTION DE ASIGNATURAS:
    
    public DefaultComboBoxModel leerAsignatura() throws FileNotFoundException, IOException{
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        ArrayList <Asignatura> asignaturas =  new ArrayList();
        File fichero = new File("asignaturas.dat");//declara el fichero
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
        try {
            FileInputStream fis = new FileInputStream(fichero);
            dataIS = new ObjectInputStream(fis);
            asignaturas = (ArrayList) dataIS.readObject();
            
            for (int j = 0; j < asignaturas.size(); j++) {
                Asignatura muestra = asignaturas.get(j);
                modelo.addElement(muestra.getAsig()+"("+muestra.getCurso()+")");
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error:" + e); 
        }
        dataIS.close(); // cerrar stream de entrada
        return modelo;
    }
    
    public Asignatura obtenerAsignatura(String asignatura) throws FileNotFoundException, IOException{
        Asignatura respuesta = null;
        ArrayList <Asignatura> asignaturas =  new ArrayList();
        File fichero = new File("asignaturas.dat");//declara el fichero
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
        try {
            FileInputStream fis = new FileInputStream(fichero);
            dataIS = new ObjectInputStream(fis);
            asignaturas = (ArrayList) dataIS.readObject();
            
            //Leo las asignaturas:
            for (int j = 0; j < asignaturas.size(); j++) {
                Asignatura muestra = asignaturas.get(j);
               if(muestra.getAsig().equals(asignatura)){
                   respuesta = muestra; // Guardo la que quiero mostrar
               }
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error:" + e); 
        }
        dataIS.close(); // cerrar stream de entrada
        return respuesta;
    }
    
    
    public void insertarAsignatura(Asignatura asignatura) throws FileNotFoundException, IOException{
        boolean seRepite = false; // Controla si la asignatura ya existe o no
        
        ArrayList <Asignatura> asignaturas =  new ArrayList();
        File archivo = new File("asignaturas.dat");
        
        if(archivo.exists()){ // Comprobamos si el archivo existe
            try {
                asignaturas = cargarArchivoAsignatura(); // Cargamos el archivo de aasignaturas que ya existen

                for (int i = 0; i < asignaturas.size(); i++) { // Recorremos la lista de asignaturas
                    Asignatura asig = asignaturas.get(i); // Seleccionamos una por una la asignatura
                    if (asignatura.getAsig().equals(asig.getAsig())) { 
                        seRepite = true; // Si se repite, no se añade
                        System.out.println("La asignatura ya existe");
                    }
                }
                if (!seRepite) {
                    asignaturas.add(asignatura); // Añadimos la asignatura si no existe
                }
                archivo.delete(); // Borro el archivo para crear uno nuevo con los datos nuevos
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ha ocurrido una excepcion: "+e);
            }
        }else{ // Si no existe el archivo, añado la asignatura nueva a la lista y creo el archivo
            asignaturas.add(asignatura);
        }        
        FileOutputStream archivoAsignaturas = new FileOutputStream(archivo, false); // al ponerlo en true se corrompe el archivo
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAsignaturas);  
            escribeFichero.writeObject(asignaturas); // Insetamos la lista de asignaturas
        }catch(IOException ex){
            System.out.println("error al guardar al archivo");
            System.out.println(ex);
        }finally{
            archivoAsignaturas.close();
        }    
    }
    
    public void modAsignatura(Asignatura AsignaturaObjetivo, String nombreAsig, String nombreCurso) throws FileNotFoundException, IOException{
        boolean seRepite = false; // Controla si la asignatura ya existe o no
        
        ArrayList <Asignatura> asignaturas =  new ArrayList();
        File archivo = new File("asignaturas.dat");
        
        if(archivo.exists()){ // Comprobamos si el archivo existe
            try {
                asignaturas = cargarArchivoAsignatura(); // Cargamos el archivo de asignaturas que ya existen
//                Alumno nuevoAlumno = a; 
                for (int i = 0; i < asignaturas.size(); i++) { // Recorremos la lista de asignaturas
                    Asignatura asig = asignaturas.get(i); // Seleccionamos una por una las asignaturas
                    if (AsignaturaObjetivo.getAsig().equals(asig.getAsig()) && AsignaturaObjetivo.getCurso().equals(asig.getCurso())) { // Si el nombre de la asignatura  y el curso es el mismo es el mismo
                        
                        seRepite = true; // Si se repite, no se añade
                        asig.setAsig(nombreAsig);
                        asig.setCurso(nombreCurso);
                        System.out.println("Modificacion con exito");
                        
                    }
                }
                if (!seRepite) {
                    asignaturas.add(AsignaturaObjetivo); // Añadimos la asignatura si no existe
                }
                archivo.delete(); // Borro el archivo para crear uno nuevo con los datos nuevos
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ha ocurrido una excepcion: "+e);
            }
        }else{ // Si no existe el archivo, añado la asignatura neuva a la lista y creo el archivo
            asignaturas.add(AsignaturaObjetivo);
        }        
        FileOutputStream archivoAsignaturas = new FileOutputStream(archivo, false); // al ponerlo en true se corrompe el archivo
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAsignaturas);  
            escribeFichero.writeObject(asignaturas); // Insetamos la lista de asignaturas
        }catch(IOException ex){
            System.out.println("error al guardar al archivo");
            System.out.println(ex);
        }finally{
            archivoAsignaturas.close();
        }    
    }
    
    public void eliminarAsignatura(Asignatura borrarAsig) throws IOException{     
        ArrayList <Asignatura> asignaturas =  new ArrayList();
        ArrayList <Asignatura> asignaturaTarget =  new ArrayList(); // Lista con las asignaturas que NO he borrado
        
        File archivo = new File("asignaturas.dat");
        
        if(archivo.exists()){ // Comprobamos si el archivo existe
            try {
                asignaturas = cargarArchivoAsignatura(); // Cargamos el archivo de asignaturas que ya existen
                for (int i = 0; i < asignaturas.size(); i++) { // Recorremos la lista de asignaturas
                    Asignatura asig = asignaturas.get(i); // Seleccionamos uno por uno a las asignaturas
                    if (!borrarAsig.getAsig().equals(asig.getAsig())) { // Si existe la asignatura, no se inserta
                        asignaturaTarget.add(asig); // Añadimos la asignatura si no existe
                    }
                }
                archivo.delete(); // Borro el archivo para crear uno nuevo con los datos nuevos
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ha ocurrido una excepcion: "+e);
            }
        }else{ // Si no existe el archivo, añado el alumno nuevo a la lista y creo el archivo
            System.out.println("No se encuentra el archivo");
        }        
        
        FileOutputStream archivoAsignaturas = new FileOutputStream(archivo, false); // al ponerlo en true se corrompe el archivo
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAsignaturas);  
            escribeFichero.writeObject(asignaturaTarget); // Insetamos la lista de estudiantes
        }catch(IOException ex){
            System.out.println("error al borrar del archivo");
            System.out.println(ex);
        } 
        archivoAsignaturas.close(); 
    }
    
    public ArrayList <Asignatura> cargarArchivoAsignatura() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList <Asignatura> asignaturas =  new ArrayList();
        ObjectInputStream fichero = null; 
        try{ 
                 
            File archivo = new File("asignaturas.dat");   
            FileInputStream fis = new FileInputStream(archivo);
            fichero = new ObjectInputStream(fis);
            asignaturas = (ArrayList) fichero.readObject();
                    
            return asignaturas; // Me devuelve la lista de alumnos
            
        }catch(IOException e){
            System.out.println("error al cargar el archivo");
            System.out.println(e);
        }
        fichero.close();
        return null; // Me devuelve un null si el archivo no existe
    }
    
    public DefaultListModel mostrarListaAsignatura() throws IOException, FileNotFoundException, ClassNotFoundException{
        DefaultListModel modelo = new DefaultListModel(); // Creamos el modelo
        ArrayList <Asignatura> asignaturas =  new ArrayList(); // Creamos el ArrayList
        asignaturas = cargarArchivoAsignatura(); // Cargamos a los alumnos
        
        // Insertamos a los alumnos en el modelo:
        for(int i = 0; i< asignaturas.size(); i++){
            Asignatura asig = asignaturas.get(i);
            String nombre = asig.getAsig()+" ("+asig.getCurso()+") ";
            modelo.addElement(nombre);
        }
        return modelo;
    }
    
    public boolean insertarNota(Alumno alumn, Asignatura asig, Nota nota) throws FileNotFoundException, IOException{
       boolean valid = false;
       ArrayList <Alumno> alumnos =  new ArrayList();
        try {
            alumnos = cargarArchivoAlumnos(); // Cargo las notas
            for (Alumno a : alumnos){
                if(a instanceof Alumno){
                    if (a.getDNI().equals(alumn.getDNI())) {
                        String nombreAsig = asig.getAsig()+" ("+nota.getConvocatoria()+")";
                        a.setNotas(nombreAsig, nota.getNota());
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String fileName = "alumnos.dat";
        File archivoAlumnos_exist = new File(fileName);
        if(archivoAlumnos_exist.exists()){
            archivoAlumnos_exist.delete();
        }
        
        FileOutputStream archivoAlumnos = new FileOutputStream(fileName, false); 
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAlumnos);  
            escribeFichero.writeObject(alumnos); // Insetamos la lista de estudiantes
            valid = true;
        }catch(IOException ex){
            System.out.println("error al guardar al archivo");
            System.out.println(ex);
        }finally{
            archivoAlumnos.close();
            return  valid;
        }   
    }
    
    public DefaultListModel mostrarListaNotas(Alumno alumno ) throws IOException, FileNotFoundException, ClassNotFoundException{
        DefaultListModel modelo = new DefaultListModel(); // Creamos el modelo
        ArrayList <Alumno> alumnos =  new ArrayList(); // Creamos el ArrayList
        alumnos = cargarArchivoAlumnos(); // Cargamos a los alumnos
        Hashtable <String, Float> lista_notas_alumno_db = new Hashtable();
        for (Alumno a : alumnos){
            if(a instanceof Alumno){
                    if (a.getDNI().equals(alumno.getDNI())) {
                        lista_notas_alumno_db = a.getNotas();
                    }
            }
        }
        // Insertamos a los alumnos en el modelo:
        try{
            Enumeration notas;
            notas = lista_notas_alumno_db.keys();
            while (notas.hasMoreElements()) {                    
                String clave = (String) notas.nextElement();
                String respuesta = clave+" ==> "+alumno.getNotas().get(clave); // Inserto la respuesta en el modelo
                modelo.addElement(respuesta);
            }
        }catch(Exception e){
            modelo.addElement("");
        }

        return modelo;
    }
    
    
    public void eliminarNota(Alumno alumno, String claveObjetivo) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        ArrayList <Alumno> alumnos =  new ArrayList();
        try {
            alumnos = cargarArchivoAlumnos();
            for (Alumno a : alumnos){
                if(a instanceof Alumno){
                    if (a.getDNI().equals(alumno.getDNI())) { // Si encuentro el alumno, recorro sus notas
                        
                        Hashtable <String, Float> lista_notas_alumno_db = new Hashtable();
                        lista_notas_alumno_db = a.getNotas();
                        lista_notas_alumno_db.remove(claveObjetivo);
                        System.err.println("Se ha borrado?--->"+lista_notas_alumno_db);
                        
                        // Recorrer el array para insertar las notas nuevas:
                        Enumeration notas;
                        notas = lista_notas_alumno_db.keys();
                        
                        while (notas.hasMoreElements()) {    
                            String clave = (String) notas.nextElement();
                            a.setNotas(clave, lista_notas_alumno_db.get(clave));
                            System.err.println("Nuevas notas del alumno-> "+a.getNotas());

                        }
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String fileName = "alumnos.dat";
        File archivoAlumnos_exist = new File(fileName);
        if(archivoAlumnos_exist.exists()){
            archivoAlumnos_exist.delete();
        }
        
        FileOutputStream archivoAlumnos = new FileOutputStream(fileName, false); 
        // al ponerlo en true se corrompe el archivo
     
        try {
            ObjectOutputStream escribeFichero = new ObjectOutputStream(archivoAlumnos);  
            escribeFichero.writeObject(alumnos); // Insetamos la lista de estudiantes
        }catch(IOException ex){
            System.out.println("error al guardar al archivo");
            System.out.println(ex);
        }finally{
            archivoAlumnos.close();
        }   
    }
}
