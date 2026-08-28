
package gestionbancaria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
 import java.sql.ResultSet;
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
                System.out.println("Cuenta registrada");
                }else{
                    System.out.println("No se encontro ningun usuario con esa cedula");
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
                    System.out.println("Cuenta registrada");
                }else{
                    System.out.println("No se encontro ningun usuario con esa cedula");
                }
            } catch (Exception e) {
                c.rollback();
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
