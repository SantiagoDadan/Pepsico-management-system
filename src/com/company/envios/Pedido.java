package com.company.envios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Pedido implements Serializable {

    // ATRIBUTOS

    private ArrayList<Caja> arrayListCajas;
    private String destinatario;
    private String direccion;

    // CONSTRUCTORES


    public Pedido(ArrayList<Caja> arrayListCajas, String destinatario, String direccion) {
        this.arrayListCajas = arrayListCajas;
        this.destinatario = destinatario;
        this.direccion = direccion;
    }

    public Pedido() {
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


    @Override
    public String toString() {

        return "\nPedido para: " + destinatario+ " en: "+ direccion +"\n" +
                "Cajas =\n" +  arrayListCajas +
                 '\n';
    }

}
