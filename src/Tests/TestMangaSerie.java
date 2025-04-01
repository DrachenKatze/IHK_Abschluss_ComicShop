package Tests;

import Model.Manga;
import Model.MangaSerie;

import java.util.ArrayList;
import java.util.Arrays;

public class TestMangaSerie {

    public static void main(String args[]){

       /* MangaSerie ms1 = new MangaSerie("Dragon Quest", "Musahi","FeuerBuch");
        ms1.updateAnzahl(7);

        MangaSerie ms2 = new MangaSerie("Dragon Master", "Musahi","FeuerBuch", 3);
        ms2.updateAnzahl(3);


        System.out.println(ms1.getMsName() + " " + ms1.getMsAutor() + " " + ms1.getMsVerlag() + " " + ms1.getMsAnzahl());
        System.out.println(ms2.getMsName() + " " + ms2.getMsAutor() + " " + ms2.getMsVerlag() + " " + ms2.getMsAnzahl());

        */

        MangaSerie serie1 = MangaSerie.createSerie("DragonMaster", "Mashihoro", "FeuerSpiel", 5);
        System.out.println( serie1.getMsName() + " " + serie1.getMsAutor() + " " +
                            serie1.getMsVerlag() + " " + serie1.getMsAnzahl());

        ArrayList<Model.Manga> mangas = new ArrayList<>(Arrays.asList(serie1.mangaSerieAnlegen()));

        for (Manga m : mangas) {
            System.out.println(m.getName() + " Band: " + m.getNumber());
        }

        System.out.println("");
        mangas.add(serie1.mangaHinzufügen());

        for (Manga m : mangas) {
            System.out.println(m.getName() + " Band: " + m.getNumber());
        }



    }
}
