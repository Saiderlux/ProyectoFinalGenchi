package farmacia.Inicio_De_Sesion;

// Clase para trabajadores, que hereda de Usuario


public class Trabajador extends Usuario {

    public Trabajador(String nombre, String password) {
        super(nombre, password);
    }

    // Sobrescribir el método inicio para la clase Trabajador
    @Override
    public boolean inicio() {
        IniciarTrabajador iniciarTrabajador = new IniciarTrabajador();
        return iniciarTrabajador.inicio(this);
    }
}