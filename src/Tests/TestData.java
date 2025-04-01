package Tests;

import Model.*;
import java.util.List;
import java.util.ArrayList;

// Klasse legt Testdaten an, welche die Datenbankausgabe simulieren
public class TestData {

    // Listen für alle Daten
    public List<MangaSerie> mangaSerieTestData = new ArrayList<>();
    public List<Manga> mangaTestData = new ArrayList<>();;
    public List<Customer> customerTestData = new ArrayList<>();;
    public List<Inventory> inventoryTestData = new ArrayList<>();;
    public List<Order> orderTestData = new ArrayList<>();;

    // Konstruktor - Erzeugt alle Testdaten
    public TestData(){

         //mangaSerieTestData.add(MangaSerie.createSerie("DanDaDan", "Yukinobu Tatsu", "CrunchyRoll", 7));
         mangaSerieTestData.add(MangaSerie.createSerie("Record of Lodoss War", "Ryō Mizuno", "Carlsen", 6));
         mangaSerieTestData.add(MangaSerie.createSerie("Gleipnir", "Sun Takeda", "Egmont", 14));
         mangaSerieTestData.add(MangaSerie.createSerie("Magic Knight Rayearth", "Clamp","Carlsen",6));
         mangaSerieTestData.add(MangaSerie.createSerie("Witch Watch", "Kenta Shinohara", "Egmont", 3));
         mangaSerieTestData.add(MangaSerie.createSerie("Made in Abyss" , "Akihito Tsukushi", "Altraverse", 13));
         mangaSerieTestData.add(MangaSerie.createSerie("Demon Slayer" , "Koyoharu Gotougeu", "MangaCult", 23));
/*
        // Manga wird eigentlich generiert
        mangaTestData.add(new Manga("Witch Watch", 1));
        mangaTestData.add(new Manga("Witch Watch", 2));
        mangaTestData.add(new Manga("Witch Watch", 3));
        mangaTestData.add(new Manga("Dragon Buster", 1));
        mangaTestData.add(new Manga("Dragon Buster", 4));
        mangaTestData.add(new Manga("Dragon Buster", 5));
        mangaTestData.add(new Manga("Demon Slayer", 1));
        mangaTestData.add(new Manga("Made in Abyss", 1));*/

        // Inventory wird auf Basis Manga generiert
        /*
        inventoryTestData.add(new Inventory(mangaTestData.get(0),5, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(0),2, Inventory.Condition.Used ));
        inventoryTestData.add(new Inventory(mangaTestData.get(1),5, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(1),0, Inventory.Condition.Used ));
        inventoryTestData.add(new Inventory(mangaTestData.get(2),15, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(2),3, Inventory.Condition.Used ));
        inventoryTestData.add(new Inventory(mangaTestData.get(3),3, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(3),6, Inventory.Condition.Used ));
        inventoryTestData.add(new Inventory(mangaTestData.get(4),7, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(4),9, Inventory.Condition.Used ));
        inventoryTestData.add(new Inventory(mangaTestData.get(5),3, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(5),1, Inventory.Condition.Used ));
        inventoryTestData.add(new Inventory(mangaTestData.get(6),25, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(6),3, Inventory.Condition.Used ));
        inventoryTestData.add(new Inventory(mangaTestData.get(7),13, Inventory.Condition.Neu));
        inventoryTestData.add(new Inventory(mangaTestData.get(7),2, Inventory.Condition.Used ));
*/

        // Customer
        //customerTestData.add(new Customer("Peter"));
        customerTestData.add(new Customer("Paul", "Ostrring 3","City" , "paulina@gmx.de", 10945, 03046710 ));
        customerTestData.add(new Customer("Sandra"));
        //customerTestData.add(new Customer("Yuki", "Am Katzberg 7", "Hohausen", "yuki-winter@webd.de", 19035, 015623724));

        // Orders
        Order order1 = new Order();
        Order order2 = new Order();
      //  Order order3 = new Order();
       // Order order4 = new Order();
       // Order order5 = new Order();
      //  order1.takeOrder(mangaTestData.get(0),customerTestData.get(3));
      //  order2.takeOrder(mangaTestData.get(3),customerTestData.get(2));
    //    order3.takeOrder(mangaTestData.get(6),customerTestData.get(3));
        order1.takeOrder(mangaTestData.get(3),customerTestData.get(0));
        order2.takeOrder(mangaTestData.get(7),customerTestData.get(1));

        orderTestData.add(order1);
        orderTestData.add(order2);
     //   orderTestData.add(order3);
      //  orderTestData.add(order4);
      //  orderTestData.add(order5);

    }

    // Main - Testmethode
    /*
    public static void main(String[] args) {
        TestData td = new TestData();
        System.out.println(td.mangaSerieTestData.getFirst().getMsName());
    }*/
}
