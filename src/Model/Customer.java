package Model;

public class Customer {

    int customerID = 0;
    private String cName;
    private String cStrasse;
    private String cOrt;
    private String cEmail;
    private int cPLZ;
    private double cTelefon;

    // Konstrucktoren

    // Besteller Abholen
    public Customer(String name) {
        cName = name;
        cStrasse = "keine Angabe";
        cOrt = "keine Angabe";
        cEmail = "keine Angabe";
        cPLZ = 000000;
        cTelefon = 0000000000;
    }

    // Besteller Zusenden
    public Customer(String name, String strasse, String ort, String eMail, int plz, double telefon) {
        cName = name;
        cStrasse = strasse;
        cOrt = ort;
        cEmail = eMail;
        cPLZ = plz;
        cTelefon = telefon;
    }

    // Setter
    public void setAdress(String strasse, String ort, String eMail, int plz, long telefon) {
        cStrasse = "keine Angabe";
        cOrt = "keine Angabe";
        cEmail = "keine Angabe";
        cPLZ = 000000;
        cTelefon = 0000000000;
    }

    // Getter
    public int getCustomerID() {
        return customerID;
    }
    public String getcName() {
        return cName;
    }
    public int getcPLZ() {
        return cPLZ;
    }
    public String getcOrt() {
        return cOrt;
    }
    public String getcStrasse() {
        return cStrasse;
    }
    public double getcTelefon() {
        return cTelefon;
    }
    public String getcEmail() {
        return cEmail;
    }
}
