package com.company;

import java.util.ArrayList;

public class Pedido {

    // ATRIBUTOS

    private ArrayList<Caja> arrayListCajas;
    private String destinatario;
    private String direccion;

    // CONSTRUCTOR

    public Pedido(ArrayList<Caja> arrayListCajas, String destinatario, String direccion) {
        this.arrayListCajas = new ArrayList<Caja>();
        this.destinatario = destinatario;
        this.direccion = direccion;
    }

    // SETTERS Y GETTERS

    public ArrayList<Caja> getArrayListCajas() {
        return arrayListCajas;
    }

    public void setArrayListCajas(ArrayList<Caja> arrayListCajas) {
        this.arrayListCajas = arrayListCajas;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    // TOSTRING
}
