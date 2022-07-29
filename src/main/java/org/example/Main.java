package org.example;

import org.example.creaciontarjetas.CreacionListaTarjetas;
import org.example.objetos.Tarjeta;
import org.example.pantalla.Pantalla;
import org.example.service.TarjetaServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean end = false; // Sera mi salida del while

        Scanner in = new Scanner(System.in); // Creo mi variable Scanner para que la persona pueda ingresar una tarjeta
        CreacionListaTarjetas creacionListaTarjetas = new CreacionListaTarjetas(); // Esta clase la uso como si fuera una "BBDD", es para poder traer una tarjeta y mostrarla por pantalla
        List<Tarjeta> listadoTarjetas =  creacionListaTarjetas.crearListaTarjetas(); // llamo al metodo crearListaTarjetas de mi clase creacion lista tarjetas, y guardo esa lista en una variable de tipo List.

        TarjetaServiceImpl tarjetaService = new TarjetaServiceImpl(); // Instancio mi clase TarjetaServiceImpl. para poder utilizar sus metodos


        //** Dibujo la Pantalla **
      /*  System.out.println("*************************************");
        System.out.println("Bienvenido al sistema de ELDAR S.R.L.");
        System.out.println("*************************************");
        System.out.println("");
        System.out.println("Ingrese una opcion a realizar");
        System.out.println("L: Listado de Tarjetas en BBDD");
        System.out.println("B: Buscar Información de una tarjeta");
        System.out.println("C: ¿Puedo realizar la Compra?");
        System.out.println("O: ¿Mi tarjeta sigue vigente para Operar?");
        System.out.println("T: ¿Cual es la tasa que me corresponde para una operacion?");
        System.out.println("V: Verificar si mi tarjeta es duplicada");

       */

        while(end==false){
            new Pantalla();
            String opcion= in.nextLine().trim();
            switch (opcion.toUpperCase()){
                case "L":  //Listado de Tarjetas
                    System.out.println("***********************************");
                    System.out.println("*** Listado de Tarjetas en BBDD ***");
                    System.out.println("***********************************");
                    listadoTarjetas.forEach(tarj-> System.out.println(tarj));
                    System.out.print("Presione Enter para continuar");
                    in.nextLine();
                    break;
                case "B":  // Busca la info de una tarjeta en especifico
                       String numero = tarjetaService.busqueda();
                       if (numero!=null){tarjetaService.buscarNroTarjeta(numero, listadoTarjetas);};
                    break;
                case "C":  // Consulta si puede realizar su compra
                    tarjetaService.consultaCompra();
                    break;
                case "O":  // Puede operar? Chequea la fecha de vencimiento
                    tarjetaService.puedoOperarTarjetaVigente();
                    break;
                case "T":  // Cual es la tasa segun Tarjeta?
                    break;
                case "V":  // Verifica q la tarjeta sea o no duplicada
                    break;
                case "COMPRAR": // Realiza una compra
                    break;
                case "X": // Sale del programa
                    end=true;
                    break;
            }



        }

    }
}