
package gestionbancaria;

public class Cliente {
    
    private int cedula;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    public Cliente(int cedula, String nombre, String apellido, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    @Override
    public String toString() {
        return "Nombre: "+this.nombre+" | Apellido: "+this.apellido+" | Cedula: "+this.cedula+" | Correo: "+this.correoElectronico;
    }
    
    
    
}
