/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.Usuario;
import Logica.DtFecha;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class Artista extends Usuario {
    
    private String descripcion;
    private String biografia;
    private String link;
    private Map organiza;
    
    public Artista(String nombre, String apellido, String email, String nickname, DtFecha fecha_nac, String descripcion, String biografia, String link ){
        
        super(nombre, apellido, email, nickname, fecha_nac);
        this.descripcion = descripcion;
        this.biografia = biografia;
        this.link = link;
        this.organiza = new HashMap();
    }
    
    public String GetDescripcion(){
        return this.descripcion;
    }
    
    public String GetBiografia(){
        return this.biografia;
    }
    
     public String GetLink(){
        return this.link;
    }
    
    public void SetDescripcion (String des){
        this.descripcion = des;
    }

    public void SetLink(String _link){
        this.link = _link;
    }

    public void SetBiografia (String bio){
        this.biografia = bio;
    } 
     
    @Override 
    public DtArtista ArmarDT(){
         DtArtista dt = new DtArtista(nombre, apellido, email, nickname, fecha_nac, descripcion, biografia, link);
         return dt;
    }
    
    public void asociarEspectaculo(Espectaculo e){
        String clave = e.getNombre() + " (" + e.getPlataforma() + ")";
        this.organiza.put(clave,e);
    }
    
    public String[] listarEspectaculosOrganizo(){
        
        String[] res = new String[this.organiza.size()];
        
        Iterator it = this.organiza.values().iterator();
        int i = 0;
        while (it.hasNext()){
            Espectaculo e = (Espectaculo) it.next();
            res[i] = e.getNombre() + " (" +  e.getPlataforma() + ")";
            i++;
        }
        return res;
    }
}
