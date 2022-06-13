package com.company;

import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class Main {

    static Scanner scan;

    public static void main(String[] args) {

        scan = new Scanner(System.in);



        scan.close();
    }

    public static void menu(){

        int opcion, loginStatus;

        do {

            System.out.println("PepsiCo, inc\n\n");
            System.out.println("1 - Ingresar como empleado\n2 - Ingresar como admin\n0 - Salir");
            opcion = scan.nextInt();

            switch (opcion){

                case 1:

                    loginStatus = login();

                    if(loginStatus == 1){
                        menuEmpleado("asd");
                    }else{
                        System.out.println("No se econtro el Usuario y/o la contraseña!\n");
                    }
                    break;

                case 2:

                    loginStatus = login();

                    if(loginStatus == 1){
                        menuAdmin("asd");
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

    public static int login(){

        String fuente = JsonUtiles.leer();

        try {
            JSONObject obj = new JSONObject(fuente);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public static void menuEmpleado(String nombre){

        int opcion;

        System.out.println("Bienvenido " +nombre+ "!\n\n");

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

    public static void menuAdmin(String nombre){

        int opcion;

        System.out.println("Bienvenido " +nombre+ "!\n\n");

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

