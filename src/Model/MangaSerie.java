package Model;

public class MangaSerie {

    // MangaSerie Daten
    private String msName;
    private String msAutor;
    private String msVerlag;
    int msAnzahl = 1;

    // Konstruktor
    private MangaSerie(String name, String autor, String verlag, int anzahl){
        msName = name;
        msAutor = autor;
        msVerlag = verlag;
        msAnzahl = anzahl;
    }

    // Fabrikmethoden
    public static MangaSerie createSerie(String name){
        return new MangaSerie(name,"unknown", "unknown", 1);
    }
    public static MangaSerie createSerie(String name, String autor, String verlag){
        return new MangaSerie(name,autor, verlag, 1);
    }
    public static MangaSerie createSerie(String name, String autor, String verlag, int anzahl){
        return new MangaSerie(name,autor, verlag, anzahl);
    }

    // Methoden
    public Manga[] mangaSerieAnlegen(){
        Manga[] m = new Manga[msAnzahl];

        for (int i = 0; i < msAnzahl; ){
           // m[i] = new Manga(msName, ++i);
            m[i] = new Manga(msName, ++i, 7, Manga.MangaStatus.RePrint);
        }
        return m;
    }

    public Manga mangaHinzufügen(){
        msAnzahl ++;
        return new Manga(msName, msAnzahl, 7, Manga.MangaStatus.Available);
    }

    // Getter
    public String getMsName() {
        return msName;
    }
    public String getMsAutor() {
        return msAutor;
    }
    public String getMsVerlag() {
        return msVerlag;
    }
    public int getMsAnzahl() {
        return msAnzahl;
    }

    // Setter
    public void setMsName(String msName) {
        this.msName = msName;
    }
    public void setMsAutor(String msAutor) {
        this.msAutor = msAutor;
    }
    public void setMsVerlag(String msVerlag) {
        this.msVerlag = msVerlag;
    }
    public void setMsAnzahl(int msAnzahl) {
        this.msAnzahl = msAnzahl;
    }
}
