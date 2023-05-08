package farmacia;

import farmacia.Inicio_De_Sesion.Administrador;
import farmacia.Inicio_De_Sesion.IniciarAdmin;
import farmacia.Inicio_De_Sesion.IniciarTrabajador;
import farmacia.Inicio_De_Sesion.SistemaUsuarios;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuOpciones {

    Scanner scanner = new Scanner(System.in);
    int opcion;
    String usuario, contraseña;

    public void MenuGlobal() throws IOException, ParseException {

        SistemaUsuarios sistemaUsuarios = new SistemaUsuarios();
        IniciarAdmin iniciarAdmin = new IniciarAdmin();
        IniciarTrabajador iniciarTrabajador = new IniciarTrabajador();
        System.out.println("Iniciando sistema...\n");
        // Verificar si hay al menos un administrador en el sistema
        if (sistemaUsuarios.ComprobarAdmin("administrador.txt")) {
            System.out.println("No hay administradores en el sistema. Debe agregar uno.");
            System.out.print("Nombre de usuario: ");
            String nombre = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            sistemaUsuarios.agregarUsuario(new Administrador(nombre, password));
            sistemaUsuarios.guardarUsuarios();
        }

        while (true) {
            System.out.println("Bienvenido ¿Qué deseas realizar?(Seleccione una oción)");
            System.out.println("1. Iniciar el sistema.");
            System.out.println("2.Dar de alta nuevos usuarios");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce tu nombre de usuario");
                    usuario = scanner.nextLine();
                    System.out.println("Introduce tu contraseña");
                    contraseña = scanner.nextLine();

                    if (iniciarAdmin.inicio(usuario, contraseña) == true || iniciarTrabajador.inicio(usuario, contraseña) == true) {
                        MenuProductos();
                    } else {
                        System.out.println("**El usuario ingresado no está dado de alta en el sistema**");
                    }
                    break;
                case 2:
                    System.out.println("Introduce tu nombre de usuario");
                    usuario = scanner.nextLine();
                    System.out.println("Introduce tu contraseña");
                    contraseña = scanner.nextLine();
                    if (iniciarAdmin.inicio(usuario, contraseña) == true) {

                    }

                    break;
                default:
                    throw new AssertionError();
            }
        }

    }

    public void MenuProductos() throws IOException, ParseException {

        System.out.println("---BIENVENIDO AL SISTEMA DE FARMACIA EL SOL---\n");
        System.out.println("Seleccione una opcion: ");
        System.out.println("1. Acciones de medicamentos");
        System.out.println("2. Acciones de productos de higiene");
        System.out.println("3. Salir\n");
        opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                while (true) {
                    System.out.println("--MENU MEDICAMENTOS--");
                    System.out.println("Seleccione una opcion: ");
                    System.out.println("1. Dar de alta medicamento");
                    System.out.println("2. Dar de baja medicamento");
                    System.out.println("3. Editar medicamento");
                    System.out.println("4. Consultar medicamento");
                    System.out.println("5. Regresar al menu principal\n");
                    int opcionMed = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcionMed) {
                        case 1:
                            DarDeAltaMedicamento AltaMed = new DarDeAltaMedicamento();
                            System.out.println("--DAR DE ALTA MEDICAMENTO--");
                            AltaMed.darDeAlta();
                            break;
                        case 2:
                            DarDeBajaMedicamento BajaMed = new DarDeBajaMedicamento();
                            System.out.println("--DAR DE BAJA MEDICAMENTO");
                            BajaMed.darDeBaja();
                            break;
                        case 3:
                            EditarMedicamento EditMed = new EditarMedicamento();
                            System.out.println("--EDITAR MEDICAMENTO--");
                            EditMed.Editar();
                            break;
                        case 4:
                            ConsultarMedicamento ConsMed = new ConsultarMedicamento();
                            System.out.println("--CONSULTAR MEDICAMENTO--");
                            ConsMed.Consultar();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    if (opcionMed == 5) {
                        break;
                    }
                }
                break;
            case 2:
                while (true) {
                    System.out.println("--MENU PRODUCTOS DE HIGIENE--");
                    System.out.println("Seleccione una opción: ");
                    System.out.println("1. Dar de alta producto de higiene");
                    System.out.println("2. Dar de baja producto de higiene");
                    System.out.println("3. Editar producto de higiene");
                    System.out.println("4. Consultar producto de higiene");
                    System.out.println("5. Regresar al menú principal");
                    int opcionHig = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcionHig) {
                        case 1:
                            DarDeAltaProductoHigiene AltaProdHig = new DarDeAltaProductoHigiene();
                            AltaProdHig.darDeAlta();
                            break;
                        case 2:
                            DarDeBajaProductoHigiene BajaProdHig = new DarDeBajaProductoHigiene();
                            BajaProdHig.darDeBaja();
                            break;
                        case 3:
                            EditarProductoHigiene EditProDHig = new EditarProductoHigiene();
                            EditProDHig.Editar();
                            break;
                        case 4:
                            ConsultarProductoHigiene ConsProdHig = new ConsultarProductoHigiene();
                            ConsProdHig.Consultar();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    if (opcionHig == 5) {
                        break;
                    }
                }
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }
}
