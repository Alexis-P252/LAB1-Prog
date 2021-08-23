/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.Usuario;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author User
 */
@Entity
public class Espectador extends Usuario {
    
    public Espectador(String nombre, String apellido, String email, String nickname, Date fecha_nac){
    
        super(nombre, apellido, email, nickname, fecha_nac);
    }
    
    public Espectador(){}
    
    @Override
    public DtEspectador ArmarDT(){
        DtEspectador dt = new DtEspectador(nombre, apellido, email, nickname, fecha_nac);
        return dt;
    }
}
