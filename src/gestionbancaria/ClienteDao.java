
package gestionbancaria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class ClienteDao {
    
    
    public void crearCliente(Cliente c1){
        String sql="insert into clientes(cedula,nombre,apellido,correo)values(?,?,?,?)";
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "Maranatha2023");
            
            try(c;PreparedStatement stmt = c.prepareStatement(sql)) {
                c.setAutoCommit(false);
                stmt.setInt(1, c1.getCedula());
                stmt.setString(2, c1.getNombre());
                stmt.setString(3, c1.getApellido());
                stmt.setString(4, c1.getCorreoElectronico());
                stmt.executeUpdate();
                c.commit();
                System.out.println("Cliente registrado");
            } catch (Exception e) {
                c.rollback();
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            
            
            
            
             
        
        
        
        
    }
    
    
}
