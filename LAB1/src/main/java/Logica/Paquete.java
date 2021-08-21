/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nicolás Guillén
 */
public class Paquete {
    private String nombre;
    private String descripcion;
    private float descuento;
    private DtFecha fecha_alta;
    private DtFecha fecha_fin;
    private DtFecha fecha_ini;
    private Map espectaculos;

    public Paquete(String nombre, String descripcion, float descuento, DtFecha fecha_alta, DtFecha fecha_fin, DtFecha fecha_ini) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fecha_alta = fecha_alta;
        this.fecha_fin = fecha_fin;
        this.fecha_ini = fecha_ini;
        this.espectaculos = new HashMap(); 
    }
    
    public DtPaquete ArmarDT(){
         DtPaquete dt = new DtPaquete(nombre, descripcion, descuento, fecha_alta, fecha_fin, fecha_ini);
         return dt;
    }

    public Paquete() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getDescuento() {
        return descuento;
    }

    public DtFecha getFecha_alta() {
        return fecha_alta;
    }

    public DtFecha getFecha_fin() {
        return fecha_fin;
    }

    public DtFecha getFecha_ini() {
        return fecha_ini;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public void setFecha_alta(DtFecha fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public void setFecha_fin(DtFecha fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setFecha_ini(DtFecha fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean espectaculoPertenece(String s){
        if(this.espectaculos.get(s) == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    
}
