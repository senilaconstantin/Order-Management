package model;

/**
 * Aceasta clasa reprezinta tabela din baza de date clienti avand aceleasi atribute ca si acolo.
 */
public class Clienti {
    private int idClienti;
    private String numeClienti;
    private String adresa;
    private String email;
    public Clienti(){

    }

    public Clienti(int idClienti, String nume, String adresa, String email) {
        this.idClienti=idClienti;
        this.numeClienti = nume;
        this.adresa = adresa;
        this.email = email;
    }

    public Clienti(String nume, String adresa, String email) {
        this.numeClienti = nume;
        this.adresa = adresa;
        this.email = email;
    }

    public int getIdClienti() {
        return idClienti;
    }

    public void setIdClienti(int idClienti) {
        this.idClienti = idClienti;
    }

    public String getNumeClienti() {
        return numeClienti;
    }

    public void setNumeClienti(String numeClienti) {
        this.numeClienti = numeClienti;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Clienti{" +
                "idClienti=" + idClienti +
                ", nume='" + numeClienti +
                ", adresa='" + adresa  +
                ", email='" + email  +
                '}';
    }
}
