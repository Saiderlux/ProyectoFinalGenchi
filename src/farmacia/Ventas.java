/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ventas {

    private static final String MEDICAMENTO_FILE = "medicamento.txt";
    private static final String HIGIENE_FILE = "productos_higiene.txt";
    private static final String VENTAS_FILE = "ventas.txt";
    private static final String TICKET_FILE = "FarmaciaAmor.pdf";

    public void iniciarVenta() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a Farmacia Amor");
        System.out.println("============================");
        int cantidad = 0;
        Producto producto = null;
        boolean ventaActiva = true;
        List<Producto> carrito = new ArrayList<>();

        while (ventaActiva) {
            System.out.println("Ingrese el ID del producto a vender (M para medicamento, H para producto de higiene):");
            String id = scanner.next();
            scanner.nextLine();

            if (id.startsWith("M")) {
                producto = buscarMedicamentoPorId(id);
            } else if (id.startsWith("H")) {
                producto = buscarProductoHigienePorId(id);
            }

            if (producto == null) {
                System.out.println("El producto con el ID ingresado no existe. Intente nuevamente.");
                continue;
            }

            System.out.println("Ingrese la cantidad a vender:");
            cantidad = scanner.nextInt();
            scanner.nextLine();

            if (cantidad > producto.getCantidad()) {
                System.out.println("No hay suficientes unidades en inventario. Cantidad disponible: " + producto.getCantidad());
                continue;
            }

            if (producto.getCantidad() - cantidad < 10) {
                System.out.println("Quedan muy pocas existencias del producto. Considere dar de alta más productos en el sistema.");
            }

            producto.setCantidad(producto.getCantidad() - cantidad);
            carrito.add(producto);

            System.out.println("Producto agregado al carrito de compra.");

            System.out.println("¿Desea agregar otro producto? (S/N):");
            String opcion = scanner.next();
            scanner.nextLine();

            if (opcion.equalsIgnoreCase("N")) {
                ventaActiva = false;
            }
        }

        double totalVenta = calcularTotalVenta(carrito, cantidad);
        String fecha = obtenerFechaActual();
        String hora = obtenerHoraActual();

        guardarVenta(carrito, totalVenta, fecha, hora);
        actualizarCantidadProductos(carrito, cantidad);
        generarTicket(carrito, totalVenta, fecha, hora, cantidad, producto.getPrecio());

        System.out.println("Venta finalizada. Gracias por su compra.");
    }

    private Medicamento buscarMedicamentoPorId(String id) {
        Medicamento medicamento = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(MEDICAMENTO_FILE);
            br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes[0].equals(id)) {
                    String productId = attributes[0].substring(1);
                    String productName = attributes[1];
                    String productDescription = attributes[2];
                    double productPrice = Double.parseDouble(attributes[3]);
                    int productQuantity = Integer.parseInt(attributes[4]);
                    String productLaboratory = attributes[5];
                    String formaFarmaceutica = attributes[6];

                    return new Medicamento(productId, productName, productDescription, productPrice, productQuantity, productLaboratory, formaFarmaceutica);
                }
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return medicamento;
    }

    private Higiene buscarProductoHigienePorId(String id) throws ParseException {
        Higiene higiene = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(HIGIENE_FILE);
            br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes[0].equals(id)) {
                    String productId = attributes[0].substring(1);
                    String productName = attributes[1];
                    String productDescription = attributes[2];
                    double productPrice = Double.parseDouble(attributes[3]);
                    int productQuantity = Integer.parseInt(attributes[4]);
                    String productBrand = attributes[5];

                    return new Higiene(productId, productName, productDescription, productPrice, productQuantity, productBrand);
                }
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return higiene;
    }

    private double calcularTotalVenta(List<Producto> carrito, int cantidad) {
        double total = 0.0;
        for (Producto producto : carrito) {
            total += producto.getPrecio() * cantidad;
        }
        return total;
    }

    private String obtenerFechaActual() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String obtenerHoraActual() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private int obtenerSiguienteIdVenta() {
        int maxId = 0;

        try {
            FileReader fr = new FileReader(VENTAS_FILE);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                int ventaId = Integer.parseInt(attributes[0]);
                if (ventaId > maxId) {
                    maxId = ventaId;
                }
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return maxId + 1;
    }

    private String obtenerProductosVendidos(List<Producto> carrito) {
        StringBuilder productosVendidos = new StringBuilder();
        for (int i = 0; i < carrito.size(); i++) {
            Producto producto = carrito.get(i);
            String productId = producto.getId();
            if (producto instanceof Medicamento) {
                productId = "M" + productId;
            } else if (producto instanceof Higiene) {
                productId = "H" + productId;
            }
            productosVendidos.append(productId);
            if (i < carrito.size() - 1) {
                productosVendidos.append("|");
            }
        }
        return productosVendidos.toString();
    }

    private void generarTicket(List<Producto> carrito, double totalVenta, String fecha, String hora, int cantidad, double precio) {
        try {
            FileWriter writer = new FileWriter(TICKET_FILE);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write("Farmacia Amor - Ticket de Venta");
            buffer.newLine();
            buffer.write("================================");
            buffer.newLine();
            buffer.newLine();
            buffer.write("Fecha: " + fecha);
            buffer.newLine();
            buffer.write("Hora: " + hora);
            buffer.newLine();
            buffer.newLine();
            buffer.write("Productos:");
            buffer.newLine();
            for (Producto producto : carrito) {
                buffer.write("- " + producto.getNombre() + "(" + cantidad + "x" + precio + ")");
                buffer.newLine();
            }
            buffer.newLine();
            buffer.write("Total: $" + totalVenta);
            buffer.newLine();

            buffer.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarVenta(List<Producto> carrito, double totalVenta, String fecha, String hora) {
        try {
            FileWriter writer = new FileWriter(VENTAS_FILE, true);
            BufferedWriter buffer = new BufferedWriter(writer);

            int ventaId = obtenerSiguienteIdVenta();
            String productosVendidos = obtenerProductosVendidos(carrito);
            String lineaVenta = String.format("%d,%s,%.2f,%s,%s", ventaId, productosVendidos, totalVenta, fecha, hora);

            buffer.write(lineaVenta);
            buffer.newLine();

            buffer.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarCantidadProductos(List<Producto> carrito, int cantidad) {
        for (Producto producto : carrito) {
            if (producto instanceof Medicamento) {
                actualizarCantidadMedicamento((Medicamento) producto, cantidad);
            } else if (producto instanceof Higiene) {
                actualizarCantidadHigiene((Higiene) producto, cantidad);
            }
        }
    }

    private void actualizarCantidadMedicamento(Medicamento medicamento, int cantidad) {
        try {
            File archivo = new File(MEDICAMENTO_FILE);
            File archivoTemporal = new File("temp_medicamento.tmp");

            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(archivoTemporal);
            BufferedWriter bw = new BufferedWriter(fw);

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                String id = attributes[0];
                int cantidadFinal = Integer.parseInt(attributes[4]);

                if (id.equals("M" + medicamento.getId())) {
                    cantidadFinal -= cantidad;
                    if (cantidadFinal < 0) {
                        cantidadFinal = 0;
                    }
                    line = String.format("%s,%s,%s,%.2f,%d,%s,%s", id, medicamento.getNombre(), medicamento.getDescripcion(),
                            medicamento.getPrecio(), cantidadFinal, medicamento.getLaboratorio(),
                            medicamento.getForma_Farmaceutica());
                }
                bw.write(line);
                bw.newLine();
            }

            br.close();
            fr.close();
            bw.close();
            fw.close();

            if (archivo.delete()) {
                if (archivoTemporal.renameTo(archivo)) {
                    System.out.println("El archivo temporal se ha renombrado exitosamente.");
                } else {
                    System.out.println("No se pudo renombrar el archivo temporal.");
                }
            } else {
                System.out.println("No se pudo eliminar el archivo original.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarCantidadHigiene(Higiene higiene, int cantidad) {
        try {
            File archivo = new File(HIGIENE_FILE);
            File archivoTemporal = new File("temp_higiene.tmp");

            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(archivoTemporal);
            BufferedWriter bw = new BufferedWriter(fw);

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                String id = attributes[0];
                int cantidadFinal = Integer.parseInt(attributes[4]);

                if (id.equals("H" + higiene.getId())) {
                    cantidadFinal -= cantidad;
                    if (cantidadFinal < 0) {
                        cantidadFinal = 0;
                    }
                    line = String.format("%s,%s,%s,%.2f,%d,%s", id, higiene.getNombre(), higiene.getDescripcion(),
                            higiene.getPrecio(), cantidadFinal, higiene.getMarca());
                }
                bw.write(line);
                bw.newLine();
            }

            br.close();
            fr.close();
            bw.close();
            fw.close();

            if (archivo.delete()) {
                if (archivoTemporal.renameTo(archivo)) {
                    System.out.println("El archivo temporal se ha renombrado exitosamente.");
                } else {
                    System.out.println("No se pudo renombrar el archivo temporal.");
                }
            } else {
                System.out.println("No se pudo eliminar el archivo original.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
