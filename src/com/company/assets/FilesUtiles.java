package com.company.assets;

import com.company.envios.Camion;
import com.company.envios.Pedido;
import com.company.productos.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FilesUtiles {   // clase contenedora de los metodos de grabar y leer archivos para distintos tipos de datos, con sus respectivas clases para File

    public static void grabar(String archivo, Camion obj) {       //funcion para grabar objetos de tipo Camion en un archivo

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(obj);

            objectOutputStream.close();

        }catch (FileNotFoundException e){

            e.printStackTrace();

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public static ArrayList<Pedido> leer(String archivo){      // lee en un arraylist objetos de tipo Pedido provenientes del archivo

        ArrayList<Pedido>arrayList = new ArrayList<>();

        try{
            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            int path = 1;

            while (path == 1){

                arrayList.add((Pedido) objectInputStream.readObject());
            }

            objectInputStream.close();

        }catch (EOFException e){

        }catch (FileNotFoundException e){

            e.printStackTrace();

        }catch (ClassNotFoundException e) {

            e.printStackTrace();

        }catch (IOException e){

            e.printStackTrace();
        }

        return arrayList;
    }

    public static ArrayList<Producto> leerProductos(String archivo){        // lee en un arraylist objetos de tipo Producto provenientes del archivo

        ArrayList<Producto>arrayList = new ArrayList<>();

        try{
            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            int path = 1;

            while (path == 1){

                arrayList.add((Producto) objectInputStream.readObject());
            }

            objectInputStream.close();

        }catch (EOFException e){

        }catch (FileNotFoundException e){

            e.printStackTrace();

        }catch (ClassNotFoundException e) {

            e.printStackTrace();

        }catch (IOException e){

            e.printStackTrace();
        }

        return arrayList;
    }

    public static HashMap<String,Camion> leerCamiones(String archivo){        // lee en un arraylist objetos de tipo Camion provenientes del archivo

        HashMap<String,Camion> camiones = new HashMap<>();

        try{
            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            int path = 1;
            int ordenCamion = 1;

            while (path == 1){

                camiones.put(String.valueOf(ordenCamion),(Camion) objectInputStream.readObject());
                ordenCamion++;
            }

            objectInputStream.close();

        }catch (EOFException e){

        }catch (FileNotFoundException e){

            e.printStackTrace();

        }catch (ClassNotFoundException e) {

            e.printStackTrace();

        }catch (IOException e){

            e.printStackTrace();
        }

        return camiones;
    }
}
