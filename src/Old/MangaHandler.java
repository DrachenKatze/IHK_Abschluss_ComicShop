package Old;

import Model.Manga;

import java.util.ArrayList;
import java.util.List;

//todo: wird nicht mehr benötigt, eventuell um MangaListe zu erstellen als Creator?
public class MangaHandler {

    private List<Manga> mangaList;

    public MangaHandler(){
        mangaList = new ArrayList<>();
    }

    public void setMangaList(List<Manga> mangaList) {
        this.mangaList = mangaList;
    }

    public List<Manga> getMangaList() {
        return mangaList;
    }

    // erzeugt String Liste für die Ausgabe in der Tabelle
    public String[][] createStringLine(){
        int anz = mangaList.size();
        String[][] stringList = new String[anz][5];

        for (int i = 0;  i < anz; i++) {
            stringList[i][0] = mangaList.get(i).getName();
            stringList[i][1] = String.valueOf(mangaList.get(i).getNumber());
            stringList[i][2] = String.valueOf(mangaList.get(i).getPreis());
            stringList[i][3] = "";
            stringList[i][4] = String.valueOf(mangaList.get(i).getStatus());
        }
        return stringList;
    }
}
