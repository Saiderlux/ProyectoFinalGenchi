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

public class SistemaUsuarios {

    private final ArrayList<Usuario> usuarios;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
    }

// Método para guardar los usuarios en un archivo
    public void guardarUsuarios() {
        try {
            File trabajadoresFile = new File("trabajadores.txt");
            File administradoresFile = new File("administradores.txt");

            // Validar si los archivos existen antes de escribir en ellos
            if (!trabajadoresFile.exists()) {
                trabajadoresFile.createNewFile();
            }
            if (!administradoresFile.exists()) {
                administradoresFile.createNewFile();
            }

            PrintWriter administradoresWriter;
            try (PrintWriter trabajadoresWriter = new PrintWriter(new FileWriter(trabajadoresFile))) {
                administradoresWriter = new PrintWriter(new FileWriter(administradoresFile));
                for (Usuario usuario : usuarios) {
                    if (usuario instanceof Trabajador) {
                        trabajadoresWriter.println(usuario.getNombre() + "," + usuario.getPassword());
                    } else if (usuario instanceof Administrador) {
                        administradoresWriter.println(usuario.getNombre() + "," + usuario.getPassword());
                    }
                }
            }
            administradoresWriter.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios");
        }
    }

// Método para agregar un usuario al sistema
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Método para verificar si hay al menos un administrador en el sistema
    public boolean archivoConDatos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo");
            }
            return false;
        } else if (archivo.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void darDeBajaTrabajador(String nombre) {
        File archivoViejo = new File("trabajadores.txt");
        if (!archivoViejo.exists()) {
            System.out.println("El archivo de trabajadores no existe.");
            return;
        }

        try (FileReader reader = new FileReader(archivoViejo); BufferedReader buffer = new BufferedReader(reader); FileWriter writer = new FileWriter("trabajadores.tmp", false); BufferedWriter bufferNuevo = new BufferedWriter(writer)) {

            String linea = "";
            boolean seElimino = false;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombreBorrar = partes[0];
                if (nombreBorrar.equals(nombre)) {
                    seElimino = true;
                    continue;
                }
                bufferNuevo.write(linea);
                bufferNuevo.newLine();
            }

            if (!seElimino) {
                System.out.println("No se encontró el trabajador con nombre de usuario: " + nombre);
                return;
            }

            archivoViejo.delete();
            File archivoNuevo = new File("trabajadores.txt");
            archivoNuevo.delete();
            new File("trabajadores.tmp").renameTo(archivoNuevo);
            System.out.println("El trabajador se ha dado de baja exitosamente");
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se encontró el archivo de trabajadores.");
        } catch (IOException e) {
            System.out.println("Error al dar de baja al trabajador.");
        }
    }
}
