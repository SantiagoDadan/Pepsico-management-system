package com.company;

public abstract class Persona {

    private int dni;
    private String nombreApellido;
    private String usuario;
    private int pass;
    private boolean admin;

    public Persona() {
    }

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

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
