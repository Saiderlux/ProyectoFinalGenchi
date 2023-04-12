/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

import java.text.ParseException;

class Medicamento extends Producto {
    private String laboratorio;

    public Medicamento(int id, String nombre, String descripcion, double precio, int cantidad, String fechaCaducidad, String laboratorio) throws ParseException {
        super(id, nombre, descripcion, precio, cantidad, fechaCaducidad);
        this.laboratorio = laboratorio;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public boolean validar() {
        return (super.validar() && !laboratorio.isEmpty());
    }

    @Override
    public String toString() {
        return super.toString() + "," + laboratorio;
    }
}


