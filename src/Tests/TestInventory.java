package Tests;

import Model.Inventory;
import Model.Manga;

import java.util.*;

public class TestInventory {

    public static void main(String[] args) {

        List<Inventory> mangaBestand = new ArrayList<>();
        mangaBestand.add(new Inventory(new Manga("Doragon", 1), 3, Inventory.Condition.New));
        mangaBestand.add(new Inventory(new Manga("Doragon", 1), 7, Inventory.Condition.Used));
        mangaBestand.add(new Inventory(new Manga("Doragon", 2), 1, Inventory.Condition.New));

        String ausgabe = mangaBestand.getFirst().getiManga().getName();
        ausgabe += " " + mangaBestand.getFirst().getiValue();
        ausgabe += " " + mangaBestand.getFirst().getiCondition();

        System.out.println(ausgabe);
    }
}
