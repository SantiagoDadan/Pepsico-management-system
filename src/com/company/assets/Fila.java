package com.company.assets;

import java.util.ArrayList;

public class Fila<T> {

    private Nodo<T> primero, ultimo;

    public Fila() {
        primero = null;
        ultimo = null;
    }

    public boolean filaVacia(){

        if(primero == null){

            return true;
        }else{
            return false;
        }
    }

    public void insertar(T info){

        Nodo<T> nn = new Nodo<>();

        nn.info = info;
        nn.siguiente = null;

        if (filaVacia()){

            primero = nn;
            ultimo = nn;

        }else{

            ultimo.siguiente = nn;
            ultimo = nn;
        }
    }

    public T avanzar(){

        if (!filaVacia()){

            T info = primero.info;

            if (primero == ultimo){

                primero = null;
                ultimo = null;

            }else{

                primero = primero.siguiente;
            }

            return info;

        }else{

            return null;
        }
    }

    public ArrayList<T> listar(){

        Nodo<T> path = primero;
        ArrayList<T> array = new ArrayList<>();

        while (path != null){

            array.add(path.info);
            path = path.siguiente;

        }

        return array;
    }

    public void eliminar(T aBorrar){

        Nodo<T> actual = new Nodo<>();
        Nodo<T> anterior = new Nodo<>();

        actual = primero;
        anterior = null;

        while (actual != null){

            if(actual.info.equals(aBorrar)){

                if (actual == primero){

                    primero = primero.siguiente;

                }else{

                    anterior.siguiente = actual.siguiente;
                }
            }

            anterior = actual;
            actual = actual.siguiente;
        }
    }
}
