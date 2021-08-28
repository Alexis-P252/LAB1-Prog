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
    
    private EntityManager em;
    
    public Sistema(){
      
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("coronaTicketsPU");
        this.em = emf.createEntityManager();
    }
    
    
    
    //  ** CREA UN NUEVO ESPECTACULO CON LOS DATOS RECIBIDOS 
    //  ** LO PERSISTE A NIVEL DE LA BASE DE DATOS
    //  ** ASOCIA EL ESPECTACULO CON LA PLATAFORMA EN DONDE SE CREO
    //  ** ASOCIA EL ESPECTACULO CON EL ARTISTA QUE ORGANIZO EL MISMO
    public void crearEspectaculo(String Plataforma,String nombre,Date fecha_registro,float costo, String url,int cant_max_espec,int cant_min_espec,int duracion,String descripcion, String artista){
        
        em.getTransaction().begin();
        
        Plataforma p = em.find(Plataforma.class, Plataforma);
        Artista a = em.find(Artista.class, artista);
        
        Espectaculo e = new Espectaculo(nombre,fecha_registro,costo,url,cant_max_espec,cant_min_espec,duracion,descripcion, Plataforma);
        em.persist(e);
        p.agregarEspectaculo(e);
        a.asociarEspectaculo(e);
        em.getTransaction().commit();
 
    }
    // VERIFICA SI EL NOMBRE DEL ESPECTACULO RECIBIDIDO COMO PARAMETRO PERTENECE A UN ESPECTACULO YA CREADO, DEVUELTE TRUE EN CASO POSITIVO, FALSE EN CASO NEGATIVO
    public boolean verificarEspectaculo(String espectaculo){
        
        if(em.find(Espectaculo.class,espectaculo) == null){
            return false;
        }
        else{
            return true;
        }
      
    }
    // LISTA TODOS LOS ARTISTAS EXISTENTES, EN CASO DE QUE LA CONSULTA NO DEVUELVA NINGUN ARTISTA, DEVUELVE UN ARREGLO VACIO.
    public String[] listarArtistas(){
        
        Query q = em.createQuery("SELECT a.nickname FROM Artista a");
        try{
            List lista = q.getResultList();
            String res[] = new String[lista.size()];
            int i = 0;
            
            for(Object object: lista){
                res[i] = (String) object;
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // LISTA TODOS LOS ESPECTADORES EXISTENTES, EN CASO DE QUE LA CONSULTA NO DEVUELVA NINGUN ESPECTADOR, DEVUELVE UN ARREGLO VACIO.
    public String[] listarEspectadores(){
        
        Query q = em.createQuery("SELECT e.nickname FROM Espectador e");
        try{
            List lista = q.getResultList();
            String res[] = new String[lista.size()];
            int i = 0;
            
            for(Object object: lista){
                res[i] = (String) object;
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
 
    // LISTA TODAS LAS PLATAFORMAS EXISTENTES, EN CASO DE QUE LA CONSULTA NO DEVUELVA NIGNUNA PLATAFORMA, DEVUELVE UN ARREGLO VACIO
    public String[] listarPlataformas(){
        Query q = em.createQuery("SELECT p FROM Plataforma p");
        try{
            List plataformas = q.getResultList();
            String[] res = new String[plataformas.size()];
            int i = 0;

            for(Object object: plataformas){
                Plataforma p = (Plataforma) object;
                res[i] = p.GetNombre();
                i++;
            }
            return res; 
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // CREA UN NUEVO ESPECTADOR CON LOS DATOS RECIBIDIDOS Y LO PERSISTE.
    public void ingresarEspectador(String nombre, String apellido, String correo, String nickname, Date fecha_nac){
        
        Usuario u = new Espectador(nombre, apellido, correo, nickname, fecha_nac);
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
    
    
    // CREA UN NUEVO ARTISTA CON LOS DATOS RECIBIDIDOS Y LO PERSISTE.
    public void ingresarArtista(String nombre, String apellido, String correo, String nickname, Date fecha_nac, String descripcion, String biografia, String link){
        
        Usuario u = new Artista(nombre, apellido, correo, nickname, fecha_nac, biografia, descripcion, link);
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
    
    
    // VERIFICA SI EXISTE UN USUARIO CON EL NICKNAME RECIBIDO COMO PARAMETRO, EN CASO POSITIVO RETORNA TRUE, EN CASO NEGATIVO RETORNA FALSE 
    public boolean UsuarioxNickname(String nickname){
        
        if(em.find(Artista.class, nickname) == null && em.find(Espectador.class, nickname) == null)
            return false;
        else
            return true;
        
    }
    
    
    // VERIFICA SI EXISTE UN USUARIO CON EL EMAIL RECIBIDO COMO PARAMETRO, EN CASO POSITIVO RETORNA TRUE, EN CASO NEGATIVO RETORNA FALSE 
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
    
    
    // DEVUELVE UN ARREGLO CON TODOS LOS NICKNAMES DE LOS USUARIOS, TANTO ESPECTADORES COMO ARTISTAS.
    public String[] ColNickname(){
        //Query q = em.createQuery("Select a.nickname from Artista a UNION Select e.nickname from Espectador e");
        Query q = em.createQuery("Select CONCAT(a.nickname, ' (A)') FROM Artista a UNION Select CONCAT(e.nickname, ' (E)') FROM Espectador e");
        
     
        try{
            List lista = q.getResultList();
            //List listaEspectadores = q1.getResultList();

            String res[] = new String[lista.size()];
            int i = 0;

            for(Object object :lista){
                res[i] =(String) object;
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // DEVUELVE UN DTUSUARIO CON LA INFORMACION DEL USUARIO CON NICKNAME IGUAL AL RECIBIDO COMO PARAMETRO
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
    
    
    // EJECUTA UNA SENTENCIA SQL PARA MODIFICAR AL ESPECTADOR CON EL NICKNAME DADO.
    public void modificarEspectador(String nickname, String nombre, String apellido, Date f){
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE Espectador e SET e.nombre = :nombre, e.apellido = :apellido, e.fecha_nac = :fecha WHERE e.nickname = :nickname");
        q.setParameter("fecha", f);
        q.setParameter("nombre", nombre);
        q.setParameter("apellido", apellido);
        q.setParameter("nickname", nickname);
        q.executeUpdate();
       
        Espectador esp = em.find(Espectador.class, nickname);
        em.refresh(esp); // NECESARIO PARA QUE LOS CAMBIOS EN EL ESPECTADOR SE VEAN INMEDIATAMENTE, SIN NECESIDAD DE CERRAR Y VOLVER A ABRIR LA APLICACION.
        em.getTransaction().commit();
    }
    
    
    // EJECUTA UNA SENTENCIA SQL PARA MODIFICAR AL ARTISTA CON EL NICKNAME DADO
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
        em.refresh(art); // NECESARIO PARA QUE LOS CAMBIOS EN EL ARTISTA SE VEAN INMEDIATAMENTE, SIN NECESIDAD DE CERRAR Y VOLVER A ABRIR LA APLICACION.
        em.getTransaction().commit();
    }
    
    
    // LISTA TODOS LOS ESPECTACULOS DEL SISTEMA QUE PERTENECEN A LA PLATAFORMA CON NOMBRE IGUAL AL PARAMETRO RECIBIDO 
    // EN CASO DE QUE LA CONSULTA NO DEVUELVA NADA, SE RETORNA UN STRING VACIO
    public String[] listarEspectaculos(String n){
  
        Query q = em.createQuery("SELECT pe.Espectaculos FROM Plataforma pe WHERE pe.nombre = :plataforma");
        q.setParameter("plataforma", n);
        
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
    
    
    // DEVUELVE UN DTESPECTACULO CON LA INFORMACION DEL ESPECTACULO CON NOMBRE IGUAL AL RECIBIDO POR PARAMETRO
    public DtEspectaculo mostrarEspectaculo (String espectaculo){
       
        Query q = em.createQuery("SELECT esp FROM Espectaculo esp WHERE esp.nombre = :espectaculo");
        q.setParameter("espectaculo", espectaculo);
        
        Espectaculo e =  (Espectaculo) q.getSingleResult();
        DtEspectaculo DtE = e.crearDtEspectaculo();
        return DtE;
    }
    
    
    // LISTA TODOS LOS ESPECTACULOS QUE FUERON ORGANIZADOS POR EL ARTISTA DE NICKNAME IGUAL AL PARAMETRO RECIBIDO POR PARAMETRO
    // EN CASO DE QUE LA QUERY NO RETORNE NINGUN RESULTADO, SE DEVUELVE UNA LISTA VACIA.
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
    }
    
    
    // VERIFICA SI EXISTE UN PAQUETE EN EL SISTEMA CON MISMO NOMBRE QUE EL RECIBIDO POR PARAMETRO, EN CASO POSITIVO RETORNA TRUE, EN CASO NEGATIVO FALSE
    public boolean ExistePaquete(String paquete){
        
        if(em.find(Paquete.class, paquete) == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    // CREA UN NUEVO PAQUETE CON LOS DATOS RECIBIDOS Y LO PERSISTE
    public void AgregarPaquete(String nombre, String descripcion, float descuento, Date fecha_alta, Date fecha_fin, Date fecha_ini){
        Paquete p = new Paquete(nombre, descripcion, descuento, fecha_alta, fecha_fin, fecha_ini);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
    
    
    // LISTA TODOS LOS PAQUETES EXISTENTES, EN CASO DE QUE LA QUERY NO RETORNE NI UN PAQUETE, RETORNA UN ARREGLO VACIO
     public String[] listarPaquetes(){
        Query q = em.createQuery("SELECT p FROM Paquete p");
        try{
            List paquetes = q.getResultList();
            String[] res = new String[paquetes.size()];
            int i = 0;

            for(Object object: paquetes){
                Paquete p = (Paquete) object;
                res[i] = p.getNombre();
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
     
    // DEVUELVE UN DTPAQUETE CON LOS DATOS DEL PAQUETE CON NOMBRE IGUAL AL PARAMETRO RECIBIDO 
    public DtPaquete mostrarPaquete(String paquete){
        
        Query q = em.createQuery("SELECT p FROM Paquete p WHERE p.nombre = :nombre ");
        q.setParameter("nombre", paquete);
        
        Paquete p = (Paquete) q.getSingleResult();
        DtPaquete dtP = p.ArmarDT();
        return dtP;
    }
    
    
    // VERIFICA SI EL ESPECTACULO PERTENECE AL PAQUETE, AMBOS DATOS RECIBIDOS POR PARAMETRO
    public boolean EspectaculoenPaq (String paquete, String espectaculo){
        Query q = em.createQuery("SELECT p.espectaculos FROM Paquete p WHERE p.nombre = :paquete");
        q.setParameter("paquete", paquete);
        
        try{
            List espectaculos = q.getResultList();
            for(Object object: espectaculos){
                Espectaculo e = (Espectaculo) object;
                if(e.getNombre() == espectaculo){
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            return false;
        }   
    }
    
    
    // AÑADE EL ESPECTACULO CON NOMBRE IGUAL AL RECIBIDO POR PARAMETRO AL PAQUETE CON NOMBRE IGUAL AL RECIBIDO POR PARAMETRO
    public void AddEspectaculoaPaquete(String paquete, String espectaculo){
        em.getTransaction().begin();
        Espectaculo esp = em.find(Espectaculo.class,espectaculo);
        Paquete p = em.find(Paquete.class, paquete);
        p.addEsp(esp);
        em.getTransaction().commit();
    }
    
    
    // LISTA TODOS LOS ESPECTACULOS PERTENECIENTES AL PAQUETE CON NOMBRE IGUAL AL RECIBIDO POR PARAMETRO
    // EN CASO DE QUE LA QUERY NO RETORNE NINGUN VALOR, SE RETORNA UN ARREGLO VACIO
    public String[] listarEspectaculosxPaq(String paquete){
        Query q = em.createQuery("SELECT p.espectaculos FROM Paquete p WHERE p.nombre = :paquete");
        q.setParameter("paquete", paquete);
        
        try{
            List espectaculos = q.getResultList();
            String[] res = new String[espectaculos.size()];
            int i = 0;
    
            for(Object object: espectaculos){
                Espectaculo e = (Espectaculo) object;
                res[i] = e.getNombre();
                i++;
            }
            
            return res;
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // LISTA TODOS LOS PAQUETES DE LOS CUALES FORMA PARTE EL ESPECTACULO CON NOMBRE IGUAL AL RECIBIDO POR PARAMETRO
    // EN CASO DE QUE LA QUERY NO RETORNE NINGUN VALOR, SE DEVUELVE UN ARREGLO VACIO
    public String[] listarPaquetesdeEsp(String espectaculo){
        
        Query q = em.createNativeQuery("SELECT p.paquete_nombre FROM paquete_espectaculo p WHERE p.espectaculos_nombre = '"+ espectaculo+"'");
     
        try{
            List lista = q.getResultList();
            String[] res = new String[lista.size()];
            int i=0;
            for(Object object: lista){
                String nombre = (String) object;
                res[i] = nombre;
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // CREA UNA NUEVA FUNCION CON LOS DATOS INTRODUCIDOS
    // PARA CADA UNO DE LOS NICKNAMES DE ARTISTAS RECIBIDOS EN LA LISTA, ASOCIA DICHO ARTISTA A LA NUEVA FUNCION COMO ARTISTAS INVITADOS
    // FINALMENTE ASOCIA A LA FUNCION CON EL ESPECTACULO AL CUAL PERTENECE
    public void AgregarFuncion(String nombre, Date fecha_hora, Date fecha_registro, String espectaculo, List artistas){
        
        em.getTransaction().begin();
        Funcion f = new Funcion(nombre, fecha_registro, fecha_hora,espectaculo);
        
        for(Object object: artistas){
            Artista art = em.find(Artista.class, object);
            f.agregarArtistaInvitado(art);
        }
        
        Espectaculo esp = em.find(Espectaculo.class, espectaculo);
        esp.agregarFuncion(f);
        em.persist(f);
        em.getTransaction().commit();
    }
    
    
    // LISTA TODOS LOS ARTISTAS INVITADOS A LA FUNCION CON NOMBRE IGUAL AL VALOR RECIBIDO POR PARAMETRO
    // EN CASO DE QUE LA QUERY NO RETORNE NINGUN VALOR SE DEVUELVE UN ARREGLO VACIO.
    public String[] Artistasinvitados(String funcion){
        
        Query q = em.createQuery("SELECT f.Artistas FROM Funcion f WHERE f.nombre = :funcion");
        q.setParameter("funcion", funcion);
        
        try{
            List artistas = q.getResultList();
            String[] res = new String[artistas.size()];
            int i=0;
            
            for(Object object: artistas){
                Artista art = (Artista) object;
                res[i] = art.GetNickname();
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // VERIFICA SI EXISTE UNA FUNCION CON NOMBRE IGUAL AL VALOR RECIBIDO POR PARAMETRO, EN CASO POSITIVO RETORNA TRUE, EN CASO NEGATIVO FALSE
    public boolean ExisteFuncion(String funcion){
        if(em.find(Funcion.class, funcion) == null){
            return false;
        }
        else{
            return true;
        }
    }


    // DEVUELVE UN DTFUNCION CON LOS DATOS DE LA FUNCION CON NOMBRE IGUAL AL VALOR RECIBIDO POR PARAMETRO
    public DtFuncion MostrarFuncion (String funcion){
        
        Query q = em.createQuery("SELECT f FROM Funcion f WHERE f.nombre = :funcion");
        q.setParameter("funcion", funcion);

        Funcion f= (Funcion) q.getSingleResult();
        DtFuncion dtF = f.crearDtFuncion();
        return dtF;
    }
    
    
    // LISTA TODOS LOS ARTISTAS A EXCEPCION DEL ARTISTA ORGANIZADOR DEL ESPECTACULO CON NOMBRE IGUAL AL VALOR RECIBIDO POR PARAMETRO
    // EN CASO DE QUE LA QUERY NO RETORNE NINGUN VALOR, SE RETORNA UN ARREGLO VACIO.
    public String[] listarArtistasmenosOrganizador(String espectaculo){
        
        Query q = em.createNativeQuery("SELECT DISTINCT a.nickname FROM artista a  WHERE a.nickname NOT IN (SELECT ae.artista_nickname FROM artista_espectaculo ae WHERE ae.organiza_nombre = " +"'" + espectaculo +"')");

        try{
            List lista = q.getResultList();
            String[] res = new String[lista.size()];
            int i=0;
            for(Object object: lista){
                String nombre = (String) object;
                res[i] = nombre;
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // LISTA TODAS LAS FUNCIONES PERTENECIENTES AL ESPECTACULO CON NOMBRE IGUAL AL VALOR RECIBIDO POR PARAMETRO
    // EN CASO DE QUE LA QUERY NO RETORNE NINGUN VALOR, SE DEVUELVE UN ARREGLO VACIO
    public String[] listarFuncionesxEspectaculo(String espectaculo){
        
        Query q = em.createQuery("SELECT e.Funciones FROM Espectaculo e WHERE e.nombre = :nombre");
        q.setParameter("nombre", espectaculo);
        
        try{
            List funciones = q.getResultList();
            String[] res = new String[funciones.size()];
            int i = 0;
            for(Object object: funciones){
                Funcion f = (Funcion) object;
                res[i] = f.getNombre();
                i++;
            }
            return res;
            
        }catch(Exception e){
            return new String[1];
        }
    }
    
    
    // DADO EL NOMBRE DE UN ESPECTACULO DEVUELVE EL PRECIO DEL MISMO
    public float darPrecioEspectaculo(String espectaculo){
        Query q = em.createQuery("SELECT e.costo FROM Espectaculo e WHERE e.nombre = :espectaculo");
        q.setParameter("espectaculo", espectaculo);
        float costo = (float) q.getSingleResult();
        return costo;
    }
    
    
    
    public boolean espectadorRegistrado(String espectador, String funcion){
        
        Query q = em.createNativeQuery("SELECT COUNT(*) FROM espectador_registro er WHERE er.espectador_nickname = '" + espectador +"' AND er.registros_key = '"+funcion+"'");
        
        long cant = (long) q.getSingleResult();
        
        if(cant == 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    public boolean cantMaxAsistentes(String espectaculo, String funcion){
        Query q = em.createQuery("SELECT e.cant_max_espec FROM Espectaculo e WHERE e.nombre = :espectaculo");
        q.setParameter("espectaculo", espectaculo);
        int max = (int) q.getSingleResult();
        
        Query q2 = em.createNativeQuery("SELECT COUNT(*) FROM espectador_registro er WHERE  er.registros_key = '"+funcion+"'");
        long cant = (long) q2.getSingleResult();
        
        if(cant < max){
            return false;
        }
        else{
            return true;
        }
        
    }
    
    public void agregarRegistro(String espectador,String funcion, String espectaculo, Date f, int costo){
        
        em.getTransaction().begin();
        
        Espectador esp = (Espectador) em.find(Espectador.class, espectador);
        Funcion fu = (Funcion) em.find(Funcion.class, funcion);
        Espectaculo e = (Espectaculo) em.find(Espectaculo.class, espectaculo);
        
        if(costo != 0){
             costo = (int) e.getCosto();
        }
        
        Registro r = new Registro(f, e.getCosto(), fu);
        em.persist(r);
        
        esp.agregarRegistro(r, fu.getNombre());
        
        em.getTransaction().commit();
        
    }
    
}

