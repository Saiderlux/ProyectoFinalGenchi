/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;


class Medicamento extends Producto {
    private String laboratorio;

    public Medicamento(String id, String nombre, String descripcion, double precio, int cantidad, String laboratorio) {
        super(id, nombre, descripcion, precio, cantidad);
        this.laboratorio = laboratorio;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }
}


