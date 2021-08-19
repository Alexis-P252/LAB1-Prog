/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtFecha;
import Logica.Espectaculo;


/**
 *
 * @author usuario
 */
public class DtFuncion {
    private String nombre;
    private DtFecha fecha_registro;
    private DtFecha fecha_hora;

    public DtFuncion() {
    }

    public DtFuncion(String nombre, DtFecha fecha_registro, DtFecha fecha_hora) {
        this.nombre = nombre;
        this.fecha_registro = fecha_registro;
        this.fecha_hora = fecha_hora;
    }

    public String getNombre() {
        return nombre;
    }

    public DtFecha getFecha_registro() {
        return fecha_registro;
    }

    public DtFecha getFecha_hora() {
        return fecha_hora;
    }
       
}
