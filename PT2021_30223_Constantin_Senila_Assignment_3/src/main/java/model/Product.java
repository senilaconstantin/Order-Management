package model;

/**
 * Aceasta clasa reprezinta de asemenea tabela de produse din paza de date avand aceleasi atribute si de aceleasi tip.
 * Sunt trei constructori dintre care unul gol care face posibila folosirea reflexilor si metode de get si set de asemenea.
 */
public class Product {
    private int idProduct;
    private String numeProduct;
    private int cantitate;
    private double pret;
    public Product(){

    }
    public Product(int idProduct, String nume, int cantitate, double pret) {
        this.idProduct = idProduct;
        this.numeProduct = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }
    public Product( String nume, int cantitate, double pret) {
        this.numeProduct = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNumeProduct() {
        return numeProduct;
    }

    public void setNumeProduct(String numeProduct) {
        this.numeProduct = numeProduct;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nume=" + numeProduct +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }
}
