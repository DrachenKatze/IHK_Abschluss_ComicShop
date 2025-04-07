package Logic;

import Model.Customer;
import Model.Inventory;
import Model.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventroyHandler extends DatebaseHandler{

    // Lade Daten aus der Manga Datenbank
    public static List<Inventory> loadInventoryList(){

        //Lokale Liste
        List<Inventory> storeInventory = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            String query = "SELECT * FROM MangaInventory ORDER BY manganame, bandnummer";

            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(query);

                // geht alle Elemente der Tabelle durch
                while(rs.next()){

                    storeInventory.add(new Inventory(new Manga( rs.getString("manganame"),
                                                                rs.getInt("bandnummer"),
                                                                rs.getDouble("preis"),
                                                                Manga.getBestellStatusByString(rs.getString("lieferstatus"))
                                                                ),
                                                    rs.getInt("bestand"),
                                                    Inventory.getConditionByString(rs.getString("zustand")),
                                                    rs.getInt("id") ) );
                }

            }catch(SQLException e) {
                System.out.println("Fehler beim Empfangen des Datensatzes: " + e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
        return storeInventory;
    }

    // Update eines Datensatzes in der Datenbank
    public static void updateInventoryDB(int id, int value) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "UPDATE MangaInventory SET bestand = ? WHERE id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {

                // Parameter setzen
                pstmt.setInt(1, value);       // Ändert Wert
                pstmt.setInt(2, id);     // Datensatzes, der aktualisiert werden soll

                // SQL-Abfrage ausführen und Anzahl der betroffenen Zeilen erhalten
                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected + " Zeile(n) aktualisiert." + id + value);

            } catch (SQLException e) {
                System.out.println("Fehler beim Aktualisieren des Datensatzes: " + e.getMessage());
            }
        }catch (SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
    }

    // todo - Schreibe Datensatz in Datenbank
    public static void insertInventoryDB(){}

    // erzeugt String Liste für die Ausgabe in der Tabelle
    public String[][] createStringLine(List<Inventory> inventoryList){
        int anz = inventoryList.size();
        String[][] stringList = new String[anz][6];

        for (int i = 0;  i < anz; i++) {
            stringList[i][0] = inventoryList.get(i).getiManga().getName();
            stringList[i][1] = String.valueOf(inventoryList.get(i).getiManga().getNumber());
            stringList[i][2] = inventoryList.get(i).getPreis() + " EUR";
            stringList[i][3] = String.valueOf(inventoryList.get(i).getiCondition());
            stringList[i][4] = String.valueOf(inventoryList.get(i).getiValue());
            stringList[i][5] = String.valueOf(inventoryList.get(i).getStatus());
        }
        return stringList;
    }
}
