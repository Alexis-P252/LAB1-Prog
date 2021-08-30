/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Date;


public class DtRegistro {
    private int id;
    private String funcion;
    private String espectaculo;
    private Date fecha;
    private boolean canjeado;
    private float costo;

    DtRegistro(){}
    DtRegistro (int id,String funcion,String espectaculo,Date fecha,boolean canjeado,float costo){
        this.id=id;
        this.funcion= funcion;
        this.espectaculo = espectaculo;
        this.fecha = fecha;
        this.canjeado = false;
        this.costo = costo;
    }
    public float getCosto(){
        return this.costo;
    }
    
    public int getId(){
        return this.id;
    }
    public String getFuncion (){
        return this.funcion;
    }
    
    public boolean getCanjeado(){
        return this.canjeado;
    }
    
    public String getEspectaculo (){
        return this.espectaculo;
    }
    public Date getFecha (){
        return this.fecha;
    }
}
