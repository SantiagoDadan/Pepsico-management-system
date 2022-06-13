package com.company;

public class Caja {

    // ATRIBUTOS

    private Producto tipoProducto;
    private static int cantProductosPorCaja = 10;
    private String tamanioProductos;     // chico, mediano o grande

    // CONSTRUCTOR

    public Caja(Producto tipoProducto, String tamanioProductos) {
        this.tipoProducto = tipoProducto;
        this.tamanioProductos = tamanioProductos;
    }

    // SETTERS Y GETTERS

    public Producto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(Producto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public static int getCantProductosPorCaja() {
        return cantProductosPorCaja;
    }

    public static void setCantProductosPorCaja(int cantProductosPorCaja) {
        Caja.cantProductosPorCaja = cantProductosPorCaja;
    }

    public String getTamanioProductos() {
        return tamanioProductos;
    }

    public void setTamanioProductos(String tamanioProductos) {
        this.tamanioProductos = tamanioProductos;
    }


    // TOSTRING


    @Override
    public String toString() {
        return "Caja{" +
                "tipoProducto=" + tipoProducto +
                ", tamanioProductos='" + tamanioProductos +
                ", cantProductosPorCaja='" + '\'' +
                '}';
    }
}
