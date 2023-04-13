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
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Ssaid
 */
public class AccionesMedicamento {

    static void darDeAltaMedicamento() {
        try {
            FileWriter writer = new FileWriter("medicamento.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del medicamento: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el nombre del medicamento: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese la descripción del medicamento: ");
            String descripcion = scanner.nextLine();
            System.out.println("Ingrese el precio del medicamento: ");
            double precio = scanner.nextDouble();
            System.out.println("Ingrese la cantidad de medicamento: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese la fecha de caducidad del medicamento (dd/MM/yyyy): ");
            String fechaCaducidadStr = scanner.nextLine();

            System.out.println("Ingrese el laboratorio del medicamento: ");
            String laboratorio = scanner.nextLine();
            Medicamento medicamento = new Medicamento(id, nombre, descripcion, precio, cantidad, fechaCaducidadStr, laboratorio);
            String linea = medicamento.getId() + "," + medicamento.getNombre() + "," + medicamento.getDescripcion() + ","
                    + medicamento.getPrecio() + "," + medicamento.getCantidad() + ","
                    + medicamento.getFechaCaducidad() + "," + medicamento.getLaboratorio();
            buffer.write(linea);
            buffer.newLine();
            buffer.close();
            System.out.println("El medicamento se ha dado de alta exitosamente");
        } catch (IOException e) {
            System.out.println("Error al dar de alta el medicamento");
        } catch (ParseException e) {
            System.out.println("Error al parsear la fecha de caducidad");
        }
    }

    static void darDeBajaMedicamento() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del medicamento a dar de baja: ");
            int idADarDeBaja = scanner.nextInt();
            scanner.nextLine();
            File archivoViejo = new File("medicamento.txt");
            File archivoNuevo = new File("medicamento.tmp");
            FileReader reader = new FileReader(archivoViejo);
            BufferedReader buffer = new BufferedReader(reader);
            FileWriter writer = new FileWriter(archivoNuevo, true);
            BufferedWriter bufferNuevo = new BufferedWriter(writer);
            String linea = "";
            boolean seElimino = false;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                if (id == idADarDeBaja) {
                    seElimino = true;
                    continue;
                }
                bufferNuevo.write(linea);
                bufferNuevo.newLine();
            }
            buffer.close();
            bufferNuevo.close();
            if (!seElimino) {
                System.out.println("No se encontró el medicamento con ID " + idADarDeBaja);
                return;
            }
            archivoViejo.delete();
            archivoNuevo.renameTo(archivoViejo);
            System.out.println("El medicamento se ha dado de baja exitosamente");
        } catch (IOException e) {
            System.out.println("Error al dar de baja el medicamento");
        }
    }

    static void editarMedicamento() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del medicamento a editar: ");
            int idAEditar = scanner.nextInt();
            scanner.nextLine();
            File archivoViejo = new File("medicamento.txt");
            File archivoNuevo = new File("medicamento.tmp");
            FileReader reader = new FileReader(archivoViejo);
            BufferedReader buffer = new BufferedReader(reader);
            FileWriter writer = new FileWriter(archivoNuevo, true);
            BufferedWriter bufferNuevo = new BufferedWriter(writer);
            String linea = "";
            boolean seEdito = false;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                if (id == idAEditar) {
                    System.out.println("Ingrese el nuevo nombre del medicamento (o Enter para no cambiar): ");
                    String nombreNuevo = scanner.nextLine();
                    if (!nombreNuevo.equals("")) {
                        partes[1] = nombreNuevo;
                    }
                    System.out.println("Ingrese la nueva descripción del medicamento (o Enter para no cambiar): ");
                    String descripcionNueva = scanner.nextLine();
                    if (!descripcionNueva.equals("")) {
                        partes[2] = descripcionNueva;
                    }
                    System.out.println("Ingrese el nuevo precio del medicamento (o 0 para no cambiar): ");
                    double precioNuevo = scanner.nextDouble();
                    scanner.nextLine();
                    if (precioNuevo != 0) {
                        partes[3] = Double.toString(precioNuevo);
                    }
                    System.out.println("Ingrese la nueva cantidad de medicamento (o 0 para no cambiar): ");
                    int cantidadNueva = scanner.nextInt();
                    scanner.nextLine();
                    if (cantidadNueva != 0) {
                        partes[4] = Integer.toString(cantidadNueva);
                    }
                    System.out.println("Ingrese la nueva fecha de caducidad del medicamento (en formato dd/mm/yyyy, o Enter para no cambiar): ");
                    String fechaNueva = scanner.nextLine();
                    if (!fechaNueva.equals("")) {
                        partes[5] = fechaNueva;
                    }
                    System.out.println("Ingrese el nuevo laboratorio del medicamento (o Enter para no cambiar): ");
                    String laboratorioNuevo = scanner.nextLine();
                    if (!laboratorioNuevo.equals("")) {
                        partes[6] = laboratorioNuevo;
                    }
                    seEdito = true;
                }
                bufferNuevo.write(String.join(",", partes));
                bufferNuevo.newLine();
            }
            buffer.close();
            bufferNuevo.close();
            if (!seEdito) {
                System.out.println("No se encontró el medicamento con ID " + idAEditar);
                return;
            }
            archivoViejo.delete();
            archivoNuevo.renameTo(archivoViejo);
            System.out.println("El medicamento se ha editado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al editar el medicamento");
        }
    }

    static void consultarMedicamento() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del medicamento a consultar: ");
            int idAConsultar = scanner.nextInt();
            scanner.nextLine();
            FileReader reader = new FileReader("medicamento.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String linea = "";
            boolean seEncontro = false;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                if (id == idAConsultar) {
                    seEncontro = true;
                    System.out.println("ID: " + partes[0]);
                    System.out.println("Nombre: " + partes[1]);
                    System.out.println("Descripción: " + partes[2]);
                    System.out.println("Precio: " + partes[3]);
                    System.out.println("Cantidad: " + partes[4]);
                    System.out.println("Fecha de caducidad: " + partes[5]);
                    System.out.println("Laboratorio: " + partes[6]);
                    break;
                }
            }
            buffer.close();
            if (!seEncontro) {
                System.out.println("No se encontró el medicamento con ID " + idAConsultar);
            }
        } catch (IOException e) {
            System.out.println("Error al consultar el medicamento");
        }
    }
}
