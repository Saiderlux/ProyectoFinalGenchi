/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia.Inicio_De_Sesion;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Ssaid
 */
public class AccionesSesion {

    public void Acciones() {
        SistemaUsuarios sistemaUsuarios = new SistemaUsuarios();
        sistemaUsuarios.cargarUsuarios();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("Menú de opciones:");
            System.out.println("1. Dar de alta un administrador");
            System.out.println("2. Dar de alta un trabajador");
            System.out.println("3. Dar de baja un trabajador");
            System.out.println("4. Salir");

            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de usuario: ");
                    String nombreAdmin = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String passwordAdmin = scanner.nextLine();

                    sistemaUsuarios.agregarUsuario(new Administrador(nombreAdmin, passwordAdmin));
                    sistemaUsuarios.guardarUsuarios();
                    System.out.println("Administrador agregado correctamente.");
                    break;
                case 2:
                    System.out.print("Nombre de usuario: ");
                    String nombreTrab = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String passwordTrab = scanner.nextLine();

                    sistemaUsuarios.agregarUsuario(new Trabajador(nombreTrab, passwordTrab));
                    sistemaUsuarios.guardarUsuarios();
                    System.out.println("Trabajador agregado correctamente.");
                    break;
                case 3:
                    System.out.println("Introduce el nombre de usuario a eliminar");
                    String usuarioBorrar = scanner.nextLine();
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    if (respuesta == JOptionPane.YES_OPTION) {
                        sistemaUsuarios.darDeBajaTrabajador(usuarioBorrar);
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
}
