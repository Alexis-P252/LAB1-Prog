/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.Espectaculo;
import java.util.Date;


/**
 *
 * @author usuario
 */
public class DtFuncion {
    private String nombre;
    private Date fecha_registro;
    private Date fecha_hora;

    public DtFuncion() {
    }

    public DtFuncion(String nombre, Date fecha_registro, Date fecha_hora) {
        this.nombre = nombre;
        this.fecha_registro = fecha_registro;
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
       
}
