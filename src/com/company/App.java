package com.company;

import com.company.assets.Fila;
import com.company.assets.FilesUtiles;
import com.company.assets.JsonUtiles;
import com.company.envios.Pedido;
import com.company.exceptions.PasswordIncorrecto;
import com.company.exceptions.UsuarioIncorrecto;
import com.company.personal.Admin;
import com.company.personal.Empleado;
import com.company.productos.Bebiba;
import com.company.productos.Cereal;
import com.company.productos.Producto;
import com.company.productos.Snack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class App {

    private Fila<Pedido> pedidos;
    private ArrayList<Producto> productos;
    private HashSet<Empleado> empleados;
    private HashSet<Admin> admins;

    public App() {
        pedidos = new Fila<>();
        cargarPedidos();
        productos = new ArrayList<>();
        cargarProductos();
        empleados = new HashSet<>();
        admins = new HashSet<>();
        cargarEmpleadosYAdmins();
    }

    private void cargarPedidos(){

        ArrayList<Pedido> pedidosArchi = FilesUtiles.leer("pedidos.bin");

        for (int i = 0; i < pedidosArchi.size(); i++){

            pedidos.insertar(pedidosArchi.get(i));

        }

    }

    private void cargarProductos(){

        productos = FilesUtiles.leerProductos("productos.bin");
    }

    private void cargarEmpleadosYAdmins(){

        String fuente = JsonUtiles.leer("usersData");

        try {
            JSONObject obj = new JSONObject(fuente);
            JSONArray array = obj.getJSONArray("empleados");

            for (int i = 0; i < array.length(); i++){

                JSONObject temp = array.getJSONObject(i);
                Empleado auxE = new Empleado();

                auxE.setDni(temp.getInt("dni"));
                auxE.setNombreApellido(temp.getString("nombre"));
                auxE.setUsuario(temp.getString("user"));
                auxE.setPass(temp.getString("pass"));
                auxE.setCantPedidos(temp.getInt("pedidos"));
                auxE.setAntiguedad(temp.getInt("antiguedad"));
                auxE.setComision(temp.getInt("comision"));

                empleados.add(auxE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(fuente);
            JSONArray array = obj.getJSONArray("admins");

            for (int i = 0; i < array.length(); i++){

                JSONObject temp = array.getJSONObject(i);
                Admin auxA = new Admin();

                auxA.setDni(temp.getInt("dni"));
                auxA.setNombreApellido(temp.getString("nombre"));
                auxA.setUsuario(temp.getString("user"));
                auxA.setPass(temp.getString("pass"));
                auxA.setCategoria(temp.getString("categoria"));

                admins.add(auxA);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Fila<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public HashSet<Empleado> getEmpleados() {
        return empleados;
    }

    public HashSet<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Pedido> listaPedidos(){

        ArrayList<Pedido> aux = pedidos.listar();

        return aux;
    }

    public StringBuilder listarStock(){

        StringBuilder stocks = new StringBuilder();

        for (Producto e : productos){
            stocks.append("{ " + e.getNombre()+ " " + e.getStockCajas() + " cajas }\n");
        }

        return stocks;
    }
}
