package com.company.envios;

import java.io.Serializable;

public class Camion implements Serializable {

    private String patente;
    private String Marca;
    private int modelo;
    private boolean disponible;

    public Camion(String patente, String marca, int modelo, boolean disponible) {
        this.patente = patente;
        Marca = marca;
        this.modelo = modelo;
        this.disponible = disponible;
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return Marca;
    }

    public int getModelo() {
        return modelo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public String toString() {
        return "Camion{ " +
                "patente='" + patente + '\'' +
                ", Marca='" + Marca + '\'' +
                ", modelo=" + modelo +
                ", disponible=" + disponible +
                " }";
    }
}
