
package Modelado;


public class Administracion {
    protected int numeroCasas;
    protected int numeroApartamentos;
    protected double precioAdministracionApartamentos;
    protected double precioAdministracionCasas;

    public Administracion(int numeroCasas, int numeroApartamentos, double precioAdministracionApartamentos, double precioAdministracionCasas) {
        this.numeroCasas = numeroCasas;
        this.numeroApartamentos = numeroApartamentos;
        this.precioAdministracionApartamentos = precioAdministracionApartamentos;
        this.precioAdministracionCasas = precioAdministracionCasas;
    }

    public int getNumeroCasas() {
        return numeroCasas;
    }

    public void setNumeroCasas(int numeroCasas) {
        this.numeroCasas = numeroCasas;
    }

    public int getNumeroApartamentos() {
        return numeroApartamentos;
    }

    public void setNumeroApartamentos(int numeroApartamentos) {
        this.numeroApartamentos = numeroApartamentos;
    }

    public double getPrecioAdministracionApartamentos() {
        return precioAdministracionApartamentos;
    }

    public void setPrecioAdministracionApartamentos(double precioAdministracionApartamentos) {
        this.precioAdministracionApartamentos = precioAdministracionApartamentos;
    }

    public double getPrecioAdministracionCasas() {
        return precioAdministracionCasas;
    }

    public void setPrecioAdministracionCasas(double precioAdministracionCasas) {
        this.precioAdministracionCasas = precioAdministracionCasas;
    }
    
}
