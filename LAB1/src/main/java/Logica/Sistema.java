/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import Logica.Usuario;
import Logica.DtUsuario;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Sistema implements ISistema {
    
    private Map Usuarios;
    
    public Sistema(){
        this.Usuarios = new HashMap();
    }
    
    public void ingresarEspectador(String nombre, String apellido, String correo, String nickname, DtFecha fecha_nac){
        
        Usuario u = new Espectador(nombre, apellido, correo, nickname, fecha_nac);
        this.Usuarios.put(nickname, u);
    }
    
    public void ingresarArtista(String nombre, String apellido, String correo, String nickname, DtFecha fecha_nac, String descripcion, String biografia, String link){
        
        Usuario u = new Artista(nombre, apellido, correo, nickname, fecha_nac, biografia, descripcion, link);
        this.Usuarios.put(nickname, u);
    }
    
    public boolean UsuarioxNickname(String nickname){
        if(this.Usuarios.get(nickname) == null)
            return false;
        else
            return true;
        
    }
    
    public boolean UsuarioxEmail(String email){
        
        String s;
        Iterator it = this.Usuarios.values().iterator();

        while (it.hasNext()){
            Usuario u = (Usuario) it.next();
            s = u.GetEmail();
            
            if(s.equals(email))
                return true;
        }
        return false;
           
    }
    
    public String[] ColNickname(){
        String res[] = new String[this.Usuarios.size()];
        
        Iterator it = this.Usuarios.values().iterator();

        int i = 0;
        while (it.hasNext()){
            Usuario u = (Usuario) it.next();
            if(u instanceof Artista){
                res[i] = u.GetNickname() + " (A)";
            }
            else{
                res[i] = u.GetNickname() + " (E)";
            }
            i++;
        }
        
        return res;
    }
    
    public DtUsuario GetDtUsuario(String nickname){
        Usuario u = (Usuario) this.Usuarios.get(nickname);
        return u.ArmarDT();
        
    }
    
    public void modificarEspectador(String nickname, String nombre, String apellido, DtFecha f){
        Usuario u = (Espectador) this.Usuarios.get(nickname);
        u.SetNombre(nombre);
        u.SetApellido(apellido);
        u.SetFecha(f);
    }
    
    public void ModificarArtista (String nickname, String nombre,String apellido, DtFecha f,String descripcion, String biografia, String link){
        Artista u = (Artista) this.Usuarios.get(nickname);
        u.SetNombre(nombre);
        u.SetApellido (apellido);
        u.SetFecha (f);
        u.SetDescripcion (descripcion);
        u.SetBiografia (biografia);
        u.SetLink (link);
    }
    
    

}


