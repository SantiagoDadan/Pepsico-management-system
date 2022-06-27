package com.company.interfaces;


public interface I_AccionesConDatosGenericos <T>{

        void listarPedidos(T dato);
        boolean agregarPedido(T dato);
        boolean eliminarPedido(T dato);
        boolean modificarPedido(T dato);

        void listarProductos(T dato);
        boolean agregarProducto(T dato);
        boolean eliminarProducto(T dato);
        boolean modificarProducto(T dato);

        void listarEmpleados(T dato);
        boolean agregarEmpleado(T dato);
        boolean eliminarEmpleado(T dato);
        boolean modificarEmpleado(T dato);

        void listarCamiones(T dato);
        boolean agregarCamione(T dato);
        boolean eliminarCamione(T dato);
        boolean modificarCamione(T dato);

}
