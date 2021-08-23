/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


public class SistemaFactory {
    
    private static SistemaFactory instancia;
    private SistemaFactory(){};


    public static SistemaFactory getInstance(){
        if (instancia == null){
            instancia = new SistemaFactory();
        }
        return instancia;
    }
    
    public ISistema getISistema() {
        ISistema sis = new Sistema();
        return sis;
    }
    
}
