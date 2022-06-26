package com.company;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static Scanner scan;

    public static void main(String[] args) {

        scan = new Scanner(System.in);

        App app = new App();

        menu(app);

        scan.close();
    }

    public static void menu(App app){

        int opcion;

        Empleado eAux;
        Admin aAux;

        do {

            System.out.println("\n\nPepsiCo, inc\n\n");
            System.out.println("1 - Ingresar como empleado\n2 - Ingresar como admin\n0 - Salir");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion){    // se ingresa como admin o empleado segun elija el usuario

                case 1:

                    try{                                       //se logea como empleado y se accede a su menu.
                        Empleado empleado = loginEmp(app);
                        menuEmpleado(empleado, app);

                    }catch (UsuarioIncorrecto | PasswordIncorrecto e){   // captura de excepciones customs al logearse como empleado
                        System.out.println(e.getMessage());              // y al usar el menu de empleado.
                    } catch (FaltaPedidos e) {
                        e.printStackTrace();
                    } catch (FaltaStock e) {
                        e.printStackTrace();
                    } catch (FaltaCamiones e) {
                        e.printStackTrace();
                    }

                    break;


                case 2:

                    try{

                        Admin admin = loginAdmin(app);
                        menuAdmin(admin, app);

                    }catch (UsuarioIncorrecto | PasswordIncorrecto e){

                        System.out.println(e.getMessage());
                    }

                    break;

                case 0:

                    System.out.println("Hasta la proxima!");
                    break;

                default:

                    System.out.println("Ingrese una opcion valida!\n");
                    break;
            }


        }while (opcion != 0);
    }

    public static Empleado loginEmp(App app) throws UsuarioIncorrecto, PasswordIncorrecto{

        int fUser = 0, fPass = 0;

        HashSet<Empleado> empleados = app.getEmpleados();    // se crea un hashset con los empleados cargados (que provienen de un archivo json)
        Empleado aux = new Empleado();

        System.out.println("\nUsuario:");
        String usuario = scan.nextLine();

        System.out.println("\npass:");
        String pass = scan.nextLine();

        for (Empleado empleado : empleados) {               // se comparan los datos del empleado ingresado con los existentes, si existe lo retorna,
                                                            // y si no existe se lanza excepcion de usuario o contraseña incorrecta.
            if(empleado.getUsuario().equals(usuario)){

                fUser = 1;

                if(empleado.getPass().equals(pass)){

                    fPass = 1;
                    aux = empleado;
                }
            }
        }

        if(fUser == 0){
            throw new UsuarioIncorrecto("Usuario invalido");

        }else if(fPass == 0){

            throw new PasswordIncorrecto("Password invalida");
        }

        return aux;
    }

    public static Admin loginAdmin(App app) throws UsuarioIncorrecto, PasswordIncorrecto{

        int fUser = 0, fPass = 0;

        HashSet<Admin> admins = app.getAdmins();    // se crea un hashset con los admins cargados (que provienen de un archivo json)
        Admin aux = new Admin();

        System.out.println("\nUsuario:");
        String usuario = scan.nextLine();

        System.out.println("\npass:");
        String pass = scan.nextLine();

        for (Admin admin : admins){                      // se comparan los datos del admin ingresado con los existentes, si existe lo retorna,
                                                         // y si no existe se lanza excepcion de usuario o contraseña incorrecta.
            if(admin.getUsuario().equals(usuario)){

                fUser = 1;

                if(admin.getPass().equals(pass)){

                    fPass = 1;
                    aux = admin;
                }
            }
        }

        if(fUser == 0){
            throw new UsuarioIncorrecto("Usuario invalido");

        }else if(fPass == 0){
            throw new PasswordIncorrecto("Password invalida");
        }

        return aux;
    }

    public static void menuEmpleado(Empleado empleado, App app) throws FaltaPedidos, FaltaStock, FaltaCamiones {

        int opcion;

        System.out.println("\n\nBienvenido empleado " +empleado.getNombreApellido() + "!");

        do {
            System.out.println("\n\nIngrese la opcion que quiera realizar como Empleado:");
            System.out.println("\n1 - Preparar pedido\n2 - Listar pedidos\n3 - Ver stock de productos\n4 - Solicitar aumento de stock\n5 - Ver camiones disponibles\n6 - Ver camiones en camino\n7 - Informar vuelta de camion\n0 - Salir\n\n");
            opcion = scan.nextInt();

            switch (opcion){

                case 1:             // el empleado prepara un pedido

                    String pedido = app.prepararPedido(empleado);

                    System.out.println(pedido + "En camino!\n\n");

                    break;

                case 2:            // el empleado ve los pedidos pendientes

                    ArrayList<Pedido> pedidos = app.listaPedidos();

                    for (int i = 0; i < pedidos.size(); i++){

                        System.out.println(pedidos.get(i).toString());
                    }
                    break;

                case 3:         // el empleado ve el stock que hay de cada producto

                    String stock = app.listarStock().toString();

                    System.out.println(stock);

                    break;

                case 4:// con esta opcion se agrega stock de los productos que hay menos de 10 cajas

                    ArrayList<Producto> productos = app.getProductos();
                    StringBuilder flag = new StringBuilder();

                    for (int i = 0; i < productos.size(); i++){

                        if (productos.get(i).getStockCajas() < 10)  // se verifican todos los productos, los que tienen < 10 cajas de stock, se les agregan 50
                        {
                            productos.get(i).setStockCajas(50);
                            flag.append("{ Stock de " + productos.get(i).getNombre() + " renovado! | Actual: " + productos.get(i).getStockCajas() + " }\n");
                            // se muestra el nuevo stock del producto que tenia pocas cajas
                        }
                    }

                    if (flag.isEmpty()){

                        flag.append("\nNo hay stock que actualizar!\n\n");

                    }else{
                        app.setProductos(productos);      // se modifica el stock en el objeto app que controla la lista de productos
                    }

                    System.out.println(flag);     // se muestra la cadena que guarda cuando se modifica o no el stock

                    break;

                case 5:

                    String disponibles = app.camionesDisponibles();

                    if (!disponibles.isEmpty()){

                        System.out.println("Camiones disponibles: \n" + disponibles);

                    }else{
                        System.out.println("No contamos con camiones disponibles");
                    }

                    break;

                case 6:

                    if(!app.getCamionesFuera().filaVacia()){

                        for (Camion e : app.listarCamionesFuera()){

                            System.out.println(e.toString());
                        }

                    }else{

                        System.out.println("No hay camiones realizando entregas!");
                    }

                    break;

                case 7:

                    Camion aux = app.vueltaCamion();

                    if (aux != null){

                        System.out.println("Volvio el " + aux);
                    }else {
                        System.out.println("No hay camiones realizando entregas!");
                    }

                    break;

                case 0:

                    break;

                default:

                    System.out.println("\nIngrese una opcion valida!\n\n");
                    break;
            }

        }while (opcion != 0);
    }

    public static void menuAdmin(Admin admin, App app){

        int opcion;

        System.out.println("\n\nBienvenido Administrador " + admin.getNombreApellido()+ "!");

        do {
            System.out.println("\n\nIngrese la opcion que quiera realizar como Administrador:");
            System.out.println("\n1 - Listar\n2 - Modificar\n3 - Agregar\n4 - Eliminar\n0 - Salir");
            opcion = scan.nextInt();

            switch (opcion){

                case 1:
                        menuListar(admin, app);  // metodo con el que un admin lista los datos de la empresa, segun lo que le permita su categoria.
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Ingrese una opcion valida!");
                    break;
            }

        }while (opcion != 0);


    }

    public static void menuListar(Admin admin, App app){
        int opcionListar;

        do {

            if (admin.getCategoria().equals("A"))
            {
                System.out.println("\nAl ser un Adminstador de categoria \"" + admin.getCategoria() + "\" puede Listar lo siguiente:");
                System.out.println("\n1 - Productos\n2 - Pedidos\n3 - Camiones\n4 - Empleados\n0 - Salir de Opcion Listar");
            }
            else if (admin.getCategoria().equals("B"))
            {
                System.out.println("\nAl ser un Adminstador de categoria \"" + admin.getCategoria() + "\" puede Listar lo siguiente:");
                System.out.println("\n1 - Productos\n2 - Pedidos\n3 - Camiones\n0 - Salir de Opcion Listar");
            }
            else
            {
                System.out.println("\nAl ser un Adminstador de categoria \"" + admin.getCategoria() + "\" puede Listar lo siguiente:");
                System.out.println("2 - Pedidos\n3 - Camiones\n0 - Salir de Opcion Listar");
            }

            System.out.println("\n¿Que quiere listar?:");
            opcionListar = scan.nextInt();

            switch (opcionListar){

                case 1:
                    if (admin.getCategoria().equals("A") || admin.getCategoria().equals("B"))
                    {


                    }
                    else
                        System.out.println("\nTu categoria no es suficiente para listar Productos!\n");

                    break;

                case 2:


                    break;

                case 3:

                    break;

                case 4:
                    if (admin.getCategoria().equals("A"))
                    {


                    }
                    else
                        System.out.println("\nTu categoria no es suficiente para listar Empleados!\n");

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Ingrese una opcion valida para Listar!");
                    break;
            }

        }while (opcionListar != 0);

    }

    public static void menuModificar(){

    }

    public static void menuAgregar(){

    }

    public static void menuEliminar(){

    }

}



