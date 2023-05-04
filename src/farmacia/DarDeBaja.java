package farmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Alumno
 */
abstract class DarDeBaja {

    private String archivo;
    private String archivoTmp;
    private String tipoProducto;

    public DarDeBaja(String archivo, String archivoTmp, String tipoProducto) {
        this.archivo = archivo;
        this.archivoTmp = archivoTmp;
        this.tipoProducto = tipoProducto;
    }

    public void darDeBaja(int idADarDeBaja) {
        try {
            File archivoViejo = new File(archivo);
            File archivoNuevo = new File(archivoTmp);
            FileReader reader = new FileReader(archivoViejo);
            BufferedReader buffer = new BufferedReader(reader);
            FileWriter writer = new FileWriter(archivoNuevo, true);
            BufferedWriter bufferNuevo = new BufferedWriter(writer);
            String linea = "";
            boolean seElimino = false;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                if (id == idADarDeBaja) {
                    seElimino = true;
                    continue;
                }
                bufferNuevo.write(linea);
                bufferNuevo.newLine();
            }
            buffer.close();
            bufferNuevo.close();
            if (!seElimino) {
                System.out.println("No se encontró el " + tipoProducto + " con ID " + idADarDeBaja);
                return;
            }
            archivoViejo.delete();
            archivoNuevo.renameTo(archivoViejo);
            System.out.println("El " + tipoProducto + " se ha dado de baja exitosamente");
        } catch (IOException e) {
            System.out.println("Error al dar de baja el " + tipoProducto);
        }
    }
}

class DarDeBajaMedicamento extends DarDeBaja {

    public DarDeBajaMedicamento() {
        super("medicamento.txt", "medicamento.tmp", "medicamento");
    }
}

class DarDeBajaProductoHigiene extends DarDeBaja {

    public DarDeBajaProductoHigiene() {
        super("productos_higiene.txt", "productos_higiene.tmp", "producto de higiene");
    }
}
