/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Nicolás Guillén
 */
public class DtPaquete {
    private String nombre;
    private String descripcion;
    private float descuento;
    private DtFecha fecha_alta;
    private DtFecha fecha_fin;
    private DtFecha fecha_ini;

    public DtPaquete(String nombre, String descripcion, float descuento, DtFecha fecha_alta, DtFecha fecha_fin, DtFecha fecha_ini) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fecha_alta = fecha_alta;
        this.fecha_fin = fecha_fin;
        this.fecha_ini = fecha_ini;
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
    
}
