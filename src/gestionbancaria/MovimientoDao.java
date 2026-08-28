
package gestionbancaria;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MovimientoDao {
    
    public void depositar(int cuentaID,double monto){
        String sql = "select saldo from cuentas where cuenta_ID =?";
        String sql1="update cuentas set saldo=? where cuenta_ID=?";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemabancario", "root", "Maranatha2023")) {
            try(PreparedStatement stmt = c.prepareStatement(sql)) {
                c.setAutoCommit(false);
                stmt.setInt(1, cuentaID);
                stmt.executeQuery();
                ResultSet r = stmt.executeQuery();
                r.next();
                double saldo = r.getDouble("saldo")+monto;
                PreparedStatement stmt1 = c.prepareStatement(sql1);
                stmt1.setDouble(1, saldo);
                stmt1.setInt(2, cuentaID);
                stmt1.executeUpdate();
                c.commit();
                System.out.println("Transferencia completada");
            } catch (Exception e) {
                c.rollback();
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
}
