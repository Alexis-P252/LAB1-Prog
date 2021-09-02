/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.Funcion;
import Logica.DtRegistro;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity
public class Registro {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private Date fecha;
    private float costo;
    private boolean canjeado;
    @OneToOne
    Funcion funcion;

    public Registro(){}
    
    public Registro(Date fecha, float costo, Funcion funcion){
        this.fecha = fecha;
        this.costo = costo;
        this.funcion = funcion;
        this.canjeado = false;
        
    }

    public Date getFecha() {
        return fecha;
    }

    public float getCosto() {
        return costo;
    }

    public Funcion getFuncion() {
        return funcion;
    }
    
    public boolean getCanjeado(){
        return canjeado;
    }
    
    public DtRegistro ArmarDt(){
        DtRegistro dt = new DtRegistro(this.id,this.funcion.getNombre(),this.funcion.getEspectaculo(),this.funcion.getFecha_hora(),this.getCanjeado(),this.getCosto());
        return dt;
    }
    
}



