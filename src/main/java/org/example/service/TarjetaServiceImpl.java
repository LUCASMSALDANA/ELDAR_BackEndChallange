package org.example.service;

import org.example.pantalla.PantallaBusqueda;

import java.util.Scanner;

public class TarjetaServiceImpl {

    Scanner in = new Scanner(System.in);

    public void busqueda() {
        new PantallaBusqueda();
        boolean end=false;
        while(end==false){
            String opcion= in.nextLine().trim();

        }
    }

    public boolean verificarTarjeta(String tarjeta){
        // Voy a utilizar el algoritmo de Lhun para verificar si el numero de la tarjeta es correcto.
        boolean multiplicar = true;
        int num=0;    // creo una variable donde voy a contener el numero a trabajar
        int sumaDigitos= 0; // La variable suma contendra la suma de los digitos
        for(int i = 0; i<tarjeta.length();i++){ // El Algoritmo de lhun en primer lugar duplica los numeros en las posiciones pares (desde la perspectiva de java) incluyendo el 0
            // Para saber cuando multiplicar y cuando no esta mi variable booleana q cambiara de valor cada vez q i se incremente.
            num = Integer.parseInt(Character.toString(tarjeta.charAt(i))); // Luego de varias conversiones, guardo el numero de mi posicion deseada dentro de num, para uego seguir trabajandolo
            if(multiplicar){num= num*2;} // Lhun me dice que debo multiplicar x dos el numero
            //Luego Lhun, me dice que si el resultado de esa multiplicacion tiene dos digitos, debo sumar esos digitos.
            // Por lo cual voy a preguntar si num es mayor o igual a 10.
            // Si es mayor o igual voy a dividir a num por 10, y me quedo con el resto, es decir 15 dividio 10, me deja de resto 15, 18 dividido 10, me deja de
            //resto un 8, y asi. Una vez que tengo ese resultado, a num le sumo 1

            if(num>=10){            //Este if solo puede ingresar cuando el num es mayor a 10, es decir solo si se hizo la multiplicacion y el resultado tiene 2 digitos
                sumaDigitos+=num%10;
                num+=1;
            }
            sumaDigitos+=num;  // sumo los numeros
            multiplicar= !multiplicar; // cambio de valor mi variable booleana
        }

        return (sumaDigitos%10 ==0); // devuelve verdadero, solo si el el resto me da 0, cumpliendo con el algoritmo de lhun
    }

}
