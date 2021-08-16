/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtFecha;
import Logica.DtUsuario;

/**
 *
 * @author User
 */
public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String nickname;
    protected DtFecha fecha_nac;
    
    public Usuario(String nombre, String apellido, String email, String nickname, DtFecha fecha_nac){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nickname = nickname;
        this.fecha_nac = fecha_nac;
        
    }
    
    public String GetNombre(){
        return this.nombre;
    }
    
    public String GetApellido(){
        return this.apellido;
    }
    
    public String GetEmail(){
        return this.email;
    }
    
    public String GetNickname(){
        return this.nickname;
    }
    
    public DtFecha GetFecha_Nac(){
        return this.fecha_nac;
    }
    
    public abstract DtUsuario ArmarDT();
    
    
}
