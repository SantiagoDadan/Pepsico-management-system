package com.company;

import com.company.assets.Fila;
import com.company.assets.FilesUtiles;
import com.company.assets.JsonUtiles;
import com.company.envios.Caja;
import com.company.envios.Camion;
import com.company.envios.Pedido;
import com.company.exceptions.*;
import com.company.personal.Admin;
import com.company.personal.Empleado;
import com.company.productos.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class App {

    private Fila<Pedido> pedidos;
    private ArrayList<Producto> productos;
    private HashSet<Empleado> empleados;
    private HashSet<Admin> admins;
    private HashMap<String,Camion> camiones;

    public App() {
        pedidos = new Fila<>();
        cargarPedidos();
        productos = new ArrayList<>();
        cargarProductos();
        empleados = new HashSet<>();
        admins = new HashSet<>();
        cargarEmpleadosYAdmins();
        camiones = new HashMap<>();
        cargarCamiones();
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

    private void cargarCamiones(){

        camiones = FilesUtiles.leerCamiones("camiones.bin");
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

    public HashMap getCamiones() {
        return camiones;
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

    public boolean camionDisponible(){

        boolean aux = false;

        for (int i = 0; i < camiones.size(); i++){

            if (camiones.get(String.valueOf(i+1)).isDisponible()){
                aux = true;
                break;
            }
        }

        return aux;
    }

    public boolean comprobarStockPedido(Pedido pedido){

        boolean aux = false;

        int cntCajas = 0, flag = 0;

        ArrayList<Caja> cajas = pedido.getArrayListCajas();

        for (Caja c : cajas){

            cntCajas++;

            for(int i = 0; i < productos.size(); i++){

                if (c.getTipoProducto().getIdProducto() == productos.get(i).getIdProducto()){

                    flag++;
                }
            }
        }

        if(cntCajas == flag){

            aux = true;

            for (Caja c : cajas){

                for(int i = 0; i < productos.size(); i++){

                    if (c.getTipoProducto().getIdProducto() == productos.get(i).getIdProducto()){

                        productos.get(i).setStockCajas(-1);
                    }
                }
            }
        }

        return aux;
    }

    public String prepararPedido(Empleado empleado) throws FaltaCamiones, FaltaPedidos, FaltaStock {

        StringBuilder builder = new StringBuilder();

        if (!pedidos.filaVacia()){

            if(camionDisponible()){

                Pedido pedido = pedidos.getPrimero();

                if (comprobarStockPedido(pedido)){

                    pedido = pedidos.avanzar();

                    empleado.pasarPedido();

                    builder.append("\nDatos:\n" + pedido.toString() + "\n\n");

                }else{

                    throw new FaltaStock("No contamos con stock suficiente para el pedido!");
                }

            }else{

                throw new FaltaCamiones("No contamos con camiones para realizar el envio!");
            }

        }else{

            throw new FaltaPedidos("No contamos con pedidos nuevos!");
        }

        String string = builder.toString();

        return string;
    }
}
