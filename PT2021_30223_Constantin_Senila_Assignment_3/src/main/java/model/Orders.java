package model;

/**
 * Aceasta clasa reprezinta tabela din baza de date Orders, avand aceleasi atribute ca si acolo. Aceasta clasa contine 3 constructori
 * dintre care unul gol pentru a putea fi folosita reflexia.
 */
public class Orders {
    private int idOrders;
    private String numeClient;
    private String numeOrders;
    private int total;
    private double pret;

    public Orders() {

    }

    public Orders(int idOrder, String numeC, String numeP, int total) {
        this.idOrders = idOrder;
        this.numeClient = numeC;
        this.numeOrders = numeP;
        this.total = total;
    }

    public Orders(String numeC, String numeP, int total) {
        this.numeClient = numeC;
        this.numeOrders = numeP;
        this.total = total;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(int idOrders) {
        this.idOrders = idOrders;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getNumeOrders() {
        return numeOrders;
    }

    public void setNumeOrders(String numeOrders) {
        this.numeOrders = numeOrders;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        String s = String.format("%.2f", this.pret);
        return "Clientul: " + numeClient +
                " a cumparat " + total + " '" + numeOrders +
                "', pret total=" + s +
                " Ron\nCantitate: "+ total+
                "\nPretul per bucate este de: "+String.format("%.2f", pret/total)+" Ron";
    }
}
