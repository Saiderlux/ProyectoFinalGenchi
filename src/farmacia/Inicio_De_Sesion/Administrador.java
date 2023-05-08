/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia.Inicio_De_Sesion;

// Clase para administradores, que hereda de Usuario
public class Administrador extends Usuario {

    public Administrador(String nombre, String password) {
        super(nombre, password);
    }

    @Override
    public String getRol() {
        return "administrador";
    }
}
