/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package farmacia;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class MenuOpciones {

    
        public void Menu() throws IOException, ParseException{
    Scanner scanner = new Scanner(System.in);
        System.out.println("---IENVENIDO AL SISTEMA DE FARMACIA EL SOL---\n");
        System.out.println("Seleccione una opcion: ");
        System.out.println("1. Acciones de medicamentos");
        System.out.println("2. Acciones de productos de higiene");
        System.out.println("3. Salir\n");

        int opcion = scanner.nextInt();
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
                            ConsultarMedicamento ConsMed= new ConsultarMedicamento();
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