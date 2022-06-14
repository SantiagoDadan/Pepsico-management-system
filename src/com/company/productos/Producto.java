package com.company.productos;

public abstract class Producto {

    // ATRIBUTOS

    private int idProducto;
    private String nombre;
    private String tipo;
    // en bebida, el tipo es: agua, gaseosa, jugo, etc
    // en snack, el tipo es: papas, nachos, palitos, etc
    // en cereal, el tipo es: chocolate, avena, miel, etc

    private int stockCajas;


    // CONSTRUCTOR

    public Producto(int idProducto, String nombre, String tipo, int stockCajas) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.stockCajas = stockCajas;
    }

    // SETTERS Y GETTERS

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getStockCajas() {
        return stockCajas;
    }

    public void setStockCajas(int stockCajas) {
        this.stockCajas = stockCajas;
    }


    // TOSTRING

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", stockCajas=" + stockCajas +
                '}';
    }
}
