package com.company;

public class Persona {

    private int dni;
    private String nombreApellido;
    private String usuario;
    private int pass;
    private boolean admin;

    public Persona(int dni, String nombreApellido, String usuario, int pass, boolean admin) {
        this.dni = dni;
        this.nombreApellido = nombreApellido;
        this.usuario = usuario;
        this.pass = pass;
        this.admin = admin;
    }

    public int getDni() {
        return dni;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getPass() {
        return pass;
    }

    public boolean isAdmin() {
        return admin;
    }
}
