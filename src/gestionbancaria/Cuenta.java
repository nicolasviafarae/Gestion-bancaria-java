
package gestionbancaria;

public class Cuenta {
    private String tipo_cuenta;
    private double saldo;
    private String sucursal;
    private int cedulausuario;

    public Cuenta(String tipo_cuenta, double saldo, String sucursal, int cedulausuario) {
        this.tipo_cuenta = tipo_cuenta;
        this.saldo = saldo;
        this.sucursal = sucursal;
        this.cedulausuario = cedulausuario;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getSucursal() {
        return sucursal;
    }

    public int getCedulausuario() {
        return cedulausuario;
    }
    
    
}
