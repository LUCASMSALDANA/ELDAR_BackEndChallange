package org.example.creaciontarjetas;

import org.example.objetos.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class CreacionListaTarjetas {
    public List<Tarjeta> crearListaTarjetas(){
        Tarjeta tarjeta1 = new Tarjeta("VISA", "4408558500573099","Juancito Perez","05-2023");
        Tarjeta tarjeta2 = new Tarjeta("NARA", "4038538500573099","Ricardo Barbosa","08-2026");
        Tarjeta tarjeta3 = new Tarjeta("AMEX", "325863340557399","Miguel Hernandez","04-2022");

        List<Tarjeta> listado = new ArrayList<>();
        listado.add(tarjeta1);
        listado.add(tarjeta2);
        listado.add(tarjeta3);
        return listado;
    }

}
