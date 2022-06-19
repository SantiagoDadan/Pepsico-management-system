package com.company.personal;

public class Admin extends Persona {

    String categoria;

    public Admin() {

    }

    public Admin(int dni, String nombreApellido, String usuario, String pass, boolean admin, String categoria) {
        super(dni, nombreApellido, usuario, pass);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
