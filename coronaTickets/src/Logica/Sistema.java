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
    
    public boolean verificarEspectaculo(String espectaculo){
        
        if(em.find(Espectaculo.class,espectaculo) == null){
            return false;
        }
        else{
            return true;
        }
      
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
        Query q1 = em.createQuery("Select e.nickname from Espectador e");
        
        try{
            List listaArtistas = q.getResultList();
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
            
        }catch(Exception e){
            return new String[1];
        }
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
    
    
 
    public String[] listarEspectaculos(String n){
  
        Query q = em.createQuery("SELECT pe.Espectaculos FROM Plataforma pe WHERE pe.nombre = :plataforma");
        q.setParameter("plataforma", n);
        
        String[] ret = new String[1];
        try{
            List espectaculos = q.getResultList();
        
            String[] res = new String[espectaculos.size()];
            int i = 0;

            for(Object object: espectaculos){
                Espectaculo esp = (Espectaculo) object;
                res[i] = esp.getNombre();
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
        
        
  
        
    }
    
    public DtEspectaculo mostrarEspectaculo (String espectaculo){
       
        Query q = em.createQuery("SELECT esp FROM Espectaculo esp WHERE esp.nombre = :espectaculo");
        q.setParameter("espectaculo", espectaculo);
        
        
        Espectaculo e =  (Espectaculo) q.getSingleResult();
        DtEspectaculo DtE = e.crearDtEspectaculo();
        return DtE;
    }
    
    public String[] listarespectaculosXArtista(String artista){

        Query q = em.createQuery("SELECT a.organiza FROM Artista a WHERE a.nickname = :nickname");
        q.setParameter("nickname", artista);

        try{
            List espectaculos = q.getResultList();
            String[] res = new String[espectaculos.size()];
            int i = 0;
           
            for (Object object: espectaculos){
                Espectaculo e = (Espectaculo) object;
                res[i] = e.getNombre();
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
        
        
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
        
        Query q = em.createQuery("SELECT e FROM Espectaculo e WHERE e.plataforma = :plataforma AND e.nombre != (SELECT FROM Paquete p WHERE p.nombre = :paquete)");
        q.setParameter("paquete", paquete);
        q.setParameter("plataforma", plataforma);
        String[] res = new String[1];
        res[0] = "hola";
        return res;
        /*Query q = em.createQuery("SELECT e FROM Espectaculo e WHERE e.plataforma = :plataforma");
        Query q2 = em.createQuery("SELECT p.espectaculos FROM Paquete p WHERE p.nombre = :paquete");
        q.setParameter("plataforma", plataforma);
        q2.setParameter("paquete", paquete);
        
        List esp_plataforma;
        List esp_paquete;
        
        try{
            esp_plataforma = q.getResultList();
        }catch(Exception e){
            return new String[1];
        }
        
        try{
            esp_paquete = q2.getResultList();
        }catch(Exception e){
            String[] res = new String[esp_plataforma.size()];
            int i=0;
            for(Object object: esp_plataforma){
                Espectaculo esp = (Espectaculo) object;
                res[i] = esp.getNombre();
                i++;
            }
            return res;
        }
        
        try{
            JOptionPane.showMessageDialog(null,"LLEGA HASTA ACA","Alta Paquete",JOptionPane.ERROR_MESSAGE);
            
            String[] res = new String[esp_plataforma.size()];
            int i=0;
            
            for(Object object: esp_plataforma){
                Espectaculo e = (Espectaculo) object;
                String nombre = e.getNombre();
                boolean esta = false;
                
                for(Object object2: esp_paquete){
                    Espectaculo e2 = (Espectaculo) object2;
                    if(e2.getNombre() == nombre){
                        esta = true;
                    }
                }
                if(esta == false){
                    res[i] = nombre;
                    i++;
                }
                
            }
            JOptionPane.showMessageDialog(null,"NO FALLA","Alta Paquete",JOptionPane.ERROR_MESSAGE);
            return res;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"SI FALLA","Alta Paquete",JOptionPane.ERROR_MESSAGE);
            return new String[1];
        }
        
       /* Paquete paq = (Paquete) this.Paquetes.get(paquete);
        Plataforma pla = (Plataforma) this.Plataformas.get(plataforma);
        
        String[] res = pla.listaEspectaculosxPaq(paq);
        return res;*/
    }

}

