package com.company;

import com.company.assets.Fila;
import com.company.assets.FilesUtiles;
import com.company.assets.JsonUtiles;
import com.company.assets.Nodo;
import com.company.envios.Caja;
import com.company.envios.Camion;
import com.company.envios.Pedido;
import com.company.exceptions.*;
import com.company.personal.Admin;
import com.company.personal.Empleado;
import com.company.productos.Bebiba;
import com.company.productos.Cereal;
import com.company.productos.Producto;
import com.company.productos.Snack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import static com.company.Main.scan;

public class App{

    private Fila<Pedido> pedidos;
    private ArrayList<Producto> productos;
    private HashSet<Empleado> empleados;
    private HashSet<Admin> admins;
    private HashMap<String,Camion> camiones;
    private Fila<Camion> camionesFuera;

    public App() {                              // constructor que inicializa con distintos metodos las listas de pedidos, productos, empleados,
                                                // admins y camiones con informacion proveniente de archivos binarios o json previamente cargados.
        pedidos = new Fila<>();
        cargarPedidos();
        productos = new ArrayList<>();
        cargarProductos();
        empleados = new HashSet<>();
        admins = new HashSet<>();
        cargarEmpleadosYAdmins();
        camiones = new HashMap<>();
        cargarCamiones();
        camionesFuera = new Fila<>();
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

    public HashMap<String, Camion> getCamiones() {
        return camiones;
    }

    public String camionesDisponibles() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < camiones.size(); i++){

            Camion aux = camiones.get(String.valueOf(i+1));

            if (aux.isDisponible()){

                builder.append(aux.toString());
            }
        }

