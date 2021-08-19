/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.Usuario;
import Logica.DtFecha;
import java.util.Date;

/**
 *
 * @author User
 */
public class Espectador extends Usuario {
    
    public Espectador(String nombre, String apellido, String email, String nickname, DtFecha fecha_nac){
    
        super(nombre, apellido, email, nickname, fecha_nac);
    }
    
    @Override
    public DtEspectador ArmarDT(){
        DtEspectador dt = new DtEspectador(nombre, apellido, email, nickname, fecha_nac);
        return dt;
    }
}
