/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia.Inicio_De_Sesion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ssaid
 */
public class IniciarAdmin {

    public boolean inicio(Usuario usuario) {
        try {
            FileReader reader = new FileReader("administradores.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombreUsuario = partes[0];
                String password = partes[1];
                if (nombreUsuario.equals(usuario.getNombre()) && password.equals(usuario.getPassword())) {
                    buffer.close();
                    return true;
                }
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }
}