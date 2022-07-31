package org.example.service;

import org.example.Main;
import org.example.objetos.Tarjeta;
import org.example.pantalla.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TarjetaServiceImpl {

    Scanner in = new Scanner(System.in);
    LocalDate hoy = LocalDate.now();

    public String busqueda() {
        boolean validarCantidadnum, tarjetaValida, soloNumeros;
        boolean end = false;
        while (end == false) {
            new PantallaBusqueda();
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                case "":
                    System.out.print("El campo no puede estar vacio. Presione una tecla para continuar.");
                    in.nextLine();
                    break;
                default:
                    soloNumeros = sonNumeros(opc);
                    if (soloNumeros) {
                        validarCantidadnum = cantidadNumerosOK(opc);
                        tarjetaValida = verificarTarjeta(opc);

                        if (validarCantidadnum || tarjetaValida) {
                            return opc;
                        } else {
                            System.out.println("El numero de tarjeta ingresado es incorrecto. Presione una tecla para continuar.");
                            in.nextLine();
                        }
                    } else {
                        System.out.println("Debe ingresar unicamente numeros. Presione una tecla para continuar.");
                        in.nextLine();
                    }
                    break;
            }
        }
        return null;
    }

    private boolean sonNumeros(String opc) {
        for (int i = 0; i < opc.length(); i++) {
            if (!Character.isDigit(opc.charAt(i) )) {
                return false;
            }
        }
        return true;
    }


    public boolean verificarTarjeta(String tarjeta) {
        // Voy a utilizar el algoritmo de Lhun para verificar si el numero de la tarjeta es correcto.
        boolean multiplicar = true;
        int num = 0;    // creo una variable donde voy a contener el numero a trabajar
        int sumaDigitos = 0; // La variable suma contendra la suma de los digitos
        for (int i = 0; i < tarjeta.length(); i++) { // El Algoritmo de lhun en primer lugar duplica los numeros en las posiciones pares (desde la perspectiva de java) incluyendo el 0
            // Para saber cuando multiplicar y cuando no esta mi variable booleana q cambiara de valor cada vez q i se incremente.
            num = Integer.parseInt(Character.toString(tarjeta.charAt(i))); // Luego de varias conversiones, guardo el numero de mi posicion deseada dentro de num, para uego seguir trabajandolo
            if (multiplicar) {
                num = num * 2;
            } // Lhun me dice que debo multiplicar x dos el numero
            //Luego Lhun, me dice que si el resultado de esa multiplicacion tiene dos digitos, debo sumar esos digitos.
            // Por lo cual voy a preguntar si num es mayor o igual a 10.
            // Si es mayor o igual voy a dividir a num por 10, y me quedo con el resto, es decir 15 dividio 10, me deja de resto 15, 18 dividido 10, me deja de
            //resto un 8, y asi. Una vez que tengo ese resultado, a num le sumo 1

            if (num >= 10) {            //Este if solo puede ingresar cuando el num es mayor a 10, es decir solo si se hizo la multiplicacion y el resultado tiene 2 digitos
                sumaDigitos += num % 10;
                sumaDigitos += 1;
            } else {
                sumaDigitos += num;  // sumo los numeros
            }
            multiplicar = !multiplicar; // cambio de valor mi variable booleana
        }

        return (sumaDigitos % 10 == 0); // devuelve verdadero, solo si el el resto me da 0, cumpliendo con el algoritmo de lhun
    }

    public boolean cantidadNumerosOK(String tarjeta) {
        if (tarjeta.length() < 13 || tarjeta.length() >= 16) {
            return false;
        }
        return true;
    }

    public void buscarNroTarjeta(String numero, List<Tarjeta> listadoTarjetas) {
        boolean encontrada = false;
        int pos = 0;
        for (int i = 0; i < listadoTarjetas.size(); i++) {
            if (listadoTarjetas.get(i).getNumero().equals(numero)) {
                pos = i;
                i = listadoTarjetas.size();
                encontrada = true;
            }
        }
        if (encontrada) {
            System.out.println("***********************************");
            System.out.println(listadoTarjetas.get(pos));
            System.out.println("Presione una tecla para continuar");
            in.nextLine();
        } else {

            System.out.println("No se encuentra esa tarjeta en nuestra base de datos. Presione una tecla para continuar");
            in.nextLine();
        }
    }

    public void consultaCompra() {
        boolean end = false, soloNumeros;
        while (end == false) {
            new PantallaConsultaCompra();
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                case "":
                    System.out.print("El campo no puede estar vacio. Presione una tecla para continuar.");
                    in.nextLine();
                    break;
                default:
                    soloNumeros = sonNumeros(opc);
                    if (soloNumeros) {
                        if (Integer.parseInt(opc) < 1000) {
                            System.out.println("Usted PUEDE realizar la compra. Presione una tecla para continuar.");
                            in.nextLine();
                        } else {
                            System.out.println("Usted NO puede realizar la compra. Presione una tecla para continuar.");
                            in.nextLine();
                        }
                        end = true;
                    } else {
                        System.out.println("Debe ingresar unicamente numeros. Presione una tecla para continuar.");
                        in.nextLine();
                    }
                    break;
            }
        }
    }


    public void puedoOperarTarjetaVigente() {
        boolean end = false, formatoValido;
        while (end == false) {
            new PantallaVigente();
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                case "":
                    System.out.print("El campo no puede estar vacio. Presione una tecla para continuar.");
                    in.nextLine();
                    break;
                default:
                    formatoValido = validarFormato(opc);
                    if (formatoValido) {
                        if (Integer.parseInt(opc.substring(0, 2)) >= 1 && Integer.parseInt(opc.substring(0, 2)) <= 12) {
                            if (tarjetaVigente(opc)) {
                                System.out.println("Su tarjeta se encuentra VIGENTE para operar. Presione una tecla para continuar.");
                                in.nextLine();
                            } else {
                                System.out.println("Su tarjeta NO PUEDE operar. Esta Vencida. Presione una tecla para continuar.");
                                in.nextLine();
                            }
                            end = true;
                        } else {
                            System.out.println("El mes debe estar comprendido entre 1 y 12. Presione una tecla para continuar");
                            in.nextLine();
                        }
                    } else {
                        System.out.println("Formato invalido. Formato Requerido: AAAA-MM. Presione una tecla para continuar");
                        in.nextLine();
                    }
                    break;
            }
        }

    }

    public void calcularTasa() {
        boolean end = false;
        String tarjeta;
        while (end == false) {
            new PantallaTasa();
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                case "AMEX":
                case "NARA":
                case "VISA":
                    tarjeta = opc;
                    calcularElImporte(tarjeta);
                    end = true;
                    break;
                default:
                    System.out.println("La tarjeta ingresada no se encuentra en nuestro Sistema. Presione una tecla para continuar");
                    in.nextLine();
                    break;
            }
        }
    }

    private void calcularElImporte(String tarjeta) {
        boolean end = false;
        while (end == false) {
            boolean error = false;
            System.out.print("Ingrese el importe a realizar con su tarjeta : ");
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                default:
                    if (validarFormatoImporte(opc)) {
                        float importe = Float.parseFloat(prepararNumeroFloat(opc));
                        if(importe>1000) {
                            mostrarMensajedeTasa(importe, tarjeta);
                        }else{
                            System.out.println("No puede operar, su operacion supera los 1000.");
                            in.nextLine();
                        }
                        end = true;
                    } else {
                        System.out.println("Ingrese solo numeros. Presiones una tecla para continuar.");
                        in.nextLine();
                    }
            }

        }
        return;
    }

    private void mostrarMensajedeTasa(float importe, String tarjeta) {
        double tasa = 0;
        switch (tarjeta.toUpperCase()) {
            case "VISA":
                tasa = calculoAnio() / LocalDate.now().getMonthValue();
                if (tasa < 0.3) {
                    tasa = 0.3;
                }
                if (tasa > 5) {
                    tasa = 5;
                }
                break;
            case "NARA":
                tasa = LocalDate.now().getDayOfMonth() * 0.5;
                if (tasa < 0.3) {
                    tasa = 0.3;
                }
                if (tasa > 5) {
                    tasa = 5;
                }
                break;
            case "AMEX":
                tasa = LocalDate.now().getMonthValue() * 0.1;
                if (tasa < 0.3) {
                    tasa = 0.3;
                }
                if (tasa > 5) {
                    tasa = 5;
                }
                break;
        }
        double importetotal = importe + (importe * tasa / 100);
        String texto = String.format("La tasa para su tarjeta " + tarjeta + " es de %.2f. El importe total a pagar es de %.2f ", tasa, importetotal);
        System.out.println(texto);
        System.out.print("Presione una tecla para continuar.");
        in.nextLine();
        return;

    }

    private String prepararNumeroFloat(String opc) {
        int count = 0;
        String numeroLimpio = "";
        for (int i = 0; i < opc.length(); i++) {
            if (!Character.isDigit(opc.charAt(i))) {
                if (count < 1) {
                    count += 1;
                    numeroLimpio += opc.substring(i, i + 1);
                }

            } else {
                numeroLimpio += opc.substring(i, i + 1);
            }
        }
        return numeroLimpio;
    }

    public double calculoAnio() {
        double anio = LocalDate.now().getYear();
        int sacodigitos = LocalDate.now().getYear() / 100;
        anio = Math.round(((anio / 100) - sacodigitos) * 100);
        return anio;
    }

    private boolean validarFormatoImporte(String opc) {
        for (int i = 0; i < opc.length(); i++) {
            if (Character.isLetter(opc.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean tarjetaVigente(String opc) {
        int anioTarjeta = Integer.parseInt(opc.substring(3, 7));
        int mesTarjeta = Integer.parseInt(opc.substring(0, 2));
        if (anioTarjeta >= hoy.getYear()) {
            if (mesTarjeta > hoy.getMonthValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean validarFormato(String opc) {
        if (opc.length() != 7) {
            return false;
        }
        for (int i = 0; i < opc.length(); i++) {
            if (i < 2) {
                if (Character.isDigit(opc.charAt(i)) == false) {
                    return false;
                }
            } else if (i == 2) {
                if (Character.isDigit(opc.charAt(i)) || Character.isLetter(opc.charAt(i))) {
                    return false;
                }
            } else if (i < 2) {
                if (Character.isDigit(opc.charAt(i)) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public void tarjetaDuplicada(List<Tarjeta> listadoTarjetas) {
        boolean validarCantidadnum, tarjetaValida, soloNumeros;
        boolean end = false, tarjetaEncontrada = false;
        while (end == false) {
            new PantallaTarjetaDuplicada();
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                case "":
                    System.out.print("El campo no puede estar vacio. Presione una tecla para continuar.");
                    in.nextLine();
                    break;
                default:
                    soloNumeros = sonNumeros(opc);
                    if (soloNumeros) {
                        validarCantidadnum = cantidadNumerosOK(opc);
                        tarjetaValida = verificarTarjeta(opc);

                        if (validarCantidadnum || tarjetaValida) {
                            for (int i = 0; i < listadoTarjetas.size(); i++) {
                                if (opc.equals(listadoTarjetas.get(i).getNumero())) {
                                    tarjetaEncontrada = true;
                                    i = listadoTarjetas.size();
                                } else {
                                    tarjetaEncontrada = false;
                                }
                            }
                            end = true;
                            if (tarjetaEncontrada) {
                                System.out.println("La tarjeta ya existe en la base de datos. Presione una tecla para continuar.");
                            } else {
                                System.out.println("La tarjeta no se encuentra en nuestra base de datos. Presione una tecla para continuar.");
                            }
                            in.nextLine();
                        } else {
                            System.out.println("El numero de tarjeta ingresado es incorrecto. Presione una tecla para continuar.");
                            in.nextLine();
                        }
                    } else {
                        System.out.println("Debe ingresar unicamente numeros. Presione una tecla para continuar.");
                        in.nextLine();
                    }
                    break;
            }
        }
    }

    public void realizarUnaCompra() {
        boolean validarCantidadnum, tarjetaValida, soloNumeros;
        boolean end = false;
        while (end == false) {
            new PantallaCompra();
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                case "":
                    System.out.print("El campo no puede estar vacio. Presione una tecla para continuar.");
                    in.nextLine();
                    break;
                default:
                    soloNumeros = sonNumeros(opc);
                    if (soloNumeros) {
                        validarCantidadnum = cantidadNumerosOK(opc);
                        tarjetaValida = verificarTarjeta(opc);

                        if (validarCantidadnum || tarjetaValida) {
                            String fechaVencimiento = tarjetaVigenteCompra();
                        if(fechaVencimiento==null){return;}
                        tasaCompra();
                        end=true;
                        } else {
                            System.out.println("El numero de tarjeta ingresado es incorrecto. Presione una tecla para continuar.");
                            in.nextLine();
                        }
                    } else {
                        System.out.println("Debe ingresar unicamente numeros. Presione una tecla para continuar.");
                        in.nextLine();
                    }
                    break;
            }
        }
        return;

    }

    public String tarjetaVigenteCompra() {
        boolean end = false, formatoValido;
        while (end == false) {
            System.out.print("Ingrese su fecha de vencimiento.(Formato MM/AAAA) (X : Exit) : ");
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    return null;
                case "":
                    System.out.print("El campo no puede estar vacio. Presione una tecla para continuar.");
                    in.nextLine();
                    break;
                default:
                    formatoValido = validarFormato(opc);
                    if (formatoValido) {
                        if (Integer.parseInt(opc.substring(0, 2)) >= 1 && Integer.parseInt(opc.substring(0, 2)) <= 12) {
                            if (tarjetaVigente(opc)) {
                                return opc;
                            } else {
                                System.out.println("Su tarjeta NO PUEDE operar. Esta Vencida. Presione una tecla para continuar.");
                                in.nextLine();
                            }
                            end = true;
                        } else {
                            System.out.println("El mes debe estar comprendido entre 1 y 12. Presione una tecla para continuar");
                            in.nextLine();
                        }
                    } else {
                        System.out.println("Formato invalido. Formato Requerido: AAAA-MM. Presione una tecla para continuar");
                        in.nextLine();
                    }
                    break;
            }
        }
        return null;
    }
    public void tasaCompra() {
        boolean end = false;
        String tarjeta;
        while (end == false) {
            System.out.print("Ingrese la Marca de su Tarjeta (X : Exit) : ");
            String opc = in.nextLine().trim();
            switch (opc.toUpperCase()) {
                case "X":
                    end = true;
                    break;
                case "AMEX":
                case "NARA":
                case "VISA":
                    tarjeta = opc;
                    calcularElImporte(tarjeta);
                    end = true;
                    break;
                default:
                    System.out.println("La tarjeta ingresada no se encuentra en nuestro Sistema. Presione una tecla para continuar");
                    in.nextLine();
                    break;
            }
        }
    }

}