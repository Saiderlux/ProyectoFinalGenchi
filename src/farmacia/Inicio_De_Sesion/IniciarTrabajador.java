/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia.Inicio_De_Sesion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Ssaid
 */
public class IniciarTrabajador {
    public boolean inicio(String nombreUsuario, String contrasena) {
        try {
            File archivoUsuarios = new File("trabajadores.txt");
            Scanner scanner = new Scanner(archivoUsuarios);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(linea, ",");

                String usuario = tokenizer.nextToken();
                String contraseña = tokenizer.nextToken();

                if (usuario.equals(nombreUsuario) && contraseña.equals(contrasena)) {
                    return true;
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo de usuarios.");
        }

        return false;
    }
}
