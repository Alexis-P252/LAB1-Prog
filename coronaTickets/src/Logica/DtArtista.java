/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtUsuario;
import java.util.Date;

/**
 *
 * @author User
 */
public class DtArtista extends DtUsuario {
    
    private String descripcion;
    private String biografia;
    private String link;
    
    public DtArtista(String nombre, String apellido, String email, String nickname, Date fecha_nac, String descripcion, String biografia, String link ){
        
        super(nombre, apellido, email, nickname, fecha_nac);
        this.descripcion = descripcion;
        this.biografia = biografia;
        this.link = link;
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
}
