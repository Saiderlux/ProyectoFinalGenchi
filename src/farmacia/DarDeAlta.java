
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
 * @author Alumno
 */
abstract class DarDeAlta {
    private String tipoProducto;
    private String archivo;

    public DarDeAlta(String archivo, String tipoProducto) {
        this.archivo = archivo;
        this.tipoProducto = tipoProducto;
    }

    public void darDeBaja(int idADarDeBaja) throws ParseException {
        try {
            FileWriter writer = new FileWriter(archivo, true);
            try (BufferedWriter buffer = new BufferedWriter(writer)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese el ID del"+ tipoProducto + ": ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre del " + tipoProducto +":");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese la descripción del "+ tipoProducto +":");
                String descripcion = scanner.nextLine();
                System.out.println("Ingrese el precio del " + tipoProducto +":");
                double precio = scanner.nextDouble();
                System.out.println("Ingrese la cantidad del " + tipoProducto + ":");
                int cantidad = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese la fecha de caducidad del " + tipoProducto + " (dd/MM/yyyy): ");
                String fechaCaducidad = scanner.nextLine();
                System.out.println("Ingrese la marca del " + tipoProducto + ":");
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

    class DarDeAltaMedicamento extends DarDeAlta {

        public DarDeAltaMedicamento() {
            super("medicamento.txt","medicamento");
        }
    }

    class DarDeAltaProductoHigiene extends DarDeAlta {

        public DarDeAltaProductoHigiene() {
            super("productos_higiene.txt", "producto de higiene");
        }
    }
}
