package Model;

public class Order {

    // pending is for not in Stock, hold is for OutofPrint Comics
    public enum BestellStatus{openPayment, payed, delivered, pending, hold, canceled};

    private Manga orderdManga;
    private BestellStatus orderStatus;
    private boolean paymentStatus; // Benötigt?
    private boolean deliverStatus; // Benötigt?
    private Customer orderCustomer;

    // Getter
    public Manga getOrderdManga() {
        return orderdManga;
    }
    public BestellStatus getOrderStatus() {
        return orderStatus;
    }
    public boolean isPaymentStatus() {
        return paymentStatus;
    }
    public boolean isDeliverStatus() {
        return deliverStatus;
    }
    public Customer getOrderCustomer() {
        return orderCustomer;
    }

    // Setter
    public void setStatus(BestellStatus status) {
        orderStatus = status;
    }
    public void setPaymentStatus(){
        paymentStatus = true;
        orderStatus = BestellStatus.payed;
    }
    public void setDeliverStatus(){
        deliverStatus = true;
        orderStatus = BestellStatus.delivered;
        // todo bestand reduzieren
    }
    public void setOrderCustomer(Customer orderCustomer){
        this.orderCustomer = orderCustomer;
    }
    public void SetOrder(Manga orderdManga, BestellStatus orderStatus, Customer customer){
        this.orderdManga = orderdManga;
        this.orderStatus = orderStatus;
        this.orderCustomer = customer;
    }

    // Bestellung auslösen, setzt den Customer und prüft Bestellstatus
    public void takeOrder(Manga oManga, Customer customer){
        this.orderdManga = oManga;

        // todo - Bestansds-pruefung
        int localGoods = 1; // Replace with: Bestandsabfrage aus Bestands-Liste/Klasse Neu/Gebraucht beachten

        if (localGoods >= 1){   // Manga ist im Bestand
            orderStatus = BestellStatus.openPayment;
        } else if (localGoods == 0){    // Manga kann bestellt werden (Verfügbar/Nachdruck/Angekündigt)
            if (    orderdManga.getStatus().equals(Manga.MangaStatus.Available) ||
                    orderdManga.getStatus().equals(Manga.MangaStatus.RePrint) ||
                    orderdManga.getStatus().equals(Manga.MangaStatus.Announced) ) {
                    orderStatus = BestellStatus.pending; // pending, warte darauf das der Bestand erhöht wird
            } else { // Manga ist nicht verfügbar (Out of Print)
                    orderStatus = BestellStatus.hold;
            }
        }
        this.orderCustomer = customer;
    }

    public boolean orderCheck(Manga orderdManga){ // prüft ob sich der Bestellstatus verändert hat, wird von Bestand aufgerufen

        boolean changed = false;

        // todo - Bestansds-pruefung
        int localGoods = 1; // Replace with: Bestandsabfrage aus Bestands-Liste/Klasse Neu/Gebraucht beachten

        if (localGoods >= 1){
            this.orderStatus = BestellStatus.openPayment;
            changed = true;
        }

        return changed;
    }

    // Gibt aus einem String das passende ENUM zurück
    public static BestellStatus getBestellStatusByString(String statusString){

        BestellStatus temp = BestellStatus.hold; // Standard auf Hold, falls Daten nicht gefunden werden

        switch(statusString){
            case "openPayment": temp = BestellStatus.openPayment;
                break;
            case "payed": temp = BestellStatus.payed;
                break;
            case "delivered": temp = BestellStatus.delivered;
                break;
            case "pending": temp = BestellStatus.pending;
                break;
            case "hold": temp = BestellStatus.hold;
                break;
            case "canceled": temp = BestellStatus.canceled;
                break;
        }

        return temp;
    }
}
