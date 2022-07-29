package org.example.objetos;

public class Tarjeta {
    private String marca;
    private String numero;
    private String cardHolder;
    private String fechanacimiento;

    //*****************
    //** Constructor **
    //*****************

    public Tarjeta() {
    }

    public Tarjeta(String marca, String numero, String cardHolder, String fechanacimiento) {
        this.marca = marca;
        this.numero = numero;
        this.cardHolder = cardHolder;
        this.fechanacimiento = fechanacimiento;
    }

    //***********************
    //** Getters & Setters **
    //***********************


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    @Override
    public String toString(){
        return "Marca : "+this.marca+"\nNumero : "+this.numero+"\nCardHolder : "+this.cardHolder+"\nFecha de Vencimiento : "+this.fechanacimiento+"\n***********************************l";

    }
}
