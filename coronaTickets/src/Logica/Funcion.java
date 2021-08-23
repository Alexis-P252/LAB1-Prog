/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtFuncion;
import Logica.Artista;
import java.util.Date;
//import Logica.Registro;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Funcion {
    private String nombre;
    private Date fecha_registro;
    private Date fecha_hora;
    private Map Registros;
    private Map Artistas;
    

/// Constructores
    public Funcion() {
    }
    public Funcion(String nombre, Date fecha_registro, Date fecha_hora) {
        this.nombre = nombre;
        this.fecha_registro = fecha_registro;
        this.fecha_hora = fecha_hora;
        this.Registros = new HashMap();
        this.Artistas = new HashMap();
    }      

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }
    public DtFuncion crearDtFuncion (){
        DtFuncion dt = new DtFuncion (this.nombre, this.fecha_registro,this.fecha_hora);
        return dt;
    } 
    

}
