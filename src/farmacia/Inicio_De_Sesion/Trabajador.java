package farmacia.Inicio_De_Sesion;

// Clase para trabajadores, que hereda de Usuario

import java.util.Iterator;

public class Trabajador extends Usuario {

    static Iterator<Trabajador> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Trabajador(String nombre, String password) {
        super(nombre, password);
    }

    @Override
    public String getRol() {
        return "trabajador";
    }
}
