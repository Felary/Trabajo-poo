package Modelado;

public class Persona {

    private String nombre;
    private String id;
    private String telefono;
    private double sueldo;

    public Persona(String nombre, String id, String telefono,double sueldo) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
        this.sueldo= sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
