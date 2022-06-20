package com.company;

import com.company.assets.Fila;
import com.company.assets.FilesUtiles;
import com.company.envios.Pedido;
import com.company.productos.Bebiba;
import com.company.productos.Cereal;
import com.company.productos.Producto;
import com.company.productos.Snack;

import java.util.ArrayList;

public class App {

    private Fila<Pedido> pedidos;

    public App() {
        pedidos = new Fila<Pedido>();
        cargarPedidos();
    }

    public Fila<Pedido> getPedidos() {
        return pedidos;
    }

    public void cargarPedidos(){

        ArrayList<Pedido> pedidosArchi = FilesUtiles.leer("pedidos.bin");

        for (int i = 0; i < pedidosArchi.size(); i++){

            pedidos.insertar(pedidosArchi.get(i));

        }

    }

    public ArrayList<Pedido> listaPedidos(){

        ArrayList<Pedido> aux = pedidos.listar();

        return aux;
    }

    public StringBuilder listarStock(){

        ArrayList<Producto> aux = FilesUtiles.leerProductos("productos.bin");

        StringBuilder stocks = new StringBuilder();

        for (Producto e : aux){
            stocks.append("{ " + e.getNombre()+ " " + e.getStockCajas() + " cajas }\n");
        }

        return stocks;
    }


}
