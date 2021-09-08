/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtEspectaculo;
import Logica.Funcion;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author usuario
 */
@Entity
public class Espectaculo {
    @Id private String nombre;
    private Date fecha_registro;
    private float costo;
    private String url;
    private int cant_max_espec;
    private int cant_min_espec;
    private int duracion; 
    private String plataforma;
    private String descripcion;
    private int estado;
    @OneToMany
    private Map<String,Funcion> Funciones;
    @OneToMany 
    private Map<String,Categoria> categorias;
 
    public Espectaculo (){
    }

    public Espectaculo(String nombre, Date fecha_registro, float costo, String url, int cant_max_espec, int cant_min_espec, int duracion, String descripcion, String Plataforma, List categorias, int estado) {
        this.nombre = nombre;
        this.fecha_registro = fecha_registro;
        this.costo = costo;
        this.url = url;
        this.cant_max_espec = cant_max_espec;
        this.cant_min_espec = cant_min_espec;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.plataforma = Plataforma;
        this.estado = estado;
        this.Funciones = new HashMap();
        
        this.categorias = new HashMap();
        
        for(Object cat: categorias){
            Categoria c = (Categoria) cat;
            this.categorias.put(c.getNombre(), c);
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCant_max_espec(int cant_max_espec) {
        this.cant_max_espec = cant_max_espec;
    }

    public void setCant_min_espec(int cant_min_espec) {
        this.cant_min_espec = cant_min_espec;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public float getCosto() {
        return costo;
    }

    public String getUrl() {
        return url;
    }

    public String getPlataforma(){
        return plataforma;
    }
    
    public int getCant_max_espec() {
        return cant_max_espec;
    }

    public int getCant_min_espec() {
        return cant_min_espec;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public DtEspectaculo crearDtEspectaculo (){
        DtEspectaculo dt = new DtEspectaculo(this.nombre,this.fecha_registro,this.costo,this.url, this.cant_max_espec, this.cant_min_espec, this.duracion, this.descripcion);
        return dt;   
    }
    
    public void agregarFuncion(Funcion f){
        this.Funciones.put(f.getNombre(), f);
    }
}
