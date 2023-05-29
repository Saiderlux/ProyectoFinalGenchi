/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ssaid
 */
abstract class Editar {

    private String archivo;
    private String archivoTmp;
    private String tipoProducto;
    private String procedencia;

    public Editar(String archivo, String archivoTmp, String tipoProducto, String procedencia) {
        this.archivo = archivo;
        this.archivoTmp = archivoTmp;
        this.tipoProducto = tipoProducto;
        this.procedencia = procedencia;
    }

    public void Editar() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del medicamento a editar: ");
            String idAEditar = scanner.next();

            File archivoViejo = new File(archivo);
            File archivoNuevo = new File(archivoTmp);
            FileReader reader = new FileReader(archivoViejo);
            BufferedWriter bufferNuevo;
            boolean seEdito;
            try (BufferedReader buffer = new BufferedReader(reader)) {
                FileWriter writer = new FileWriter(archivoNuevo, true);
                bufferNuevo = new BufferedWriter(writer);
                String linea = "";
                seEdito = false;
                while ((linea = buffer.readLine()) != null) {
                    String[] partes = linea.split(",");
                    String id = partes[0];
                    if (id == idAEditar) {
                        int opcion = 0;
                        do {
                            System.out.println("Seleccione la opción que desea editar:");
                            System.out.println("1. Nombre");
                            System.out.println("2. Descripción");
                            System.out.println("3. Precio");
                            System.out.println("4. Cantidad");
                            System.out.println("5. Fecha de caducidad");
                            System.out.println("6. " + procedencia);
                            System.out.println("7. Salir");
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcion) {
                                case 1:
                                    System.out.println("Ingrese el nuevo nombre del " + tipoProducto + ":");
                                    String nombreNuevo = scanner.nextLine();
                                    partes[1] = nombreNuevo;
                                    break;
                                case 2:
                                    System.out.println("Ingrese la nueva descripción del " + tipoProducto + ":");
                                    String descripcionNueva = scanner.nextLine();
                                    partes[2] = descripcionNueva;
                                    break;
                                case 3:
                                    System.out.println("Ingrese el nuevo precio del " + tipoProducto + ":");
                                    double precioNuevo = scanner.nextDouble();
                                    scanner.nextLine();
                                    partes[3] = Double.toString(precioNuevo);
                                    break;
                                case 4:
                                    System.out.println("Ingrese la nueva cantidad del " + tipoProducto + ":");
                                    int cantidadNueva = scanner.nextInt();
                                    scanner.nextLine();
                                    partes[4] = Integer.toString(cantidadNueva);
                                    break;
                                case 5:
                                    System.out.println("Ingrese la nueva fecha de caducidad del " + tipoProducto + " (en formato dd/mm/yyyy):");
                                    String fechaNueva = scanner.nextLine();
                                    partes[5] = fechaNueva;
                                    break;
                                case 6:
                                    System.out.println("Ingrese el nuevo laboratorio del " + tipoProducto + ":");
                                    String laboratorioNuevo = scanner.nextLine();
                                    partes[6] = laboratorioNuevo;
                                    break;
                                case 7:
                                    break;
                                default:
                                    System.out.println("Opción inválida");
                                    break;
                            }
                        } while (opcion != 7);
                        seEdito = true;
                    }
                    bufferNuevo.write(String.join(",", partes));
                    bufferNuevo.newLine();
                }
            }
            bufferNuevo.close();
            if (!seEdito) {
                System.out.println("No se encontró el " + tipoProducto + " con ID " + idAEditar);
                return;
            }
            archivoViejo.delete();
            archivoNuevo.renameTo(archivoViejo);
            System.out.println("El " + tipoProducto + " se ha editado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al editar el " + tipoProducto);
        }
    }
}

class EditarMedicamento extends Editar {

    public EditarMedicamento() {
        super("medicamento.txt", "medicamento.tmp", "medicamento", "Laboratorio");
    }
}

class EditarProductoHigiene extends Editar {

    public EditarProductoHigiene() {
        super("productos_higiene.txt", "productos_higiene.tmp", "producto de higiene", "Marca");
    }
}
