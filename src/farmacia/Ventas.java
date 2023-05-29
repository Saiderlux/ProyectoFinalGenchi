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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        generarTicket(carrito, totalVenta, fecha, hora, cantidad, producto.getPrecio());

        System.out.println("Venta finalizada. Gracias por su compra.");
    }

    private Medicamento buscarMedicamentoPorId(String id) {
        try {
            FileReader fr = new FileReader(MEDICAMENTO_FILE);
            BufferedReader br = new BufferedReader(fr);

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

                    return new Medicamento(productId, productName, productDescription, productPrice, productQuantity, productLaboratory);
                }
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Higiene buscarProductoHigienePorId(String id) throws ParseException {
        try {
            FileReader fr = new FileReader(HIGIENE_FILE);
            BufferedReader br = new BufferedReader(fr);

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
        }

        return null;
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
            productosVendidos.append(producto.getId());
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

    private void actualizarCantidadProductos(List<Producto> carrito) {
        try {
            File medicamentoFile = new File(MEDICAMENTO_FILE);
            File higieneFile = new File(HIGIENE_FILE);

            List<String> newLines = new ArrayList<>();

            // Actualizar la cantidad de medicamentos
            if (medicamentoFile.exists()) {
                FileReader fr = new FileReader(medicamentoFile);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while ((line = br.readLine()) != null) {
                    String[] attributes = line.split(",");
                    String productId = attributes[0];
                    int productQuantity = Integer.parseInt(attributes[4]);

                    for (Producto producto : carrito) {
                        if (producto instanceof Medicamento && producto.getId().equals(productId)) {
                            productQuantity -= producto.getCantidad();
                            break;
                        }
                    }

                    String updatedLine = String.format("%s,%s", line, productQuantity);
                    newLines.add(updatedLine);
                }

                br.close();
                fr.close();

                // Guardar los cambios en el archivo
                FileWriter fw = new FileWriter(medicamentoFile);
                BufferedWriter bw = new BufferedWriter(fw);

                for (String newLine : newLines) {
                    bw.write(newLine);
                    bw.newLine();
                }

                bw.close();
                fw.close();
            }

            newLines.clear();

            // Actualizar la cantidad de productos de higiene
            if (higieneFile.exists()) {
                FileReader fr = new FileReader(higieneFile);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while ((line = br.readLine()) != null) {
                    String[] attributes = line.split(",");
                    String productId = attributes[0];
                    int productQuantity = Integer.parseInt(attributes[4]);

                    for (Producto producto : carrito) {
                        if (producto instanceof Higiene && producto.getId().equals(productId)) {
                            productQuantity -= producto.getCantidad();
                            break;
                        }
                    }

                    String updatedLine = String.format("%s,%s", line, productQuantity);
                    newLines.add(updatedLine);
                }

                br.close();
                fr.close();

                // Guardar los cambios en el archivo
                FileWriter fw = new FileWriter(higieneFile);
                BufferedWriter bw = new BufferedWriter(fw);

                for (String newLine : newLines) {
                    bw.write(newLine);
                    bw.newLine();
                }

                bw.close();
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
