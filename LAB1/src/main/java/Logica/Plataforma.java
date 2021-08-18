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
 * @author felip
 */
public class Plataforma {
    private String nombre;
    private String descripcion;
    private String url;
    private Map Espectaculos;

    public Plataforma(String nombre, String descripcion, String url){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.Espectaculos = new HashMap();
    };

    public String GetNombre(){
      return this.nombre;
    }

     public String GetDescripcion(){
      return this.descripcion;
    }

     public String GetUrl(){
      return this.url;
    }
     
     public void agregarEspectaculo(Espectaculo e){
         Espectaculos.put(e.getNombre(),e);
     }
     
     public boolean existeEspectaculo(String espectaculo){
         if(this.Espectaculos.get(espectaculo)==null){
             return false;
         }else{
             return true;
         }
     }
}
