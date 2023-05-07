/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia.Inicio_De_Sesion;

import java.io.File;
import java.io.FileNotFoundException;
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
                String rol = datos[2];

                // Crear un nuevo usuario del tipo adecuado según su rol
                if (rol.equals("administrador")) {
                    usuarios.add(new Administrador(nombre, password));
                } else if (rol.equals("trabajador")) {
                    usuarios.add(new Trabajador(nombre, password));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuarios no encontrado.");
        }
    }

    // Método para guardar los usuarios en un archivo
    public void guardarUsuarios() {
        try (PrintWriter writerAdmin = new PrintWriter(new File("administradores.txt"));
             PrintWriter writerTrab = new PrintWriter(new File("trabajadores.txt"))) {
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
    public boolean hayAdministrador() {
        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals("administrador")) {
                return true;
            }
        }
        return false;
    }
}