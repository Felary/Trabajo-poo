package Modelado;

public abstract class Edificacion {

    protected int numeroCasas;
    protected int numeroApartamentos;
    protected double precioAdministracionApartamentos;
    protected double precioAdministracionCasas;
    protected Persona nombrePropietario;
    protected boolean estadoPago;
    
    protected abstract boolean pagoAdministracion();

    public Edificacion(int numeroCasas, int numeroApartamentos, double precioAdministracionApartamentos, double precioAdministracionCasas, Persona nombrePropietario, boolean estadoPago) {
        this.numeroCasas = numeroCasas;
        this.numeroApartamentos = numeroApartamentos;
        this.precioAdministracionApartamentos = precioAdministracionApartamentos;
        this.precioAdministracionCasas = precioAdministracionCasas;
        this.nombrePropietario = nombrePropietario;
        this.estadoPago = estadoPago;
    }

    
    

   
}
