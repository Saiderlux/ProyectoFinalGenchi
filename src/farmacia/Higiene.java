/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

import java.text.ParseException;

public class Higiene extends Producto {
    private String marca;

    public Higiene(int id, String nombre, String descripcion, double precio, int cantidad, String fechaCaducidad, String marca) throws ParseException {
        super(id, nombre, descripcion, precio, cantidad, fechaCaducidad);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
