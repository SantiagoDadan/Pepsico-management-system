package com.company;

import com.company.envios.Pedido;
import com.company.exceptions.*;
import com.company.personal.Admin;
import com.company.personal.Empleado;
import com.company.productos.Producto;

import java.util.ArrayList;
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

            System.out.println("PepsiCo, inc\n\n");
            System.out.println("1 - Ingresar como empleado\n2 - Ingresar como admin\n0 - Salir");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion){

                case 1:

                    try{
                        Empleado empleado = loginEmp(app);
                        menuEmpleado(empleado, app);

                    }catch (UsuarioIncorrecto | PasswordIncorrecto e){

                        System.out.println(e.getMessage());
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

        HashSet<Empleado> empleados = app.getEmpleados();
        Empleado aux = new Empleado();

        System.out.println("\nUsuario:");
        String usuario = scan.nextLine();

        System.out.println("\npass:");
        String pass = scan.nextLine();

        for (Empleado empleado : empleados) {

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

        HashSet<Admin> admins = app.getAdmins();
        Admin aux = new Admin();

        System.out.println("\nUsuario:");
        String usuario = scan.nextLine();

        System.out.println("\npass:");
        String pass = scan.nextLine();

        for (Admin admin : admins){

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

        System.out.println("Bienvenido " +empleado.getNombreApellido()+ "!\n\n");

        do {

            System.out.println("1 - Preparar pedido\n2 - Listar pedidos\n3 - Ver stock de productos\n4 - Informar falta de stock\n0 - Salir\n\n");
            opcion = scan.nextInt();

            switch (opcion){

                case 1:

                    String pedido = app.prepararPedido(empleado);

                    System.out.println(pedido);

                    String stock = app.listarStock().toString();

                    System.out.println(stock);

                    System.out.println("Pedidos empleado: " + empleado.getCantPedidos());

                    break;

                case 2:

                    ArrayList<Pedido> pedidos = app.listaPedidos();

                    for (int i = 0; i < pedidos.size(); i++){

                        System.out.println(pedidos.get(i).toString());
                    }
                    break;

                case 3:

                    //String stock = app.listarStock().toString();

                    //System.out.println(stock);

                    break;

                case 4:

                    ArrayList<Producto> productos = app.getProductos();
                    StringBuilder flag = new StringBuilder();

                    for (int i = 0; i < productos.size(); i++){

                        if (productos.get(i).getStockCajas() < 10){
                            productos.get(i).setStockCajas(50);
                            flag.append("{ Stock de " + productos.get(i).getNombre() + " renovado! | Actual: " + productos.get(i).getStockCajas() + " }\n");
                        }
                    }

                    if (flag.isEmpty()){

                        flag.append("\nNo hay stock que actualizar!\n\n");

                    }else{
                        app.setProductos(productos);
                    }

                    System.out.println(flag);


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

        System.out.println("Bienvenido " +admin.getNombreApellido()+ "!\n\n");

        do {

            System.out.println("1 - Listar\n2 - Modificar\n3 - Agregar\n4 - Eliminar\n0 - Salir");
            opcion = scan.nextInt();

            switch (opcion){

                case 1:

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

    public static void menuModificar(){

    }

    public static void menuAgregar(){

    }

    public static void menuEliminar(){

    }

    public static void menuListar(){

    }

}



