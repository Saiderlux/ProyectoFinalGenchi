/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

/**
 *
 * @author Ssaid
 */
public class Venta {
     private int id;
    private String productosVendidos;
    private double totalVenta;
    private double cantidadPagada;
    private String fecha;
    private String hora;

    public Venta(int id, String productosVendidos, double totalVenta, double cantidadPagada, String fecha, String hora) {
        this.id = id;
        this.productosVendidos = productosVendidos;
        this.totalVenta = totalVenta;
        this.cantidadPagada = cantidadPagada;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(String productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public double getCantidadPagada() {
        return cantidadPagada;
    }

    public void setCantidadPagada(double cantidadPagada) {
        this.cantidadPagada = cantidadPagada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
