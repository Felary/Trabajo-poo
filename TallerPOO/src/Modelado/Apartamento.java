package Modelado;

public class Apartamento extends Edificacion {

    protected String idApartamento;
    protected String torre;

   

    public Apartamento(String idApartamento, String torre, int numeroCasas, int numeroApartamentos, double precioAdministracionApartamentos, double precioAdministracionCasas, Persona nombrePropietario, boolean estadoPago) {
        super(numeroCasas, numeroApartamentos, precioAdministracionApartamentos, precioAdministracionCasas, nombrePropietario, estadoPago);
        this.idApartamento = idApartamento;
        this.torre = torre;
    }      
    
    /**
     *
     * @return
     */
    @Override
    public boolean pagoAdministracion() {

        double salrioPropietario = nombrePropietario.getSueldo();
        double admiApartamento = precioAdministracionApartamentos;
        double total = salrioPropietario - admiApartamento;
        return total >= 0;
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

    public Persona getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(Persona nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public boolean getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(String idApartamento) {
        this.idApartamento = idApartamento;
    }

    public String getTorre() {
        return torre;
    }

    public void setTorre(String torre) {
        this.torre = torre;
    }

}
