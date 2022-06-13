package com.company;

public class Empleado extends Persona{

    private int cantPedidos;
    private int antiguedad;
    private float comision;

    public Empleado(int dni, String nombreApellido, String usuario, int pass, boolean admin, int cantPedidos, int antiguedad, float comision) {
        super(dni, nombreApellido, usuario, pass, admin);
        this.cantPedidos = cantPedidos;
        this.antiguedad = antiguedad;
        this.comision = comision;
    }

    public int getCantPedidos() {
        return cantPedidos;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public float getComision() {
        return comision;
    }
}
