package Logic;

import Model.Customer;
import Model.Manga;
import Model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHandler extends DatebaseHandler{

    // ladet die Bestelldaten aus der Datenbank
    public static List<Order> loadOrdersList(){

    //Lokale Bestell-Liste
    List<Order> orderList = new ArrayList<>();

    try{
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        String query = "select * From Orders, Customer where Orders.kundenid = Customer.kundenid";

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            // geht alle Elemente der Tabelle durch
            while(rs.next()){

                Order tempOrder = new Order();
                // erstellt eine neue Bestellung aus den Datenbank Daten
                tempOrder.SetOrder( new Manga(rs.getString("manganame"), rs.getInt("bandnr")),
                                    Order.getBestellStatusByString(rs.getString("bestellstatus")),
                                    new Customer(rs.getString("kundenname")) );

                orderList.add(tempOrder);
            }

        }catch(SQLException e) {
            System.out.println("Fehler beim Empfangen des Datensatzes: " + e.getMessage());
        }
    }catch(SQLException e) {
        System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
    }
    return orderList;
}

    // Fügt eine Bestellung in die Datenbank ein
    public static void insertOrderDB(String name, int bandnr, Order.OrderStatus bestellstatus, int kundenid){

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO Orders (manganame, bandnr, bestellstatus, kundenid) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(query)){

                // Zeile Neuer Manga
                pstmt.setString(1, name);
                pstmt.setInt(2, bandnr);
                pstmt.setString(3, bestellstatus.toString());
                pstmt.setInt(4,kundenid);

                // Schritt 5: SQL-Abfrage ausführen und Datensatz hinzufügen
                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected + " Zeile(n) eingefügt.");

            }catch (SQLException e) {
                System.out.println("Fehler beim Einfügen des Datensatzes: " + e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }

    }

    // Gibt StringLine zurück für Tabellen GUI Ausgabe
    public static String[][] createStringLine(List<Order> orderList){
        int anz = orderList.size();
        String[][] stringList = new String[anz][4];

        for (int i = 0;  i < anz; i++) {
            stringList[i][0] = orderList.get(i).getOrderdManga().getName();
            stringList[i][1] = String.valueOf(orderList.get(i).getOrderdManga().getNumber());
            stringList[i][2] = orderList.get(i).getOrderCustomer().getcName();
            stringList[i][3] = orderList.get(i).getOrderStatus().toString();
        }
        return stringList;
    }
}
