package farmacia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
abstract class DarDeAlta {

    private String tipoProducto;
    private String archivo;
    private String procedencia;

    public DarDeAlta(String archivo, String tipoProducto, String procedencia) {
        this.archivo = archivo;
        this.tipoProducto = tipoProducto;
        this.procedencia = procedencia;
    }

    public void darDeAlta() throws ParseException {
        try {
            FileWriter writer = new FileWriter(archivo, true);
            try (BufferedWriter buffer = new BufferedWriter(writer)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese el ID del " + tipoProducto + ": ");
                int idADarDeAlta = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre del " + tipoProducto + ":");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese la descripción del " + tipoProducto + ":");
                String descripcion = scanner.nextLine();
                System.out.println("Ingrese el precio del " + tipoProducto + ":");
                double precio = scanner.nextDouble();
                System.out.println("Ingrese la cantidad del " + tipoProducto + ":");
                int cantidad = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese la fecha de caducidad del " + tipoProducto + " (dd/MM/yyyy): ");
                String fechaCaducidad = scanner.nextLine();
                System.out.println("Ingrese " +procedencia+ " del " + tipoProducto + ":");
                String marca_lab = scanner.nextLine();
                if("producto de higiene".equals(tipoProducto)){
                    Higiene higiene = new Higiene(idADarDeAlta, nombre, descripcion, precio, cantidad, fechaCaducidad, marca_lab);
                String linea = higiene.getId() + "," + higiene.getNombre() + "," + higiene.getDescripcion() + ","
                        + higiene.getPrecio() + "," + higiene.getCantidad() + ","
                        + higiene.getFechaCaducidad() + "," + higiene.getMarca();
                buffer.write(linea);
                buffer.newLine();
                }else if("medicamento".equals(tipoProducto)){
                       Medicamento medicamento = new Medicamento(idADarDeAlta, nombre, descripcion, precio, cantidad, fechaCaducidad, marca_lab);
                String linea = medicamento.getId() + "," + medicamento.getNombre() + "," + medicamento.getDescripcion() + ","
                        + medicamento.getPrecio() + "," + medicamento.getCantidad() + ","
                        + medicamento.getFechaCaducidad() + "," + medicamento.getLaboratorio();
                buffer.write(linea);
                buffer.newLine();
                }
            }
            System.out.println("El "+tipoProducto+" se ha dado de alta exitosamente ");
        } catch (IOException e) {
            System.out.println("Error al dar de alta el "+tipoProducto);
        }
    }
}

class DarDeAltaMedicamento extends DarDeAlta {

    public DarDeAltaMedicamento() {
        super("medicamento.txt", "medicamento", "el laboratorio");
    }
}

class DarDeAltaProductoHigiene extends DarDeAlta {

    public DarDeAltaProductoHigiene() {
        super("productos_higiene.txt", "producto de higiene","la marca");
    }
}
