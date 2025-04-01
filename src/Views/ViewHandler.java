package Views;

import Logic.*;
import Model.Customer;
import Model.Inventory;
import Model.MangaSerie;
import Model.Order;

import java.util.List;

public class ViewHandler {

    // EventHandler für die Buttonfunktionen
    ButtonEventsHandler buttonEvents = new ButtonEventsHandler();

    // Views definieren
    static TemplateTabelView itemInventory = new TemplateTabelView();              // Inventar für den Verkauf
    TemplateTabelView itemOverview = new TemplateTabelView();               // Programm Übersicht der MangaSerien
    TemplateTabelView orderStatus = new TemplateTabelView();                // Übersicht aller Bestellungen
    TemplateTabelView customerAdministration = new TemplateTabelView();     // Nutzerverwaltung

    // ColumNames initialisieren
    static String[] itemInventoryTableColumnsNames = {"Manga", "Band", "Preis", "Zustand", "Bestand", "Liefer-Status"};    // Inventar
    String[] itemOverviewTableColumnsNames = {"Manga Serie", "Anzahl Bände", "Autor", "Verlag"};                    // Programm Übersicht
    String[] customerOrdersTableColumNames = {"Manga", "Band", "Kunde", "Bestell-Status"};                          // Bestell-Übersicht
    String[] customerAdministrationTableColumNames = {"Name", "Strasse", "Ort", "PLZ", "Email", "Telefon"};         // Nutzerverwaltung

    // Daten Handler für Zugriff auf die Datenmodelle definieren
    static InventroyHandler inventoryDaten = new InventroyHandler();
    MangaSerieHandler mangaSerienDaten = new MangaSerieHandler();
    CustomerHandler customerDaten = new CustomerHandler();
    OrderHandler ordersDaten = new OrderHandler();

    // Daten über Handler aus der Datenbank bereitstellen
    List<MangaSerie> mangaSerieListe = mangaSerienDaten.loadMangaSeriesList();
    static List<Inventory> inventoryList = inventoryDaten.loadInventoryList();
    List<Customer> customerList = customerDaten.loadCustomerList();
    List<Order> orderList = ordersDaten.loadOrdersList();

    // Konstruktor, initialisiert die Buttons/ Views und übergibt die Daten für die Tabelle
    public ViewHandler(){

        // 01: Buttons für das spezifische Layout initialisieren, muss als erstes gemacht werden
        itemInventory.initInventoryButtons();
        itemOverview.initItemOverviewButtons();
        orderStatus.initOrderStatusButtons();
        customerAdministration.initCustomerAdministrationButtons();

        // 02: TabellenFenster initialisieren, Buttons müssen schon initialisiert sie, sonst sind sie NULL
        initView(itemInventory, itemInventoryTableColumnsNames, inventoryDaten.createStringLine(inventoryList));
        initView(itemOverview, itemOverviewTableColumnsNames, mangaSerienDaten.createStringLine(mangaSerieListe));
        initView(orderStatus, customerOrdersTableColumNames, ordersDaten.createStringLine(orderList));
        initView(customerAdministration, customerAdministrationTableColumNames, customerDaten.createStringLine(customerList));

        // ButtonEvents zuweisen, darf erst nach dem Initialisieren der Buttons erfolgen
        buttonEvents.ListenerInventoryButtons(itemInventory, inventoryList);
        buttonEvents.ListenerItemOverview(itemOverview, mangaSerieListe);
        buttonEvents.ListenerOrderButtons(orderStatus);
        buttonEvents.ListenerCustomerAdministration(customerAdministration);

        //todo: Sort by Name ausgeben??
    }

    // Initialisiert die Spezifische View und fügt die Daten ein
    // TabellenView, Tabellen Spalten-Überschriften, Tabellen-Inhalt
    private static void initView(TemplateTabelView view, String[] columNames, String[][] rowArray){
        view.initPanel(); // Panel laden
        view.initTabel(columNames); // Tabelle mit Überschrift laden
        view.setLayout(view); // Layout anwenden

        // Daten rowArray in Tabelle laden
        for (int i = 0; i < rowArray.length; i++) {
            view.addRow(rowArray[i]);
        }
    }

    public static void refreashInventoryTable(){
        initView(itemInventory, itemInventoryTableColumnsNames, inventoryDaten.createStringLine(inventoryList));
    }

}