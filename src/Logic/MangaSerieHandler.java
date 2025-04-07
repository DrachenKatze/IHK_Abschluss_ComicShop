package Logic;

import Model.Inventory;
import Model.Manga;
import Model.MangaSerie;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class MangaSerieHandler extends DatebaseHandler {


    public static void updateMangaSerieDB(String name, int value) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "UPDATE MangaSerie SET anzahl = ? WHERE manganame = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {

                // Parameter setzen
                pstmt.setInt(1, value);  // Ändert
                pstmt.setString(2, name);     // Datensatzes, der aktualisiert werden soll

                // SQL-Abfrage ausführen und Anzahl der betroffenen Zeilen erhalten
                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected + " Zeile(n) aktualisiert." + name + value);

            } catch (SQLException e) {
                System.out.println("Fehler beim Aktualisieren des Datensatzes: " + e.getMessage());
            }
        }catch (SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
    }

    // Manga in Datenbank StoreInventar anlegen
    public static void addMangaToInventory(String manganame, int bandnr, double preis, Inventory.Condition zustand){

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO MangaInventory (manganame, bandnummer, preis, zustand, bestand, lieferstatus) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(query)){

                // Zeile Neuer Manga
                pstmt.setString(1, manganame);
                pstmt.setInt(2, bandnr);
                pstmt.setDouble(3, preis);
                pstmt.setString(4, zustand.toString());
                pstmt.setInt(5,0);
                pstmt.setString(6, Manga.MangaStatus.Announced.toString());

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

    // Lade Daten aus der Manga Datenbank
    public List<MangaSerie> loadMangaSeriesList(){

        //Lokale Liste
        List<MangaSerie> mangaSerieListe = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            String query = "SELECT * FROM MangaSerie ORDER BY manganame";

            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(query);

                // geht alle Elemente der Tabelle durch
                while(rs.next()){
                    // Generiere Mangaserie und der Liste hinzufügen
                    mangaSerieListe.add(MangaSerie.createSerie(  rs.getString("manganame"),
                                                                rs.getString("autor"),
                                                                rs.getString("verlag"),
                                                                rs.getInt("anzahl") ));
                }

            }catch(SQLException e) {
                System.out.println("Fehler beim Empfangen des Datensatzes: " + e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
        return mangaSerieListe;
    }

    // todo
    // Schreibe Datensatz in Datenbank

    // Gibt StringLine zurück für Tabellen GUI Ausgabe
    public String[][] createStringLine(List<MangaSerie> mangaSerieListe){
        int anz = mangaSerieListe.size();
        String[][] stringList = new String[anz][4];

        for (int i = 0;  i < anz; i++) {
            stringList[i][0] = mangaSerieListe.get(i).getMsName();
            stringList[i][1] = String.valueOf(mangaSerieListe.get(i).getMsAnzahl());
            stringList[i][2] = mangaSerieListe.get(i).getMsAutor();
            stringList[i][3] = mangaSerieListe.get(i).getMsVerlag();
        }
        return stringList;
    }
}
