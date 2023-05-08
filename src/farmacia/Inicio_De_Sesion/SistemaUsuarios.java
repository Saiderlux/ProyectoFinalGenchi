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
import java.util.Iterator;
import java.util.Scanner;

public class SistemaUsuarios {

    private ArrayList<Usuario> usuarios;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
    }

    // Método para cargar los usuarios desde un archivo
    public void cargarUsuarios() {
        Scanner trabajadoresScanner = new Scanner("trabajadores.txt");
        Scanner administradoresScanner = new Scanner("administradores.txt");
        while (trabajadoresScanner.hasNextLine()) {
            String[] datos = trabajadoresScanner.nextLine().split(",");
            String nombre = datos[0];
            String password = datos[1];
            usuarios.add(new Trabajador(nombre, password));
        }
        while (administradoresScanner.hasNextLine()) {
            String[] datos = administradoresScanner.nextLine().split(",");
            String nombre = datos[0];
            String password = datos[1];
            usuarios.add(new Administrador(nombre, password));
        }
        trabajadoresScanner.close();
        administradoresScanner.close();
    }

// Método para guardar los usuarios en un archivo
    public void guardarUsuarios() {
        try {
            PrintWriter trabajadoresWriter = new PrintWriter(new FileWriter("trabajadores.txt"));
            PrintWriter administradoresWriter = new PrintWriter(new FileWriter("administradores.txt"));

            for (Usuario usuario : usuarios) {
                if (usuario instanceof Trabajador) {
                    trabajadoresWriter.println(usuario.getNombre() + "," + usuario.getPassword());
                } else if (usuario instanceof Administrador) {
                    administradoresWriter.println(usuario.getNombre() + "," + usuario.getPassword());
                }
            }

            trabajadoresWriter.close();
            administradoresWriter.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios");
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
        try {
            File archivoViejo = new File("trabajadores.txt");
            File archivoNuevo = new File("trabajadores.tmp");
            FileReader reader = new FileReader(archivoViejo);
            BufferedReader buffer = new BufferedReader(reader);
            FileWriter writer = new FileWriter(archivoNuevo, true);
            BufferedWriter bufferNuevo = new BufferedWriter(writer);
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
            buffer.close();
            bufferNuevo.close();
            if (!seElimino) {
                System.out.println("No se encontró el trabajador con nombre de usuario: " + nombre);
                return;
            }
            archivoViejo.delete();
            archivoNuevo.renameTo(archivoViejo);
            System.out.println("El trabajador se ha dado de baja exitosamente");
        } catch (IOException e) {
            System.out.println("Error al dar de baja al trabajador");
        }
    }

}