        return builder.toString();
    }

    public Fila<Camion> getCamionesFuera() {
        return camionesFuera;
    }

    public ArrayList<Pedido> listaPedidos(){

        ArrayList<Pedido> aux = pedidos.listar();

        return aux;
    }

    public StringBuilder listarStock(){

        StringBuilder stocks = new StringBuilder("\n\n");

        for (Producto e : productos){
            stocks.append("{ " + e.getNombre()+ " " + e.getStockCajas() + " cajas }\n");
        }

        return stocks;
    }

    public ArrayList<Camion> listarCamionesFuera(){

        return camionesFuera.listar();
    }

    public boolean camionDisponible(){     // comprueba de que haya algun camion en el array de camiones que este disponible para usarlo

        boolean aux = false;

        for (int i = 0; i < camiones.size(); i++){

            if (camiones.get(String.valueOf(i+1)).isDisponible()){
                aux = true;
                break;
            }
        }

        return aux;
    }

    public boolean comprobarStockPedido(Pedido pedido){         // metodo que verifica que los productos de un pedido existan y de los cuales haya stock

        boolean aux = false;

        int cntCajas = 0, flag = 0;

        ArrayList<Caja> cajas = pedido.getArrayListCajas();

        for (Caja c : cajas){                               // ser recorren las cajas del pedido y se verifica que que cada tipo de produto que hay en ellas
                                                           // exista en la lista de productos que tiene la empresa
            cntCajas++;

            for(int i = 0; i < productos.size(); i++){

                if (c.getTipoProducto().getIdProducto() == productos.get(i).getIdProducto()){

                    flag++;
                }
            }
        }

        if(cntCajas == flag){           // si existen en la empresa los productos que aparecen en el pedido (lo que se verifico anteriormente)
                                        //  se vuelven a recorrer las cajas y la lista con productos, pero esta vez para restar el stock
            aux = true;                 // en la lista de la empresa, en base a las cajas de productos que se piden en el pedido.

            for (Caja c : cajas){

                for(int i = 0; i < productos.size(); i++){

                    if (c.getTipoProducto().getIdProducto() == productos.get(i).getIdProducto()){

                        productos.get(i).aumentarStock(-1);
                    }
                }
            }
        }

        return aux;
    }

    public Camion asignarCamion() {
        Camion aux = null;                                  // metodo que recorre el hashMap de camiones y retorna el primero que encuentra disponible,
                                                            // y el valor del atributo "disponible" de este camion cambia a false

        for (int i = 0; i < camiones.size(); i++) {

            if (camiones.get(String.valueOf(i + 1)).isDisponible()) {

                aux = camiones.get(String.valueOf(i + 1));
                camiones.get(String.valueOf(i+1)).setDisponible(false);
                break;
            }
        }
        return aux;
    }

    public String prepararPedido(Empleado empleado) throws FaltaCamiones, FaltaPedidos, FaltaStock {
                                                        // metodo para preparar pedido, donde toma el primero pedido de la lista de pedidos,
        StringBuilder builder = new StringBuilder();    // luego se verifica que haya algun camion disponible para enviarlo y tambien que
                                                        // haya stock de los productos que estan en el pedido. Si alguna de las verificaciones falla,
        if (!pedidos.filaVacia()){                      // se lanzan excepciones customs, y si son correctas el camion se agrega a la Fila de
                                                        // camiones fuera (camiones en uso) y el metodo retorna un string con camion que se le asigno.
            if(camionDisponible()){

                Pedido pedido = pedidos.getPrimero();

                if (comprobarStockPedido(pedido)){

                    pedido = pedidos.avanzar();

                    empleado.pasarPedido();

                    Camion camionP = asignarCamion();

                    camionP.setPedido(pedido);

                    camionesFuera.insertar(camionP);

                    builder.append("\nCamion asignado:\n\n" + camionP.toString() + "\n\n");

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

    public Camion vueltaCamion(){

        if (!camionesFuera.filaVacia()){

            Camion aux = camionesFuera.avanzar();
                                                                                                // Si el metodo funciona corretamente, retorna el camion que ya no está en uso.                                           // metodo para setear un camion que ya se usó como disponible. Para hacerlo elimina el camion que esta
            for (int i = 0; i < camiones.size(); i++){                                          // en la Fila de camiones fuera (camiones en uso) y lo compara con los camiones que estan en el hashMap
                                                                                                // de camiones, y al encontrarlo en este hashMap le cambia el valor del atributo "disponible" por true.
                if (aux.getPatente().equals(camiones.get(String.valueOf(i+1)).getPatente())){
                    camiones.get(String.valueOf(i+1)).setDisponible(true);
                }
            }

            return aux;

        }else{

            return null;
        }
    }

    public StringBuilder listarProductos(){                     // crea una cadena (String) de productos en base al arraylist de productos

        StringBuilder cadenaProductos = new StringBuilder("\n");

        for (Producto e : productos){
            cadenaProductos.append(e.toString() + "\n");
        }

        return cadenaProductos;
    }

    public StringBuilder listarCamiones(){                  // crea una cadena (String) de camiones en base al hashMap de camiones

        StringBuilder cadenaCamiones = new StringBuilder("\n");

        Iterator<Map.Entry<String, Camion>> filas = camiones.entrySet().iterator();

        while (filas.hasNext())
        {
            Map.Entry<String, Camion> entradaMapa = filas.next();

            cadenaCamiones.append(entradaMapa.toString() + "\n");
        }
        return cadenaCamiones;
    }

    public StringBuilder listarEmpleados(){                  // crea una cadena (String) de empleados en base al hashSet de empleados

        StringBuilder cadenaEmpleados = new StringBuilder("\n");

        Iterator<Empleado> iterator = empleados.iterator();

        while (iterator.hasNext())
        {
            cadenaEmpleados.append(iterator.next() + "\n");
        }

        return cadenaEmpleados;
    }

    public void nuevoProducto(Producto producto){

        productos.add(producto);
    }

    public void nuevoPedido(Pedido pedido){

        pedidos.insertar(pedido);
    }

    public void nuevoCamion(Camion camion){

        camiones.put(String.valueOf(camiones.size()+1), camion);
    }

    public void nuevoEmpleado(Empleado empleado){

        empleados.add(empleado);
    }
}

