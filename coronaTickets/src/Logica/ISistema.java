/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.Date;
/**
 *
 * @author User
 */
public interface ISistema {
    public abstract void ingresarEspectador(String nombre, String apellido, String correo, String nickname, Date fecha_nac);
    public abstract void ingresarArtista(String nombre, String apellido, String correo, String nickname, Date fecha_nac, String descripcion, String biografia, String link);
    public abstract boolean UsuarioxNickname(String nickname);
    public abstract boolean UsuarioxEmail(String email);
    public abstract String[] ColNickname();
    public abstract DtUsuario GetDtUsuario(String nickname);
    public abstract void modificarEspectador(String nickname, String nombre, String apellido, Date f);
    public abstract void ModificarArtista (String nickname, String nombre,String apellido, Date f,String descripcion, String biografia, String link);
    public abstract String[] listarArtistas();
    public abstract DtArtista[] listarDtArtistas();
    public abstract String[] listarPlataformas();
    public abstract void crearEspectaculo(String Plataforma,String nombre,Date fecha_registro,float costo, String url,int cant_max_espec,int cant_min_espec,int duracion,String descripcion, String artista);
    public abstract boolean verificarEspectaculo(String espectaculo);
    public abstract String[] listarEspectaculos(String n);
    public abstract DtEspectaculo mostrarEspectaculo (String espectaculo);
    public abstract String[] listarespectaculosXArtista(String artista);
    public abstract boolean ExistePaquete(String paquete);
    public abstract void AgregarPaquete(String nombre, String descripcion, float descuento, Date fecha_alta, Date fecha_fin, Date fecha_ini);
    public abstract String[] listarPaquetes();
    public abstract DtPaquete mostrarPaquete(String paquete);
    public abstract boolean EspectaculoenPaq (String paquete, String espectaculo);
    public abstract void AddEspectaculoaPaquete(String paquete, String espectaculo);
    public abstract String[] listarEspectaculosxPaq(String paquete);
    public abstract String[] listarPaquetesdeEsp(String espectaculo);
}



/* COSAS PARA HACER

    - CONTROLAR FECHAS EN ALTA DE PAQUETE DE ESPECTACULO
    - ENCONTRADO BUG EN MODIFICAR DATOS DE USUARIO. AL CREAR UN ESPECTADOR NUEVO (NO UNO DE LA PRECARGA) Y LUEGO IR A LA PANTALLA DE MODIFICAR SI SE PULSA SOBRE UN ARTISTA 
        Y LUEGO SOBRE EL ESPECTADOR NUEVO, LOS CAMPOS BIOGRAFIA DESCRIPCION Y LINK NO DESAPARECERAN, PUDIENDO EL USUARIO INGRESAR DATOS Y DARLE CONFIRMAR, LOGRANDO QUE SE ROMPA.
*/
  