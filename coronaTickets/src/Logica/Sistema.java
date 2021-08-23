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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Sistema implements ISistema {
    
    private Map Usuarios;
    private Map Plataformas;
    private Map Paquetes;
    private EntityManager em;
    
    public Sistema(){
        
        this.Usuarios = new HashMap();
        this.Plataformas = new HashMap();
        this.Paquetes = new HashMap();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("coronaTicketsPU");
        this.em = emf.createEntityManager();
       
    }
    
    /*public void pruebapersistencia(){
        em.getTransaction().begin();
        Persona p = new Persona(4,"numero 4");
        em.persist(p);
        em.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "SE CREO A LA PERSONA");
    }*/
    
    public void crearEspectaculo(String Plataforma,String nombre,Date fecha_registro,float costo, String url,int cant_max_espec,int cant_min_espec,int duracion,String descripcion, String artista){
        
        
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
        
        Query q = em.createQuery("SELECT a.nickname FROM Artista a");
        List lista = q.getResultList();
        
        String res[] = new String[lista.size()];
        int i = 0;
        for(Object object: lista){
            res[i] = (String) object;
            i++;
        
        }
        return res;
    }
    
    public DtArtista[] listarDtArtistas(){
        
        Query q = em.createQuery("SELECT a FROM Artista a");
        List lista = q.getResultList();
        
        DtArtista res[] = new DtArtista[lista.size()];
        int i = 0;
        
        for(Object object: lista){
            Artista art = (Artista) object;
            DtArtista dtArt = art.ArmarDT();
            res[i] = dtArt;
            i++;
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
    
    public void ingresarEspectador(String nombre, String apellido, String correo, String nickname, Date fecha_nac){
        
        Usuario u = new Espectador(nombre, apellido, correo, nickname, fecha_nac);
        this.Usuarios.put(nickname, u);
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        
    }
    
    public void ingresarArtista(String nombre, String apellido, String correo, String nickname, Date fecha_nac, String descripcion, String biografia, String link){
        
        Usuario u = new Artista(nombre, apellido, correo, nickname, fecha_nac, biografia, descripcion, link);
        this.Usuarios.put(nickname, u);
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
    
    public boolean UsuarioxNickname(String nickname){
        
        if(em.find(Artista.class, nickname) == null && em.find(Espectador.class, nickname) == null)
            return false;
        else
            return true;
        
    }
    
    public boolean UsuarioxEmail(String email){
        
        try{
            Query q = em.createQuery("SELECT a FROM Artista a WHERE a.email = :email");
            q.setParameter("email", email);
            Artista art = (Artista) q.getSingleResult();
            return true;
        }catch(Exception e){
            
        }
        try{
            Query q2 = em.createQuery("SELECT e FROM Espectador e WHERE e.email = :email");
            q2.setParameter("email", email);
            Espectador esp = (Espectador) q2.getSingleResult();
            return true;
        }
        catch(Exception e){
            
        } 
        return false;
   
    }
    
    public String[] ColNickname(){
        Query q = em.createQuery("Select a.nickname from Artista a");
        List listaArtistas = q.getResultList();

        Query q1 = em.createQuery("Select e.nickname from Espectador e");
        List listaEspectadores = q1.getResultList();

        String res[] = new String[listaEspectadores.size() + listaArtistas.size()];

        int i = 0;

        for(Object object :listaArtistas){
            res[i] =(String) object + " (A)";
            i++;
        }

        for(Object object :listaEspectadores){
            res[i] =(String)object + " (E)";
            i++;
        }

        return res;
    }
    
    public DtUsuario GetDtUsuario(String nickname){

        if(em.find(Artista.class, nickname) == null){
            
            Espectador esp = em.find(Espectador.class, nickname);
            DtEspectador dtEsp = esp.ArmarDT();
            return dtEsp;
        }
        else{
            Artista art = em.find(Artista.class,nickname);
            DtArtista dtArt = art.ArmarDT();
            return dtArt;
       
        }
        

 
    }
    
    public void modificarEspectador(String nickname, String nombre, String apellido, Date f){
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE Espectador e SET e.nombre = :nombre, e.apellido = :apellido, e.fecha_nac = :fecha WHERE e.nickname = :nickname");
        q.setParameter("fecha", f);
        q.setParameter("nombre", nombre);
        q.setParameter("apellido", apellido);
        q.setParameter("nickname", nickname);
        q.executeUpdate();
       
        Espectador esp = em.find(Espectador.class, nickname);
        em.refresh(esp);
        em.getTransaction().commit();
        
        
    }
    
    public void ModificarArtista (String nickname, String nombre,String apellido, Date f,String descripcion, String biografia, String link){
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE Artista a SET a.nombre = :nombre, a.apellido = :apellido, a.fecha_nac = :fecha, a.biografia = :biografia, a.descripcion = :descripcion, a.link = :link WHERE a.nickname = :nickname");
        q.setParameter("fecha", f);
        q.setParameter("nombre", nombre);
        q.setParameter("apellido", apellido);
        q.setParameter("biografia", biografia);
        q.setParameter("descripcion", descripcion);
        q.setParameter("link", link);
        q.setParameter("nickname", nickname);
        q.executeUpdate();
       
        Artista art = em.find(Artista.class, nickname);
        em.refresh(art);
        em.getTransaction().commit();
        
        
        /*Artista u = (Artista) this.Usuarios.get(nickname);
        u.SetNombre(nombre);
        u.SetApellido (apellido);
        u.SetFecha (f);
        u.SetDescripcion (descripcion);
        u.SetBiografia (biografia);
        u.SetLink (link);*/
    }
    
    public void PreCarga(){
         Query q = em.createQuery("SELECT a FROM Plataforma a");
         List plataformas = q.getResultList();
         
         /*for (Object object : plataformas) {
                Plataforma p = (Plataforma) object;
                
                this.Plataformas.put(p.GetNombre(), p);
                
            }*/
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
    
    public void AgregarPaquete(String nombre, String descripcion, float descuento, Date fecha_alta, Date fecha_fin, Date fecha_ini){
        Paquete p = new Paquete(nombre, descripcion, descuento, fecha_alta, fecha_fin, fecha_ini);
        this.Paquetes.put(nombre,p);
       
    }
    
     public String[] listarPaquetes(){
        Iterator it = this.Paquetes.values().iterator();
        
        String res[] = new String[this.Paquetes.size()];
        
        int i = 0;
        while (it.hasNext()){
            Paquete p = (Paquete) it.next();
            res[i] = p.getNombre();
            i++;
        }
        return res;
    }
     
    public DtPaquete mostrarPaquete(String paquete){
        Paquete p = (Paquete) this.Paquetes.get(paquete);
        DtPaquete dtP = p.ArmarDT();
       
        return dtP;
    }
    
    // LISTA LOS ESPECTACULOS QUE PERTENECEN A LA PLATAFORMA PERO QUE NO FORMAN PARTE DEL PAQUETE
    public String[] listarEspectaculosPaq (String plataforma, String paquete){
        
        Paquete paq = (Paquete) this.Paquetes.get(paquete);
        Plataforma pla = (Plataforma) this.Plataformas.get(plataforma);
        
        String[] res = pla.listaEspectaculosxPaq(paq);
        return res;
    }

}

