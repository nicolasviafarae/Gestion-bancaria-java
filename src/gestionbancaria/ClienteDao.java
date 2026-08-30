
package gestionbancaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
public class ClienteDao {
    
    //ofuzcar encriptado
    public void crearCliente(Cliente c1){
        String sql="insert into clientes(cedula,nombre,apellido,correo)values(?,?,?,?)";
        String sql1 = "Select cedula from clientes where cedula=?";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "MiContraseña");) {
            
            
            try(PreparedStatement stmt = c.prepareStatement(sql);PreparedStatement stmt1 = c.prepareStatement(sql1)) {
                c.setAutoCommit(false);
                stmt1.setInt(1, c1.getCedula());
                ResultSet r = stmt1.executeQuery();
         
                if(r.next()){
                  JOptionPane.showMessageDialog(null,"El cliente con esa cedula ya esta registrado");
                }else{
                stmt.setInt(1, c1.getCedula());
                stmt.setString(2, c1.getNombre());
                stmt.setString(3, c1.getApellido());
                stmt.setString(4, c1.getCorreoElectronico());
                stmt.executeUpdate();
                c.commit();
                JOptionPane.showMessageDialog(null, "Cliente registrado");
                
                }
            } catch (Exception e) {
                c.rollback();
                System.out.println(e.getMessage());
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            
            
            
            
             
        
        
        
        
    }
    
    public List<Cliente> mostrarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "Select * from clientes";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/sistemaBancario", "root", "Mi_COnstraseña")) {
            
            try(PreparedStatement stmt = c.prepareStatement(sql);ResultSet r = stmt.executeQuery()){
                while(r.next()){
                    String nombre=r.getString("nombre");
                    String apellido= r.getString("apellido");
                    String correo=r.getString("correo");
                    int cedula=r.getInt("cedula");
                    Cliente cliente = new Cliente(cedula,nombre,apellido,correo);
                    clientes.add(cliente);
                }
                
                
                
            } catch (Exception e) {
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }
    
    
}
