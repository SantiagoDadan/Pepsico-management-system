package com.company;

public class Empleado extends Persona{

    private int cantPedidos;
    private int antiguedad;
    private int comision;

    public Empleado() {
    }

    public Empleado(int dni, String nombreApellido, String usuario, int pass, boolean admin, int cantPedidos, int antiguedad, int comision) {
        super(dni, nombreApellido, usuario, pass);
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

    public int getComision() {
        return comision;
    }

    public void setCantPedidos(int cantPedidos) {
        this.cantPedidos = cantPedidos;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }
}
