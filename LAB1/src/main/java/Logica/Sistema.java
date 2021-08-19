/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;
import Logica.DtEspectaculo;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import Logica.Plataforma;
import Logica.Usuario;
import Logica.DtEspectaculo;
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
    private Map Paquetes;
    
    public Sistema(){
        this.Usuarios = new HashMap();
        this.Plataformas = new HashMap();
        this.Paquetes = new HashMap();
    }
    
    public void crearEspectaculo(String Plataforma,String nombre,DtFecha fecha_registro,float costo, String url,int cant_max_espec,int cant_min_espec,int duracion,String descripcion, String artista){
        
        
        Plataforma p = (Plataforma) this.Plataformas.get(Plataforma);
        
        Espectaculo e = new Espectaculo(nombre,fecha_registro,costo,url,cant_max_espec,cant_min_espec,duracion,descripcion, Plataforma);
        
        p.agregarEspectaculo(e);
        
        Artista a = (Artista) this.Usuarios.get(artista);
        a.asociarEspectaculo(e);
        
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
                res[i] = u.GetNickname();
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
    
    public void PreCarga(){
        Plataforma twitch = new Plataforma("Twitch","Twitch is the worlds leading live streaming platform for gamers and the things we love","www.twitch.com");
        Plataforma facebook_live = new Plataforma("Facebook Live", "Facebook Live es la herramienta de vídeo en streaming que ofrece la red social por el momento para usuarios de dispositivos móviles y que permite realizar transmisiones en vivo de manera muy sencilla y rápida ya sea desde tu perfil personal o desde tu página de empresa" , "www.facebooklive.com");
        Plataforma youtube = new Plataforma("YouTube", "YouTube es un portal del Internet que permite a sus usuarios subir y visualizar videos. Fue creado en febrero de 2005 por Chad Hurley, Steve Chen y Jawed Karim", "www.youtube.com");
        this.Plataformas.put(twitch.GetNombre(), twitch);
        this.Plataformas.put(youtube.GetNombre(), youtube);
        this.Plataformas.put(facebook_live.GetNombre(), facebook_live);
        
        DtFecha fecha_nac1 = new DtFecha(22,8,1999,0,0,0);
        DtFecha fecha_nac2 = new DtFecha(17,4,1996,0,0,0);
        DtFecha fecha_nac3 = new DtFecha(1,8,2000,0,0,0);
        DtFecha fecha_nac4 = new DtFecha(14,6,2000,0,0,0);
        DtFecha fecha_nac5 = new DtFecha(4,11,1999,0,0,0);
        
        Usuario Nahuel = new Espectador("Nahuel", "Perdomo", "nperdomo@gmail.com", "nperdomo", fecha_nac1 );
        Usuario Andres = new Espectador("Andres", "Castro", "acastro@gmail.com", "acastro",fecha_nac2);
        Usuario Alexis = new Espectador("Alexis", "Peralta", "aperalta@gmail.com", "aperalta", fecha_nac3);
        Usuario Nicolas = new Espectador("Nicolas", "Guillen", "nguillen@gmail.com", "nguillen", fecha_nac4);
        Usuario Felipe = new Espectador("Felipe", "Parada", "fparada@gmail.com", "fparada",fecha_nac5);
        this.Usuarios.put(Nahuel.GetNickname(), Nahuel);
        this.Usuarios.put(Andres.GetNickname(), Andres);
        this.Usuarios.put(Alexis.GetNickname(), Alexis);
        this.Usuarios.put(Nicolas.GetNickname(), Nicolas);
        this.Usuarios.put(Felipe.GetNickname(), Felipe);
        
        DtFecha f = new DtFecha(10,04,1980,0,0,0);
        Usuario user  = new Artista ("Isabel", "Mebarak", "shakira@gmail", "Shakira", f,"Mueve las caderas","Nacio en colombia","Shakira.com");
        this.Usuarios.put("Shakira", user);
        f = new DtFecha(15,6,1982,0,0,0);
        user  = new Artista("Ricky", "Marty", "Ricky@gmail", "RickyM",f,"Hay que pedirle mas mas a la vida...","Canto un disco con Maluma","RickylaVidaloca.com");
        this.Usuarios.put("RickyM", user);
        f = new DtFecha(16,12,1990,0,0,0);
        user  = new Artista ("Maluma", "Baby", "Maluma@gmail", "Maluma",f,"El es Maluma Babi","Es adopatado","malumababy.com");
        this.Usuarios.put("Maluma", user);
        f = new DtFecha(17,03,1980,0,0,0);
        user  = new Artista("Lucas", "Sugo", "Lukita@gmail", "Lucas_Sugo",f,"El mejor cantante del interior","Se separo de su grupo inicial","lucasparati.com");
        this.Usuarios.put("Lucas_Sugo", user);
        f = new DtFecha(17,03,1995,0,0,0);
        user  = new Artista ("Soledad", "Pastoruso", "Pastoruso@gmail", "Sole",f,"Canta folklore","Es argentina","Arriba.com");
        this.Usuarios.put("Sole", user);

        DtFecha f1 = new DtFecha(10,04,1980,0,0,0);
        Usuario user1  = new Artista ("Isabel", "Mebarak", "shakira@gmail", "Shakira", f1,"Mueve las caderas","Nacio en colombia","Shakira.com");
        this.Usuarios.put("Shakira", user1);
        DtFecha f2 = new DtFecha(15,6,1982,0,0,0);
        Usuario user2  = new Artista("Ricky", "Marty", "Ricky@gmail", "RickyM",f2,"Hay que pedirle mas mas a la vida...","Canto un disco con Maluma","RickylaVidaloca.com");
        this.Usuarios.put("RickyM", user2);
        DtFecha f3 = new DtFecha(16,12,1990,0,0,0);
        Usuario user3  = new Artista ("Maluma", "Baby", "Maluma@gmail", "Maluma",f3,"El es Maluma Babi","Es adopatado","malumababy.com");
        this.Usuarios.put("Maluma", user3);
        DtFecha f4 = new DtFecha(1,12,1980,0,0,0);
        Usuario user4 = new Artista("Lucas", "Sugo", "Lukita@gmail", "Lucas_Sugo",f4,"El mejor cantante del interior","Se separo de su grupo inicial","lucasparati.com");
        this.Usuarios.put("Lucas_Sugo", user4);
        DtFecha f5 = new DtFecha(17,03,1995,0,0,0);
        Usuario user5  = new Artista ("Soledad", "Pastoruso", "Pastoruso@gmail", "Sole",f5,"Canta folklore","Es argentina","Arriba.com");
        this.Usuarios.put("Sole", user5);
         
    }
    
    public String[] listarEspectaculos(String n){
        String [] ret = null;
        Plataforma p =  (Plataforma) this.Plataformas.get(n);
        if(p!=null){
        ret= p.listarEspectaculoxPlataforma();
        }
        return ret;
    }
    
    public DtEspectaculo mostrarEspectaculo (String plataforma, String espectaculo){
        Plataforma p =  (Plataforma) this.Plataformas.get(plataforma);
        DtEspectaculo DtE = p.retDtEspectaculo(espectaculo);
        return DtE;
    }
    
    public String[] listarespectaculosXArtista(String artista){
        Artista a = (Artista) this.Usuarios.get(artista);
        return a.listarEspectaculosOrganizo();
    }
    
    public boolean ExistePaquete(String paquete){
        if(this.Paquetes.get(paquete) == null)
            return false;
        else
            return true;

    }
    
    public void AgregarPaquete(String nombre, String descripcion, float descuento, DtFecha fecha_alta, DtFecha fecha_fin, DtFecha fecha_ini){
        Paquete p = new Paquete(nombre, descripcion, descuento, fecha_alta, fecha_fin, fecha_ini);
        this.Paquetes.put(nombre,p);
    }

}

