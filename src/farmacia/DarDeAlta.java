package farmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
abstract class DarDeAlta {

    private String tipoProducto;
    private String archivo;
    private String procedencia;
    private String identificador;

    public DarDeAlta(String archivo, String tipoProducto, String procedencia, String identificador) {
        this.archivo = archivo;
        this.tipoProducto = tipoProducto;
        this.procedencia = procedencia;
        this.identificador = identificador;
    }

    public void darDeAlta() throws ParseException {
        try {
            FileWriter writer = new FileWriter(archivo, true);
            ArrayList<String> lista = new ArrayList<>();
            try (BufferedWriter buffer = new BufferedWriter(writer)) {
                Scanner scanner = new Scanner(System.in);
                FileReader fr = new FileReader("medicamento.txt");
                BufferedReader br = new BufferedReader(fr);

                String line;
                boolean end = false;
                while ((line = br.readLine()) != null) {
                    String[] attributes = line.split(",");
                    lista.add(attributes[0]);
                }
                br.close();
                fr.close();
                while (!end) {
                    System.out.println("Ingrese el ID del " + tipoProducto + ": (Solamente el número)");
                    String id = scanner.next();
                    if (lista.contains(identificador + id)) {
                        System.out.println("El ID ingresado ya existe en el archivo. Ingresa otro ID");
                    } else {

                        System.out.println("Ingrese el nombre del " + tipoProducto + ":");
                        String nombre = scanner.next();
                        scanner.nextLine();

                        System.out.println("Ingrese la descripción del " + tipoProducto + ":");
                        String descripcion = scanner.nextLine();
                        
                        System.out.println("Ingrese el precio del " + tipoProducto + ":");
                        double precio = scanner.nextDouble();

                        System.out.println("Ingrese la cantidad del " + tipoProducto + ":");
                        int cantidad = scanner.nextInt();

                        System.out.println("Ingrese " + procedencia + " del " + tipoProducto + ":");
                        String marca_lab = scanner.next();
                        scanner.nextLine();
                        
                        if ("producto de higiene".equals(tipoProducto)) {
                            Higiene higiene = new Higiene(Integer.parseInt(id), nombre, descripcion, precio, cantidad, marca_lab);
                            String linea = "H" + higiene.getId() + "," + higiene.getNombre() + "," + higiene.getDescripcion() + ","
                                    + higiene.getPrecio() + "," + higiene.getCantidad() + "," + higiene.getMarca();
                            buffer.write(linea);
                            buffer.newLine();
                        } else if ("medicamento".equals(tipoProducto)) {
                            Medicamento medicamento = new Medicamento(Integer.parseInt(id), nombre, descripcion, precio, cantidad, marca_lab);
                            String linea = "M" + medicamento.getId() + "," + medicamento.getNombre() + "," + medicamento.getDescripcion() + ","
                                    + medicamento.getPrecio() + "," + medicamento.getCantidad() + "," + medicamento.getLaboratorio();
                            buffer.write(linea);
                            buffer.newLine();
                        }
                        end = true;
                    }
                }
            }
            System.out.println("El " + tipoProducto + " se ha dado de alta exitosamente ");
        } catch (IOException e) {
            System.out.println("Error al dar de alta el " + tipoProducto);
        }
    }
}

class DarDeAltaMedicamento extends DarDeAlta {

    public DarDeAltaMedicamento() {
        super("medicamento.txt", "medicamento", "el laboratorio", "M");
    }
}

class DarDeAltaProductoHigiene extends DarDeAlta {

    public DarDeAltaProductoHigiene() {
        super("productos_higiene.txt", "producto de higiene", "la marca", "H");
    }
}
