/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia.Inicio_De_Sesion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaUsuarios {

    private ArrayList<Usuario> usuarios;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
    }

    // Método para cargar los usuarios desde un archivo
    public void cargarUsuarios() {
        try (Scanner scanner = new Scanner(new File("usuarios.txt"))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");
                String nombre = datos[0];
                String password = datos[1];
                String archivoAdministradores= "administradores.txt";
                String archivoTrabajadores= "administradores.txt";
                // Crear un nuevo usuario del tipo adecuado
                if (archivoAdministradores.contains(nombre + "," + password)) {
                    usuarios.add(new Administrador(nombre, password));
                } else if (archivoTrabajadores.contains(nombre + "," + password)) {
                    usuarios.add(new Trabajador(nombre, password));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuarios no encontrado.");
        }
    }

   

    // Método para guardar los usuarios en un archivo
    public void guardarUsuarios() {
        try (PrintWriter writerAdmin = new PrintWriter(new File("administradores.txt")); PrintWriter writerTrab = new PrintWriter(new File("trabajadores.txt"))) {
            for (Usuario usuario : usuarios) {
                String linea = usuario.getNombre() + "," + usuario.getPassword();
                if (usuario.getRol().equals("administrador")) {
                    writerAdmin.println(linea);
                } else if (usuario.getRol().equals("trabajador")) {
                    writerTrab.println(linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al guardar usuarios.");
        }
    }

    // Método para agregar un usuario al sistema
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Método para autenticar a un usuario
    public Usuario autenticarUsuario(String nombre, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    // Método para verificar si hay al menos un administrador en el sistema
    public static boolean ComprobarAdmin(String fileName) {
        File file = new File(fileName);
        return file.length() > 0;
    }

    public void darDeBajaTrabajador(String nombre) {
        try {
            File inputFile = new File("trabajadores.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            boolean encontrado = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] datos = currentLine.split(",");
                if (datos[0].equals(nombre)) {
                    encontrado = true;
                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();

            if (!encontrado) {
                System.out.println("Trabajador no encontrado.");
                return;
            }

            if (!inputFile.delete()) {
                System.out.println("No se pudo eliminar el archivo de trabajadores.");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("No se pudo renombrar el archivo temporal.");
                return;
            }

            System.out.println("Trabajador dado de baja correctamente.");

        } catch (IOException e) {
            System.out.println("Error al dar de baja el trabajador: " + e.getMessage());
        }
    }

}
