/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolás Guillén
 */
@Entity
public class Paquete {
    @Id private String nombre;
    private String descripcion;
    private float descuento;
    private Date fecha_alta;
    private Date fecha_fin;
    private Date fecha_ini;
    @OneToMany 
    private Map<String,Espectaculo> espectaculos;
    @OneToMany
    private Map<String,Categoria> categorias;

    public Paquete(String nombre, String descripcion, float descuento, Date fecha_alta, Date fecha_fin, Date fecha_ini) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fecha_alta = fecha_alta;
        this.fecha_fin = fecha_fin;
        this.fecha_ini = fecha_ini;
        this.espectaculos = new HashMap();
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setFecha_ini(Date fecha_ini) {
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
    
    public void addEsp(Espectaculo esp){
        this.espectaculos.put(esp.getNombre(), esp);
    }
    
    public void addCategoria (Categoria c){
        JOptionPane.showMessageDialog(null,"LLEGA ADENTRO DE LA FUNCION","PRUEBA",JOptionPane.ERROR_MESSAGE);
        this.categorias.put(c.getNombre(),c);
    }
    
}
