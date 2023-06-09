
package farmacia.Inicio_De_Sesion;

// Clase base para usuarios
public abstract class Usuario {
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

    // Método inicio polimórfico
    public abstract boolean inicio();
}