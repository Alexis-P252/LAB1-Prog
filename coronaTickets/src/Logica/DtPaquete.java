/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Date;

/**
 *
 * @author Nicolás Guillén
 */
public class DtPaquete {
    private String nombre;
    private String descripcion;
    private float descuento;
    private Date fecha_alta;
    private Date fecha_fin;
    private Date fecha_ini;

    public DtPaquete(String nombre, String descripcion, float descuento, Date fecha_alta, Date fecha_fin, Date fecha_ini) {
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

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public String getNombre() {
        return nombre;
    }
    
}
