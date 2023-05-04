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
abstract class Consultar {

    private String archivo;
    private String tipoProducto;
    private String procedencia;

    public Consultar(String archivo, String tipoProducto, String procedencia) {
        this.archivo = archivo;

        this.tipoProducto = tipoProducto;
        this.procedencia = procedencia;
    }

    public void Consultar() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del "+tipoProducto+" a consultar: ");
            int idAConsultar = scanner.nextInt();
            scanner.nextLine();
            FileReader reader = new FileReader(archivo);
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
                    System.out.println(procedencia + ": " + partes[6]);
                    break;
                }
            }
            buffer.close();
            if (!seEncontro) {
                System.out.println("No se encontró el "+tipoProducto+"o con ID " + idAConsultar);
            }
        } catch (IOException e) {
            System.out.println("Error al consultar el "+tipoProducto);
        }
    }
}

class ConsultarMedicamento extends Consultar {

    public ConsultarMedicamento() {
        super("medicamento.txt", "medicamento", "Laboratorio");
    }
}

class ConsultarProductoHigiene extends Consultar {

    public ConsultarProductoHigiene() {
        super("productos_higiene.txt", "producto de higiene", "Marca");
    }
}
