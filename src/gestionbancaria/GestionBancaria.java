
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
        CuentaDao dao1 = new CuentaDao();
        System.out.println("-------------BIENVENIDO A SU BANCO------------");
        do {            
            try {
                
            System.out.println("1. Registrar cliente."
                    + "\n2. Mostrar todos los clientes registrados."
                    + "\n3. Crear cuenta de ahorro."
                    + "\n4. Crear cuenta corriente."
                    + "\n5. Consultar cuenta."
                    + "\n6. Depositar."
                    + "\n7. Retirar."
                    + "\n8. Transferir."
                    + "\n9. Consultar historial"
                    + "\n10. Salir");
            
                System.out.print("Por favor digite una opcion: ");
                opcion = entrada.nextInt();
                if(opcion <1 || opcion>10){
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
                        dao.mostrarClientes();
                        break;
                        
                    case 3:
                        System.out.print("Digite la cedula del titular de la cuenta: ");
                        int cedula1 = entrada.nextInt();
                        entrada.nextLine();
                        System.out.print("Digite el tipo de cuenta: ");
                        String tipoCuenta = entrada.nextLine();
                        System.out.print("Digite el saldo de la cuenta: ");
                        double saldo=entrada.nextDouble();
                        entrada.nextLine();
                        System.out.print("Digite la sucursal: ");
                        String sucursal= entrada.nextLine();
                        Cuenta c2 = new CuentaAhorros(tipoCuenta, saldo, sucursal, cedula1);
                        dao1.crearAhorros(c2);
                        break;
                        
                    case 4:
                        System.out.print("Digite la cedula del titular de la cuenta: ");
                        int cedula2 = entrada.nextInt();
                        entrada.nextLine();
                        System.out.print("Digite el tipo de la cuenta: ");
                        String tipoCuenta2 = entrada.nextLine();
                        System.out.print("Digite el saldo de la cuenta: ");
                        double saldo2 = entrada.nextDouble();
                        entrada.nextLine();
                        System.out.print("Digite la sucursal: ");
                        String sucursal2= entrada.nextLine();
                        Cuenta c3 = new CuentaCorriente(tipoCuenta2,saldo2,sucursal2,cedula2);
                        dao1.crearCorriente(c3);
                        break;
                        
                    case 10:
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
