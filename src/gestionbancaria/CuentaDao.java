
package gestionbancaria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
 import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class CuentaDao {
    
    public void crearAhorros(Cuenta c2){
        String sql = "select Cliente_ID from clientes where cedula=?";
        String sql1= "insert into cuentas (Cliente_ID,tipo_cuenta,saldo,Sucursal)values(?,?,?,?)";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "Maranatha2023")) {
            
            try(PreparedStatement stmt = c.prepareStatement(sql)) {
                c.setAutoCommit(false);
                stmt.setInt(1, c2.getCedulausuario());
                stmt.executeQuery();
                ResultSet r = stmt.executeQuery();
                if(r.next()){
                    
                int clienteID =r.getInt("Cliente_ID");
                PreparedStatement stmt1 = c.prepareStatement(sql1);
                stmt1.setInt(1, clienteID);
                stmt1.setString(2, c2.getTipo_cuenta());
                stmt1.setDouble(3, c2.getSaldo());
                stmt1.setString(4, c2.getSucursal());
                stmt1.executeUpdate();
                c.commit();
                    JOptionPane.showMessageDialog(null,"Cuenta registrada");
                }else{
                    JOptionPane.showMessageDialog(null,"No se encontro ningun usuario con esa cedula");
                }
            } catch (Exception e) {
                c.rollback();
                System.out.println(e.getMessage());
            }
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void crearCorriente(Cuenta c3){
        String sql = "select cliente_ID from clientes where cedula=?";
        String sql1="insert into cuentas (cliente_ID,tipo_cuenta,Saldo,Sucursal)values(?,?,?,?)";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "Maranatha2023")) {
            try(PreparedStatement stmt = c.prepareStatement(sql)) {
                c.setAutoCommit(false);
                stmt.setInt(1, c3.getCedulausuario());
                stmt.executeQuery();
                ResultSet r = stmt.executeQuery();
                if(r.next()){
                int cedulaUsuario = r.getInt("cliente_ID");
                    PreparedStatement stmt1 = c.prepareStatement(sql1);
                    stmt1.setInt(1, cedulaUsuario);
                    stmt1.setString(2, c3.getTipo_cuenta());
                    stmt1.setDouble(3, c3.getSaldo());
                    stmt1.setString(4, c3.getSucursal());
                    stmt1.executeUpdate();
                    c.commit();
                    JOptionPane.showMessageDialog(null,"Cuenta registrada");
                }else{
                    JOptionPane.showMessageDialog(null,"No se encontro ningun usuario con esa cedula");
                }
            } catch (Exception e) {
                c.rollback();
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Cuenta> mostrarCuentas(int consultar){
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        String sql="select cliente_ID from clientes where cedula=?";
        String sql1 = "select * from cuentas where cliente_ID=?";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "Maranatha2023")) {
            try(PreparedStatement stmt = c.prepareStatement(sql)) {
                stmt.setInt(1, consultar);
                stmt.executeQuery();
                ResultSet r = stmt.executeQuery();
                r.next();
                int Clienteid = r.getInt("cliente_ID");
                PreparedStatement stmt1 = c.prepareStatement(sql1);
                stmt1.setInt(1, Clienteid);
                stmt1.executeQuery();
                ResultSet r2 = stmt1.executeQuery();
                while(r2.next()){
                    
//                    System.out.print(r2.getInt("cliente_ID"));
//                    System.out.print(" | "+r2.getString("tipo_Cuenta"));
//                    System.out.print(" | "+r2.getDouble("saldo"));
//                    System.out.print(" | "+r2.getString("sucursal"));
//                    System.out.println("");
                    int ID = r2.getInt("cliente_ID");
                    String tipoCuenta= r2.getString("tipo_Cuenta");
                    double saldo = r2.getDouble("saldo");
                    String sucursal = r2.getString("Sucursal");
                    Cuenta cuenta = new Cuenta(tipoCuenta,saldo,sucursal,ID);
                    cuentas.add(cuenta);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cuentas;
    }
}
