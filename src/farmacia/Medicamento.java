/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

class Medicamento extends Producto {

    private String laboratorio;
    private String forma_Farmaceutica;

    public Medicamento(String id, String nombre, String descripcion, double precio, int cantidad, String laboratorio, String forma_Farmaceutica) {
        super(id, nombre, descripcion, precio, cantidad);
        this.laboratorio = laboratorio;
        this.forma_Farmaceutica = forma_Farmaceutica;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getForma_Farmaceutica() {
        return forma_Farmaceutica;
    }

    public void setForma_Farmaceutica(String forma_Farmaceutica) {
        this.forma_Farmaceutica = forma_Farmaceutica;
    }

}
