/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtEspectaculo;
import Logica.Espectaculo;
import Logica.Paquete;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author felip
 */
@Entity
public class Plataforma {
    @Id private String nombre;
    private String descripcion;
    private String url;
    @OneToMany
    private Map<String,Espectaculo> Espectaculos;

    public Plataforma(String nombre, String descripcion, String url){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.Espectaculos = new HashMap();
    };
    
    public Plataforma(){}

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
     
     public String[] listarEspectaculoxPlataforma(){
         String res[] = new String[this.Espectaculos.size()];
        
         Iterator it = this.Espectaculos.values().iterator();

        int i = 0;
        while (it.hasNext()){
            Espectaculo e = (Espectaculo) it.next();
            res[i] = e.getNombre();
            i++;  
        }
        return res;
     }
     
     public DtEspectaculo retDtEspectaculo (String espectaculo){
         Espectaculo e =  (Espectaculo) this.Espectaculos.get(espectaculo);
         DtEspectaculo Dte = e.crearDtEspectaculo();
         return Dte;
    }
     
     public String[] listaEspectaculosxPaq(Paquete paq){
         
         String[] res = new String[this.Espectaculos.size()];
         Iterator it = this.Espectaculos.values().iterator();
         int i = 0;
         
         while(it.hasNext()){
            Espectaculo e = (Espectaculo) it.next();
            String s = e.getNombre() + " (" + this.nombre + ")";
            if(paq.espectaculoPertenece(s) == false){
                res[i] = s;
                i++;
            }         
         }
         
         return res;
     }
}
 