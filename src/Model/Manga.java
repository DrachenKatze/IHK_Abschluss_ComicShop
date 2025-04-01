package Model;

import java.io.ObjectInputFilter;

public class Manga {

    public enum MangaStatus{Announced, Available, RePrint, OutOfPrint};

    private String mName;
    private int mNumber;
    private double preis;
    private MangaStatus status;

    // Konstruktor

    public Manga (String name, int number){
        mName = name;
        mNumber = number;
    }

    public Manga (String name, int number, double preis, MangaStatus status){
        this.mName = name;
        this.mNumber = number;
        this.preis = preis;
        this.status = status;
    }

    // Setter
    public void setStatus(MangaStatus status){
        this.status = status;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    // Getter
    public String getName(){
        return mName;
    }

    public int getNumber(){
        return mNumber;
    }

    public double getPreis() {
        return preis;
    }

    public MangaStatus getStatus() {
        return status;
    }

    public static MangaStatus getBestellStatusByString(String statusString){

        MangaStatus temp = MangaStatus.Announced; // Standard auf Hold, falls Daten nicht gefunden werden

        switch(statusString){
            case "Announced": temp = MangaStatus.Announced;
                break;
            case "Available": temp = MangaStatus.Available;
                break;
            case "RePrint": temp = MangaStatus.RePrint;
                break;
            case "OutOfPrint": temp = MangaStatus.OutOfPrint;
                break;
        }

        return temp;
    }

}
