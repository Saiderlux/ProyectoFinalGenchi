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
public class AccionesHigiene {

    public void guardarProducto(Higiene producto) throws ParseException {
        try {
            FileWriter writer = new FileWriter("productos_higiene.txt", true);
            try (BufferedWriter buffer = new BufferedWriter(writer)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese el ID del producto de higiene: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre del producto de higiene: ");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese la descripción del producto de higiene: ");
                String descripcion = scanner.nextLine();
                System.out.println("Ingrese el precio del producto de higiene: ");
                double precio = scanner.nextDouble();
                System.out.println("Ingrese la cantidad del producto de higiene: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese la fecha de caducidad del mproducto de higiene (dd/MM/yyyy): ");
                String fechaCaducidad = scanner.nextLine();
                System.out.println("Ingrese la marca del producto de higiene: ");
                String marca = scanner.nextLine();
                Higiene higiene = new Higiene(id, nombre, descripcion, precio, cantidad, fechaCaducidad, marca);
                String linea = higiene.getId() + "," + higiene.getNombre() + "," + higiene.getDescripcion() + ","
                        + higiene.getPrecio() + "," + higiene.getCantidad() + ","
                        + higiene.getFechaCaducidad() + "," + higiene.getMarca();
                buffer.write(linea);
                buffer.newLine();
            }
            System.out.println("El medicamento se ha dado de alta exitosamente");
        } catch (IOException e) {
            System.out.println("Error al dar de alta el medicamento");
        }
    }
    
    
    
    
    
     void darDeBajaProductoHigiene() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del medicamento a dar de baja: ");
            int idADarDeBaja = scanner.nextInt();
            scanner.nextLine();
            File archivoViejo = new File("productos_higiene.txt");
            File archivoNuevo = new File("productos_higiene.tmp");
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

    void editarProductoHigiene() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del medicamento a editar: ");
            int idAEditar = scanner.nextInt();
            scanner.nextLine();
            File archivoViejo = new File("productos_higiene.txt");
            File archivoNuevo = new File("productos_higiene.tmp");
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
                        String marcaNuevo = scanner.nextLine();
                        if (!marcaNuevo.equals("")) {
                            partes[6] = marcaNuevo;
                        }
                        seEdito = true;
                    }
                    bufferNuevo.write(String.join(",", partes));
                    bufferNuevo.newLine();
                }
            }
            bufferNuevo.close();
            if (!seEdito) {
                System.out.println("No se encontró el medicamento con ID " + idAEditar);
                return;
            }
            archivoViejo.delete();
            archivoNuevo.renameTo(archivoViejo);
            System.out.println("El producto de higiene se ha editado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al editar el producto de higiene");
        }
    }

    void consultarProductoHigiene() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del  a consultar: ");
            int idAConsultar = scanner.nextInt();
            scanner.nextLine();
            FileReader reader = new FileReader("producto_higiene.txt");
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
                    System.out.println("Marca: " + partes[6]);
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
