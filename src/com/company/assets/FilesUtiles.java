package com.company.assets;

import com.company.envios.Pedido;

import java.io.*;
import java.util.ArrayList;

public class FilesUtiles {

    public static void grabar(String archivo, Pedido obj) {

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

    public static ArrayList<Pedido> leer(String archivo){

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

}
