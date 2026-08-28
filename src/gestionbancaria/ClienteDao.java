
package gestionbancaria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ClienteDao {
    
    
    public void crearCliente(Cliente c1){
        String sql="insert into clientes(cedula,nombre,apellido,correo)values(?,?,?,?)";
        String sql1 = "Select cedula from clientes where cedula=?";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "Maranatha2023");) {
            
            
            try(PreparedStatement stmt = c.prepareStatement(sql);PreparedStatement stmt1 = c.prepareStatement(sql1)) {
                c.setAutoCommit(false);
                stmt1.setInt(1, c1.getCedula());
                ResultSet r = stmt1.executeQuery();
         
                if(r.next()){
                    System.out.println("El cliente con esa cedula ya esta registrado");
                }else{
                stmt.setInt(1, c1.getCedula());
                stmt.setString(2, c1.getNombre());
                stmt.setString(3, c1.getApellido());
                stmt.setString(4, c1.getCorreoElectronico());
                stmt.executeUpdate();
                c.commit();
                System.out.println("Cliente registrado");    
                
                }
            } catch (Exception e) {
                c.rollback();
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            
            
            
            
             
        
        
        
        
    }
    
    public void mostrarClientes(){
        String sql = "Select * from clientes";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "Maranatha2023")) {
            
            try(PreparedStatement stmt = c.prepareStatement(sql);ResultSet r = stmt.executeQuery()){
                while(r.next()){
                    System.out.print(r.getString("nombre"));
                    System.out.print(" | "+r.getString("apellido"));
                    System.out.print(" | "+r.getString("correo"));
                    System.out.print(" | "+r.getString("cedula"));
                    System.out.println("");
                }
                
                
                
            } catch (Exception e) {
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
