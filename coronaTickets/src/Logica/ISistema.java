/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.Date;
import java.util.List;
/**
 *
 * @author User
 */
public interface ISistema {
    
    // FUNCIONES DE LISTAR
    
    public abstract String[] listarfuncionesxEspectador(String nickname);
    public abstract String[] listarFuncionesxEspectaculo(String espectaculo);
    public abstract String[] listarEspectadores();
    public abstract String[] listarEspectaculosxPaq(String paquete);
    public abstract String[] listarPaquetesdeEsp(String espectaculo);
    public abstract String[] listarEspectaculos(String n);
    public abstract String[] listarArtistas();
    public abstract String[] listarPlataformas();
    public abstract String[] listarPaquetes();
    public abstract String[] Artistasinvitados(String funcion);
    public abstract String[] listarArtistasmenosOrganizador(String espectaculo);
    public abstract String[] listarespectaculosXArtista(String artista);
    public abstract String[] ColNickname();
    public abstract List ListarRegistros (String espectador);
    public abstract String[] listarCategorias();
    
    // INGRESAR DATOS
    
    public abstract void ingresarEspectador(String nombre, String apellido, String correo, String nickname, Date fecha_nac);
    public abstract void ingresarArtista(String nombre, String apellido, String correo, String nickname, Date fecha_nac, String descripcion, String biografia, String link);
    public abstract void crearEspectaculo(String Plataforma,String nombre,Date fecha_registro,float costo, String url,int cant_max_espec,int cant_min_espec,int duracion,String descripcion, String artista, List categorias);
    public abstract void AgregarPaquete(String nombre, String descripcion, float descuento, Date fecha_alta, Date fecha_fin, Date fecha_ini);
    public abstract void AgregarFuncion(String nombre, Date fecha_hora, Date fecha_registro, String espectaculo, List artistas);
    public abstract void AddEspectaculoaPaquete(String paquete, String espectaculo);
    public abstract void agregarRegistro(String espectador,String funcion, String espectaculo, Date f, int costo);
    public abstract void AgregarCategoria(String categoria);
    
    // CONTROL DE INFORMACION
    
    public abstract boolean UsuarioxNickname(String nickname);
    public abstract boolean UsuarioxEmail(String email);
    public abstract boolean verificarEspectaculo(String espectaculo);
    public abstract boolean ExisteFuncion(String funcion);
    public abstract boolean ExistePaquete(String paquete);
    public abstract boolean espectadorRegistrado(String espectador, String funcion);
    public abstract boolean cantMaxAsistentes(String espectaculo, String funcion);
    public abstract boolean alMenos3Registros (String espectador);
    public abstract boolean EspectaculoenPaq (String paquete, String espectaculo);
    public abstract boolean ExisteCategoria(String nombre);
    
    // MOSTRAR DATOS
    
    public abstract DtUsuario GetDtUsuario(String nickname);
    public abstract DtEspectaculo mostrarEspectaculo (String espectaculo);
    public abstract DtPaquete mostrarPaquete(String paquete);
    public abstract DtFuncion MostrarFuncion (String funcion);
    public abstract float darPrecioEspectaculo(String espectaculo);
    
    // MODIFICAR DATOS
   
    public abstract void modificarEspectador(String nickname, String nombre, String apellido, Date f);
    public abstract void ModificarArtista (String nickname, String nombre,String apellido, Date f,String descripcion, String biografia, String link);
    public abstract void CanjeoRegistros(List RegistrosSeleccionados, String espectador);
    
}

    // EL FUNCIONAMIENTO DE CADA FUNCION ESTA ESPECIFICADO EN EL ARCHIVO Sistema.java




