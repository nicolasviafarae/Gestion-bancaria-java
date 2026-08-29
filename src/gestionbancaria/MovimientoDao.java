
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
        String sql2="insert into movimientos(cuenta_ID,tipo_movimiento,monto)values(?,?,?)";
        String deposito = "Deposito";
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
                PreparedStatement stmt2 = c.prepareStatement(sql2);
                stmt2.setInt(1, cuentaID);
                stmt2.setString(2, deposito);
                stmt2.setDouble(3, monto);
                stmt2.executeUpdate();
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
    
    public void retirar(int cuentaID2,double retiro){
        String sql = "Select saldo from cuentas where cuenta_ID=?";
        String sql1 = "update cuentas set saldo=? where cuenta_ID=?";
        String sql2= "insert into movimientos (cuenta_ID,tipo_movimiento,monto) values(?,?,?)";
        String Retiro = "Retiro";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemabancario", "root", "Maranatha2023")) {
            
            try(PreparedStatement stmt = c.prepareStatement(sql)) {
                c.setAutoCommit(false);
                stmt.setInt(1, cuentaID2);
                ResultSet r = stmt.executeQuery();
                r.next();
                double monto = r.getDouble("saldo")-retiro;
                PreparedStatement stmt1 = c.prepareStatement(sql1);
                stmt1.setDouble(1, monto);
                stmt1.setInt(2, cuentaID2);
                stmt1.executeUpdate();
                PreparedStatement stmt2 = c.prepareStatement(sql2);
                stmt2.setInt(1, cuentaID2);
                stmt2.setString(2, Retiro);
                stmt2.setDouble(3, retiro);
                stmt2.executeUpdate();
                c.commit();
                System.out.println("Retiro realizado");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                c.rollback();
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void transferir(int cuentaID3,int cuentaID4 ,double transferir){
        String sql = "select saldo from cuentas where cuenta_ID=?";
        String sql1= "select saldo from cuentas where cuenta_ID=?";
        String sql2= "update cuentas set saldo=? where cuenta_ID=?";
        String sql3= "update cuentas set saldo=? where cuenta_ID=?";
        String SQL="insert into transferencias (tipo_transacción)values(?)";
        String sql4= "select transferencia_ID from transferencias ";
        String Retiro = "Retiro";
        String Deposito= "Deposito";
        String sql5= "insert into movimientos (cuenta_ID,transferencia_ID,tipo_movimiento,Monto)values(?,?,?,?)";
        String sql6= "insert into movimientos (cuenta_ID,transferencia_ID,tipo_movimiento,Monto)values(?,?,?,?)";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemabancario", "root", "Maranatha2023")) {
            try(PreparedStatement stmt = c.prepareStatement(sql)) {
                c.setAutoCommit(false);
                stmt.setInt(1, cuentaID3);
                ResultSet r = stmt.executeQuery();
                //stmt.executeQuery();
                r.next();
                double saldo1 = r.getDouble("saldo")-transferir;
                PreparedStatement stmt1 = c.prepareStatement(sql1);
                stmt1.setInt(1, cuentaID4);
                ResultSet r2 = stmt1.executeQuery();
                //stmt1.executeQuery();
                r2.next();
                double saldo2 = r2.getDouble("saldo");
                PreparedStatement stmt2 = c.prepareStatement(sql2);
                stmt2.setDouble(1, saldo1);
                stmt2.setInt(2, cuentaID3);
                stmt2.executeUpdate();
                PreparedStatement stmt3 = c.prepareStatement(sql3);
                stmt3.setDouble(1, saldo2);
                stmt3.setInt(2, cuentaID4);
                stmt3.executeUpdate();
                PreparedStatement stmts = c.prepareStatement(SQL);
                stmts.setString(1, Deposito);
                stmts.executeUpdate();
                PreparedStatement stmt4 = c.prepareStatement(sql4);
                ResultSet r3 = stmt4.executeQuery();
                r3.next();
                int transID = r3.getInt("transferencia_ID");
                PreparedStatement stmt5 = c.prepareStatement(sql5);
                stmt5.setInt(1, cuentaID3);
                stmt5.setInt(2, transID);
                stmt5.setString(3, Retiro);
                stmt5.setDouble(4, transferir);
                stmt5.executeUpdate();
                PreparedStatement stmt6 = c.prepareStatement(sql6);
                stmt6.setInt(1, cuentaID4);
                stmt6.setInt(2, transID);
                stmt6.setString(3, Deposito);
                stmt6.setDouble(4, transferir);
                stmt6.executeUpdate();
                c.commit();
                System.out.println("Transferencia realizada");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                c.rollback();
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void mostrarHistorial(int cuentaID5){
        String sql= "select * from movimientos where cuenta_ID=?";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemabancario", "root", "Maranatha2023")) {
            
            try(PreparedStatement stmt = c.prepareStatement(sql)){
                stmt.setInt(1, cuentaID5);
                ResultSet r = stmt.executeQuery();
                while(r.next()){
                    System.out.print(r.getInt("cuenta_ID"));
                    System.out.print(" | "+r.getInt("transferencia_ID"));
                    System.out.print(" | "+r.getDate("Fecha"));
                    System.out.print(" | "+r.getDouble("Monto"));
                    System.out.println("");
                }
                
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
