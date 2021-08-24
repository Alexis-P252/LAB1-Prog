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
        
        em.getTransaction().begin();
        Plataforma p = em.find(Plataforma.class, Plataforma);
        Artista a = em.find(Artista.class, artista);
        Espectaculo e = new Espectaculo(nombre,fecha_registro,costo,url,cant_max_espec,cant_min_espec,duracion,descripcion, Plataforma);
        em.persist(e);
        p.agregarEspectaculo(e);
        a.asociarEspectaculo(e);
        em.getTransaction().commit();
        
        /*
        Plataforma p = (Plataforma) this.Plataformas.get(Plataforma);
        
        Espectaculo e = new Espectaculo(nombre,fecha_registro,costo,url,cant_max_espec,cant_min_espec,duracion,descripcion, Plataforma);
        
        p.agregarEspectaculo(e);
      
        Artista a = (Artista) this.Usuarios.get(artista);
        a.asociarEspectaculo(e);*/
        
    }
    
    public boolean verificarEspectacunoEnPlataforma(String plataforma,String espectaculo){
        
        Plataforma p = em.find(Plataforma.class, plataforma);
        //Plataforma p = (Plataforma)this.Plataformas.get(plataforma);
       
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
        Query q = em.createQuery("SELECT p FROM Plataforma p");
        List plataformas = q.getResultList();
        
        String[] res = new String[plataformas.size()];
        int i = 0;
        
        for(Object object: plataformas){
            Plataforma p = (Plataforma) object;
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
       
    }
    
    
    // RECIBE EL NOMBRE DE LA PLATAFORMA DE LA CUAL SE QUIEREN LISTAR LOS ESPECTACULOS.
    public String[] listarEspectaculos(String n){
        //Query q = em.createQuery("SELECT pe.espectaculos_nombre FROM plataforma_espectaculo pe WHERE pe.plataforma_nombre = :plataforma");
        //q.setParameter("plataforma", n);
        
  
        Query q = em.createQuery("SELECT pe FROM plataforma_espectaculo pe");
        
 
        /*List espectaculos = q.getResultList();
        String [] ret = new String[espectaculos.size()];
        int i = 0;
        
        for(Object object: espectaculos){
            Espectaculo esp = (Espectaculo) object;
            ret[i] = esp.getNombre();
            i++;
        }*/
        String[] ret = new String[10];
        return ret;
    }
    
    public DtEspectaculo mostrarEspectaculo (String plataforma, String espectaculo){
        Query q = em.createQuery("SELECT e FROM Espectaculo e JOIN plataforma_espectaculo ep ON e.nombre = ep.espectaculos_nombre WHERE ep.plataforma_nombre = :plataforma AND ep.espectaculos_nombre = :espectaculo");
        q.setParameter("plataforma", plataforma);
        q.setParameter("espectaculo", espectaculo);
        
        
        Espectaculo e =  (Espectaculo) q.getSingleResult();
        DtEspectaculo DtE = e.crearDtEspectaculo();
        return DtE;
    }
    
    public String[] listarespectaculosXArtista(String artista){
        Query q = em.createQuery("SELECT e FROM Espectaculo e JOIN artista_espectaculo ae ON e.nombre = ae.organiza_nombre AND ae.artista_nickname = :artista");
        q.setParameter("artista", artista);
        List espectaculos = q.getResultList();
        String[] res = new String[espectaculos.size()];
        int i = 0;
        
        for (Object object: espectaculos){
            Espectaculo e = (Espectaculo) object;
            res[i] = e.getNombre();
            i++;
        }
        return res;
        
        /*Artista a = (Artista) this.Usuarios.get(artista);
        return a.listarEspectaculosOrganizo();*/
    }
    
    public boolean ExistePaquete(String paquete){
        if(em.find(Paquete.class, paquete) == null){
            return false;
        }
        else{
            return true;
        }
        /*if(this.Paquetes.get(paquete) == null)
            return false;
        else
            return true;*/

    }
    
    public void AgregarPaquete(String nombre, String descripcion, float descuento, Date fecha_alta, Date fecha_fin, Date fecha_ini){
        Paquete p = new Paquete(nombre, descripcion, descuento, fecha_alta, fecha_fin, fecha_ini);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        //this.Paquetes.put(nombre,p);
       
    }
    
     public String[] listarPaquetes(){
        Query q = em.createQuery("SELECT p FROM Paquete p");
        List paquetes = q.getResultList();
        String[] res = new String[paquetes.size()];
        int i = 0;
        
        for(Object object: paquetes){
            Paquete p = (Paquete) object;
            res[i] = p.getNombre();
            i++;
        }
        return res;
        
        
        /*Iterator it = this.Paquetes.values().iterator();
        
        String res[] = new String[this.Paquetes.size()];
        
        int i = 0;
        while (it.hasNext()){
            Paquete p = (Paquete) it.next();
            res[i] = p.getNombre();
            i++;
        }
        return res;*/
    }
     
    public DtPaquete mostrarPaquete(String paquete){
        Query q = em.createQuery("SELECT p FROM Paquete p WHERE p.nombre = :nombre ");
        q.setParameter("nombre", paquete);
        
        Paquete p = (Paquete) q.getSingleResult();
        
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

