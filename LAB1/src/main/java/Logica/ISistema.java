/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtFecha;
/**
 *
 * @author User
 */
public interface ISistema {
    public abstract void ingresarEspectador(String nombre, String apellido, String correo, String nickname, DtFecha fecha_nac);
    public abstract void ingresarArtista(String nombre, String apellido, String correo, String nickname, DtFecha fecha_nac, String descripcion, String biografia, String link);
    public abstract boolean UsuarioxNickname(String nickname);
    public abstract boolean UsuarioxEmail(String email);
    public abstract String[] ColNickname();
    public abstract DtUsuario GetDtUsuario(String nickname);
    public abstract void modificarEspectador(String nickname, String nombre, String apellido, DtFecha f);
    public abstract void ModificarArtista (String nickname, String nombre,String apellido, DtFecha f,String descripcion, String biografia, String link);
    public abstract String[] listarArtistas();
    public abstract DtArtista[] listarDtArtistas();
    public abstract String[] listarPlataformas();
    public abstract void crearEspectaculo(String Plataforma,String nombre,DtFecha fecha_registro,float costo, String url,int cant_max_espec,int cant_min_espec,int duracion,String descripcion, String artista);
    public abstract boolean verificarEspectacunoEnPlataforma(String plataforma,String espectaculo);
    public abstract void PreCarga();
    public abstract String[] listarEspectaculos(String n);
    public abstract DtEspectaculo mostrarEspectaculo (String plataforma, String espectaculo);
    public abstract String[] listarespectaculosXArtista(String artista);
}
