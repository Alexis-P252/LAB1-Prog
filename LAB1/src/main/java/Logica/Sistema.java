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
import Logica.Plataforma;
import Logica.DtUsuario;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Sistema implements ISistema {
    
    private Map Usuarios;
    private Map Plataformas;
    
    public Sistema(){
        this.Usuarios = new HashMap();
        this.Plataformas = new HashMap();
    }
    
    public void crearEspectaculo(String Plataforma,String nombre,DtFecha fecha_registro,float costo, String url,int cant_max_espec,int cant_min_espec,int duracion,String descripcion){
        //Espectaculo e = New Espectaculo();
        Iterator itPlataformas = this.Plataformas.values().iterator();
        Plataforma p;
        while(itPlataformas.hasNext()){
            p = (Plataforma) itPlataformas.next();
            if(p.GetNombre().equals(Plataforma)){
                Espectaculo e = new Espectaculo(nombre,fecha_registro,costo,url,cant_max_espec,cant_min_espec,duracion,descripcion);
                p.agregarEspectaculo(e);
                break;
            }
        }  
    }
    public boolean verificarEspectacunoEnPlataforma(String plataforma,String espectaculo){
        Plataforma p = (Plataforma)this.Plataformas.get(plataforma);
        
        
        if(p.existeEspectaculo(espectaculo))
                return true;
            else
                return false;
    
    
    }
    public String[] listarArtistas(){
        String res[] = new String[this.Usuarios.size()];
        
        Iterator it = this.Usuarios.values().iterator();

        int i = 0;
        while (it.hasNext()){
            Usuario u = (Usuario) it.next();
            if(u instanceof Artista){
                res[i] = u.GetNombre();
                i++;
            }
            else{
               
            }
        }
        return res;
    }
    
    public DtArtista[] listarDtArtistas(){
        DtArtista res[] = new DtArtista[this.Usuarios.size()];
        
        Iterator it = this.Usuarios.values().iterator();

        int i = 0;
        while (it.hasNext()){
            Usuario u = (Usuario) it.next();
            if(u instanceof Artista){
                res[i] = (DtArtista)u.ArmarDT();
                i++;
            }
            else{
               
            }
        }
        return res;
    }
    
    public String[] listarPlataformas(){
        Iterator it = this.Plataformas.values().iterator();
        
        String res[] = new String[this.Plataformas.size()];
        
        int i = 0;
        while (it.hasNext()){
            Plataforma p = (Plataforma) it.next();
            res[i] = p.GetNombre();
            i++;
        }
        return res;
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
    
    public void PreCargaPlataforma(){
        Plataforma twitch = new Plataforma("Twitch","Twitch is the worlds leading live streaming platform for gamers and the things we love","www.twitch.com");
        Plataforma facebook_live = new Plataforma("Facebook Live", "Facebook Live es la herramienta de vídeo en streaming que ofrece la red social por el momento para usuarios de dispositivos móviles y que permite realizar transmisiones en vivo de manera muy sencilla y rápida ya sea desde tu perfil personal o desde tu página de empresa" , "www.facebooklive.com");
        Plataforma youtube = new Plataforma("YouTube", "YouTube es un portal del Internet que permite a sus usuarios subir y visualizar videos. Fue creado en febrero de 2005 por Chad Hurley, Steve Chen y Jawed Karim", "www.youtube.com");
        this.Plataformas.put(twitch.GetNombre(), twitch);
        this.Plataformas.put(youtube.GetNombre(), youtube);
        this.Plataformas.put(facebook_live.GetNombre(), facebook_live);
    }
    
    public String[] listarEspectaculos(String n){
                      
        Plataforma p =  (Plataforma) this.Plataformas.get(n);
        return p.listarEspectaculoxPlataforma();
        
    }

}

