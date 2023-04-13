/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

import java.io.*;
import java.text.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        AccionesMedicamento MedAct = new AccionesMedicamento();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Dar de alta medicamento");
            System.out.println("2. Dar de baja medicamento");
            System.out.println("3. Editar medicamento");
            System.out.println("4. Consultar medicamento");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1:
                    MedAct.darDeAltaMedicamento();
                    break;
                case 2:
                    MedAct.darDeBajaMedicamento();
                    break;
                case 3:
                    MedAct.editarMedicamento();
                    break;
                case 4:
                    MedAct.consultarMedicamento();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    
}

    
