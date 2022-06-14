package com.company.productos;

public class Cereal extends Producto {

    // ATRIBUTOS

    private int esDulce;

    // CONSTRUCTOR

    public Cereal(int idProducto, String nombre, String tipo, int stockCajas, int esDulce) {
        super(idProducto, nombre, tipo, stockCajas);
        this.esDulce = esDulce;
    }

    // SETTERS Y GETTERS

    public int getEsDulce() {
        return esDulce;
    }

    public void setEsDulce(int esDulce) {
        this.esDulce = esDulce;
    }

    // TOSTRING

    @Override
    public String toString() {
        return super.toString() + "Cereal{" +
                "esDulce=" + esDulce +
                '}';
    }
}
