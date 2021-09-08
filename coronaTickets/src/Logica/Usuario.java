/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.DtUsuario;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;
    @Id protected String nickname;
    protected Date fecha_nac;
    @OneToMany
    protected Map<String,Usuario> seguidores;
    
    
    
    public Usuario(String nombre, String apellido, String email, String nickname, Date fecha_nac, String password){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nickname = nickname;
        this.fecha_nac = fecha_nac;
        this.password = password;
        this.seguidores = new HashMap();
    
    }
    
    public Usuario(){}
    
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
    
    public Date GetFecha_Nac(){
        return this.fecha_nac;
    }
    
    public void SetNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void SetApellido(String apellido){
        this.apellido = apellido;
    }
    
    public void SetFecha(Date f){
        this.fecha_nac = f;
    }
    
    public abstract DtUsuario ArmarDT();
    
    public void addSeguidor(Usuario u){
        this.seguidores.put(u.GetNickname(), u);
    }
}
