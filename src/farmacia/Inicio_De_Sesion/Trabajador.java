package farmacia.Inicio_De_Sesion;

// Clase para trabajadores, que hereda de Usuario
class Trabajador extends Usuario {

    public Trabajador(String nombre, String password) {
        super(nombre, password);
    }

    @Override
    public String getRol() {
        return "trabajador";
    }
}
