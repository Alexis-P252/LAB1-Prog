package Logica;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class DtCategoria {
    private String nombre;


    public DtCategoria (){
    }

    public DtCategoria (String nombre){
        this.nombre=nombre;
    }
    
    public String getNombre (){
        return this.nombre;
    }
}