package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class Main {

    static Scanner scan;

    public static void main(String[] args) {

        scan = new Scanner(System.in);

        menu();

        scan.close();
    }

    public static void menu(){

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

                    Empleado empleado = loginEmpleado();

                    if(empleado != null){
                        menuEmpleado(empleado);
                    }else{
                        System.out.println("No se econtro el Usuario y/o la contraseña!\n");
                    }
                    break;

                case 2:

                    Admin admin = loginAdmin();

                    if(admin != null){
                        menuAdmin(admin);
                    }else{
                        System.out.println("No se econtro el Usuario o no posee derechos de administrador!\n");
                    }
                    break;

                case 0:

                    break;

                default:

                    System.out.println("Ingrese una opcion valida!\n");
                    break;
            }


        }while (opcion != 0);
    }

    public static Empleado loginEmpleado(){

        int flag = 0;

        Empleado aux = new Empleado();

        System.out.println("\nUsuario:");
        String usuario = scan.nextLine();
        System.out.println("\npass:");
        int pass = scan.nextInt();

        String fuente = JsonUtiles.leer("usersData");

        try {
            JSONObject obj = new JSONObject(fuente);
            JSONArray array = obj.getJSONArray("empleados");

            for (int i = 0; i < array.length(); i++){

                JSONObject temp = array.getJSONObject(i);
                if(temp.getString("user").equals(usuario) && (int) temp.getInt("pass") == pass){

                    assert false;
                    aux.setDni(temp.getInt("dni"));
                    aux.setNombreApellido(temp.getString("nombre"));
                    aux.setUsuario(usuario);
                    aux.setPass(pass);
                    aux.setCantPedidos(temp.getInt("pedidos"));
                    aux.setAntiguedad(temp.getInt("antiguedad"));
                    aux.setComision(temp.getInt("comision"));

                    flag = 1;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(flag == 1){
            return aux;
        }else{
            return null;
        }
    }

    public static Admin loginAdmin(){

        int flag = 0;

        Admin aux = new Admin();

        System.out.println("\nUsuario:");
        String usuario = scan.nextLine();
        System.out.println("\npass:");
        int pass = scan.nextInt();

        String fuente = JsonUtiles.leer("usersData");

        try {
            JSONObject obj = new JSONObject(fuente);
            JSONArray array = obj.getJSONArray("admins");

            for (int i = 0; i < array.length(); i++){

                JSONObject temp = array.getJSONObject(i);

                if(temp.getString("user").equals(usuario) && temp.getInt("pass") == pass){

                    aux.setDni(temp.getInt("dni"));
                    aux.setNombreApellido(temp.getString("nombre"));
                    aux.setUsuario(usuario);
                    aux.setPass(pass);
                    aux.setCategoria(temp.getString("categoria"));

                    flag = 1;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(flag == 1){
            return aux;
        }else{
            return null;
        }
    }

    public static void menuEmpleado(Empleado empleado){

        int opcion;

        System.out.println("Bienvenido " +empleado.getNombreApellido()+ "!\n\n");

        do {

            System.out.println("1 - Preparar pedido\n2 - Listar pedidos\n3 - Ver stock de productos\n4 - Informar falta de stock");
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

                    break;
            }

        }while (opcion != 0);


    }

    public static void menuAdmin(Admin admin){

        int opcion;

        System.out.println("Bienvenido " +admin.getNombreApellido()+ "!\n\n");

        do {

            System.out.println("1 - Listar\n2 - Modificar\n3 - Agregar\n4 - Eliminar");
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

                    break;
            }

        }while (opcion != 0);


    }

}

