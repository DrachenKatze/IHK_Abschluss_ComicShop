package Logic;

import Model.Customer;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CustomerHandler extends DatebaseHandler{

    // Lade Daten aus der Customer Datenbank
    public List<Customer> loadCustomerList(){

        //Lokale Liste
        List<Customer> customerList = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            String query = "SELECT * FROM Customer";

            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(query);

                // geht alle Elemente der Tabelle durch
                while(rs.next()){
                    // Generiere Mangaserie und der Liste hinzufügen
                    customerList.add(new Customer(  rs.getString("kundenname"),
                                                    rs.getString("strasse"),
                                                    rs.getString("ort"),
                                                    rs.getString("email"),
                                                    rs.getInt("plz"),
                                                    rs.getDouble("tel") ));
                }
            }catch(SQLException e) {
                System.out.println("Fehler beim Empfangen des Datensatzes: " + e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
        return customerList;
    }

    // todo
    // Schreibe Datensatz in Datenbank

    // erzeugt String Liste für die Ausgabe in der Tabelle
    public String[][] createStringLine(List<Customer> customerList){
        int anz = customerList.size();
        String[][] stringList = new String[anz][6];

        for (int i = 0;  i < anz; i++) {
            stringList[i][0] = customerList.get(i).getcName();
            stringList[i][1] = customerList.get(i).getcStrasse();
            stringList[i][2] = customerList.get(i).getcOrt();
            stringList[i][3] = String.valueOf(customerList.get(i).getcPLZ());
            stringList[i][4] = customerList.get(i).getcEmail();
            stringList[i][5] = String.valueOf(customerList.get(i).getcTelefon());
        }
        return stringList;
    }


}
