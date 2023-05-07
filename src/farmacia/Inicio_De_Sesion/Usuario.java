/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia.Inicio_De_Sesion;

// Clase base para usuarios
abstract class Usuario {
    protected String nombre;
    protected String password;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    // Método abstracto para obtener el rol del usuario
    public abstract String getRol();
}



