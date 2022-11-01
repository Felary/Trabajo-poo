package Modelado;

public class Casa extends Edificacion {

    protected String idCasa;

    public Casa(String idCasa, int numeroCasas, int numeroApartamentos, double precioAdministracionApartamentos, double precioAdministracionCasas, Persona nombrePropietario, boolean estadoPago) {
        super(numeroCasas, numeroApartamentos, precioAdministracionApartamentos, precioAdministracionCasas, nombrePropietario, estadoPago);
        this.idCasa = idCasa;
    }   

    
    @Override
    public boolean pagoAdministracion() {
        double salrioPropietario = nombrePropietario.getSueldo();
        double admicasa = precioAdministracionCasas;
        double total = salrioPropietario - admicasa;
        return total >= 0;
    }
    
    public String getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(String idCasa) {
        this.idCasa = idCasa;
    }

    public Persona getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(Persona nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public Boolean getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(Boolean estadoPago) {
        this.estadoPago = estadoPago;
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

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

}
