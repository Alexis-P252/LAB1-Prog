/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author User
 */

public class DtFecha {
    
    private int dia;
    private int mes;
    private int anio;
    private int hora;
    private int minuto;
    private int segundo;
    
    public DtFecha(int dia, int mes, int anio, int hora, int minuto, int segundo){
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }
    
    public int GetDia(){
        return this.dia;
    }
    
    public int GetMes(){
        return this.mes;
    }
    
    public int GetAnio(){
        return this.anio;
    }
    
    public int GetHora(){
        return this.hora;
    }
    
    public int GetMinuto(){
        return this.minuto;
    }
    
    public int GetSegundo(){
        return this.segundo;
    }
    
    public String GetFecha(){
        String res = this.dia + "/" + this.mes + "/" + this.anio;
        return res;
    }
}
