
package gestionbancaria;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GestionBancaria {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in) ;
        int opcion;
        boolean bandera = false;
        ClienteDao dao = new ClienteDao();
        System.out.println("-------------BIENVENIDO A SU BANCO------------");
        do {            
            try {
                
            System.out.println("1.Registrar cliente."
                    + "\n2.Mostrar todos los clientes registrados."
                    + "\n3.Crear cuenta de ahorro."
                    + "\n4. Crear cuenta corriente."
                    + "'n4. Consultar cuenta."
                    + "\n5. Depositar."
                    + "\n6. Retirar."
                    + "\n7. Transferir."
                    + "\n8. Consultar historial"
                    + "\n9. Salir");
            
                System.out.print("Por favor digite una opcion: ");
                opcion = entrada.nextInt();
                if(opcion <1 || opcion>9){
                    System.out.println("Por favor numeros del 1-9");
                }
                switch (opcion) {
                    case 1:
                        entrada.nextLine();
                        System.out.print("Digite el nombre del cliente: ");
                        String nombre = entrada.nextLine();
                        System.out.print("Digite el apellido del cliente: ");
                        String Apellido = entrada.nextLine();
                        System.out.print("Digite la cedula: ");
                        int cedula = entrada.nextInt();
                        entrada.nextLine();
                        System.out.print("Digite el correo: ");
                        String correo = entrada.nextLine();
                        Cliente c1 = new Cliente(cedula,nombre,Apellido,correo);
                        dao.crearCliente(c1);
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                        
                    case 9:
                        bandera=true;
                        break;
                }
            
            } catch (Exception e) {
                System.out.println("Opcion invalida. Digite nuevamente");
                entrada.nextLine();
            }
        
        } while (bandera==false);
    }
    
}
