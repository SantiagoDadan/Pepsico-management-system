package com.company;

public class Admin extends Persona{

    String categoria;

    public Admin(int dni, String nombreApellido, String usuario, int pass, boolean admin, String categoria) {
        super(dni, nombreApellido, usuario, pass, admin);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
