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
    
}
