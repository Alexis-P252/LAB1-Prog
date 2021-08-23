/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class Persona {
    @Id private int clave;
    private String descripcion;

    public Persona(){}
    
    public Persona(int clave, String descripcion) {
        this.clave = clave;
        this.descripcion = descripcion;
    }
    
    
}
