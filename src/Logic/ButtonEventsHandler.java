package Logic;

import Model.Customer;
import Model.MangaSerie;
import Model.Order;
import Model.Inventory;
import Views.CustomerEditView;
import Views.TemplateTabelView;
import Views.UserSelectWindow;

import java.util.List;


// Todo : Daten in DB wieder eintragen
public class ButtonEventsHandler {

    // EventListener
    public void ListenerInventoryButtons(TemplateTabelView view, List<Inventory> tabelData, List<Customer> customerData){

        // Button Verkaufen
        view.button1.addActionListener( e -> {

            int selectIndex = view.jTable.getSelectedRow();

            // Prüfen damit Bestand nicht unter 0 sinkt
            if (tabelData.get(selectIndex).getiValue() > 0) {
                tabelData.get(selectIndex).setiValue(tabelData.get(selectIndex).getiValue()-1);
            } else{
                tabelData.get(selectIndex).setiValue(tabelData.get(selectIndex).getiValue());
            }

            // Tabelle updaten
            view.jTable.setValueAt(tabelData.get(selectIndex).getiValue(),selectIndex,4);

            // Veränderung in DB eintragen
            InventroyHandler.updateInventoryDB( tabelData.get(selectIndex).getId(),
                                                tabelData.get(selectIndex).getiValue());
        });

        // Button Bestand erhöhen
        view.button2.addActionListener( e -> {
            int selectIndex = view.jTable.getSelectedRow();
            tabelData.get(selectIndex).setiValue(tabelData.get(selectIndex).getiValue()+1);
            view.jTable.setValueAt(tabelData.get(selectIndex).getiValue(),selectIndex,4);

            // Veränderung in DB eintragen
            InventroyHandler.updateInventoryDB( tabelData.get(selectIndex).getId(),
                                                tabelData.get(selectIndex).getiValue());
        });

        // Bestellen - öffnet Bestell-Fenster
        view.button3.addActionListener( e -> {

            // Auswahl des zu Bestellenden Manga
            int selectIndex = view.jTable.getSelectedRow();
            String selectedManga = tabelData.get(selectIndex).getiManga().getName();
            int band = tabelData.get(selectIndex).getiManga().getNumber();

            // Neues Fenster Aufrufen zur Nutzerauswahl
            new UserSelectWindow(selectedManga, band, customerData);
        });
    }

    // Programm Übersicht
    public void ListenerItemOverview(TemplateTabelView view, List<MangaSerie> tabelData){

        // Serie bestellen
        view.button1.addActionListener( e -> {
            // Bestellvorgang aus Abfrage 1 für komplette Serie ausführen

            int selectIndex = view.jTable.getSelectedRow();
            String selectedManga = tabelData.get(selectIndex).getMsName(); // MangaSerie bestimmen
           // String selectedManga = tabelData.get(selectIndex).getiManga().getName();
            //tabelData.get(selectIndex).setiValue(tabelData.get(selectIndex).getiValue()+1);
            //view.jTable.setValueAt(tabelData.get(selectIndex).getiValue(),selectIndex,4);

            //todo: Order tabel laden

            //todo
            // in Tabelle/ Datenbank Bestellungen kreieren

        });

        // Serie um 1 Band erhöhen -> neuen Bände in Inventar anlegen
        view.button2.addActionListener( e -> {

            int selectIndex = view.jTable.getSelectedRow();
            tabelData.get(selectIndex).setMsAnzahl(tabelData.get(selectIndex).getMsAnzahl()+1); // Erehöht Bände um 1
            view.jTable.setValueAt(tabelData.get(selectIndex).getMsAnzahl(),selectIndex,1);

            MangaSerieHandler.updateMangaSerieDB(tabelData.get(selectIndex).getMsName(),tabelData.get(selectIndex).getMsAnzahl());

            // Legt neuen Manga in der DB an, NEW und USED
            MangaSerieHandler.addMangaToInventory(  tabelData.get(selectIndex).getMsName(),
                                                    tabelData.get(selectIndex).getMsAnzahl(),
                                                   7.50d,
                                                   Inventory.Condition.New);
            MangaSerieHandler.addMangaToInventory(  tabelData.get(selectIndex).getMsName(),
                                                    tabelData.get(selectIndex).getMsAnzahl(),
                                               3.50d,
                                                    Inventory.Condition.Used);

            // ToDO - Tabelle Manga neu laden
            // Überarbeiten, ev daten zusätzlich holen und hier senden?
            //ViewHandler.refreashInventoryTable();

            // Serienbestellungen updaten!
            // Alle Kunden die eine Serie bestellt haben, erhalten auch den neuen Band!

        });

        // Serie anlegen
        view.button3.addActionListener( e -> {
            // Neue Bände in der Mangatabelle hinzufügen
        });

    }

    // Bestell Übersicht
    public void ListenerOrderButtons(TemplateTabelView view){

        // Storniern
        view.button1.addActionListener( e -> {
            int selectIndex = view.jTable.getSelectedRow();
            view.jTable.setValueAt(Order.BestellStatus.canceled.toString(),selectIndex,3);
        });

        // Status ändern
        view.statusSelect.addActionListener( e -> {
            int selectIndex = view.jTable.getSelectedRow();
            view.jTable.setValueAt(view.statusSelect.getSelectedItem().toString(),selectIndex,3);
        });

        // Status prüfen
        // Prüft ob bestand vorhanden ist, ändert bestand/ status und gibt Nachricht aus, welche Bände zurückgelegt werden sollen
        view.button3.addActionListener( e -> {
        });
    }

    // Kunden Übersicht
    public void ListenerCustomerAdministration(TemplateTabelView view){

        // Kunde anlegen
        view.button1.addActionListener( e -> {
            // neue View aufrufen um Kunden anzulegen
            // komplette Spalte hinzufügen und anzeigen


            int selectIndex = view.jTable.getSelectedRow();
            //String selectedManga = tabelData.get(selectIndex).getiManga().getName();

            // Neues Fenster Aufrufen zur Nutzerauswahl
            new CustomerEditView();
        });

        // Kundendaten ändern
        view.button2.addActionListener( e -> {
            // neue View aufrufen um Kunden anzulegen
            // komplette Spalte überschreiben und anzeigen
            new CustomerEditView();
        });
    }

}
