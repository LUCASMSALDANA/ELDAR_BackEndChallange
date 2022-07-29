package org.example.creaciontarjetas;

import org.example.objetos.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class CreacionListaTarjetas {
    public List<Tarjeta> crearListaTarjetas(){
        Tarjeta tarjeta1 = new Tarjeta("VISA", "4008558500573099","Juancito Perez","1992-05-30");
        Tarjeta tarjeta2 = new Tarjeta("NARA", "4008558500573099","Ricardo Barbosa","1986-08-12");
        Tarjeta tarjeta3 = new Tarjeta("VISA", "320857880557399","Miguel Hernandez","1995-04-24");

        List<Tarjeta> listado = new ArrayList<>();
        listado.add(tarjeta1);
        listado.add(tarjeta2);
        listado.add(tarjeta3);
        return listado;
    }

}
