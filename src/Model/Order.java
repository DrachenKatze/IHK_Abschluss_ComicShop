package Model;

public class Order {

    // pending is for not in Stock, hold is for OutofPrint Comics
    public enum OrderStatus {openPayment, payed, delivered, pending, hold, canceled};

    private Manga orderdManga;
    private OrderStatus orderStatus;
    private boolean paymentStatus; // Benötigt?
    private boolean deliverStatus; // Benötigt?
    private Customer orderCustomer;

    // Getter
    public Manga getOrderdManga() {
        return orderdManga;
    }
    public OrderStatus getOrderStatus() {
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
    public void setStatus(OrderStatus status) {
        orderStatus = status;
    }
    public void setPaymentStatus(){
        paymentStatus = true;
        orderStatus = OrderStatus.payed;
    }
    public void setDeliverStatus(){
        deliverStatus = true;
        orderStatus = OrderStatus.delivered;
        // todo bestand reduzieren
    }
    public void setOrderCustomer(Customer orderCustomer){
        this.orderCustomer = orderCustomer;
    }
    public void SetOrder(Manga orderdManga, OrderStatus orderStatus, Customer customer){
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
            orderStatus = OrderStatus.openPayment;
        } else if (localGoods == 0){    // Manga kann bestellt werden (Verfügbar/Nachdruck/Angekündigt)
            if (    orderdManga.getStatus().equals(Manga.MangaStatus.Available) ||
                    orderdManga.getStatus().equals(Manga.MangaStatus.RePrint) ||
                    orderdManga.getStatus().equals(Manga.MangaStatus.Announced) ) {
                    orderStatus = OrderStatus.pending; // pending, warte darauf das der Bestand erhöht wird
            } else { // Manga ist nicht verfügbar (Out of Print)
                    orderStatus = OrderStatus.hold;
            }
        }
        this.orderCustomer = customer;
    }

    public boolean orderCheck(Manga orderdManga){ // prüft ob sich der Bestellstatus verändert hat, wird von Bestand aufgerufen

        boolean changed = false;

        // todo - Bestansds-pruefung
        int localGoods = 1; // Replace with: Bestandsabfrage aus Bestands-Liste/Klasse Neu/Gebraucht beachten

        if (localGoods >= 1){
            this.orderStatus = OrderStatus.openPayment;
            changed = true;
        }

        return changed;
    }

    public static OrderStatus getOrderStatus(String mangaStatus){

        OrderStatus orderstatus = OrderStatus.hold; // default
        switch (mangaStatus){
            case "Announced" : orderstatus = OrderStatus.pending;
                break;
            case "Available" : orderstatus = OrderStatus.openPayment;
                break;
            case "RePrint" : orderstatus = OrderStatus.pending;
                break;
            case "OutOfPrint" : orderstatus = OrderStatus.hold;
                break;
        }

        return orderstatus;
    }

    // Gibt aus einem String das passende ENUM zurück
    public static OrderStatus getBestellStatusByString(String statusString){

        OrderStatus temp = OrderStatus.hold; // Standard auf Hold, falls Daten nicht gefunden werden

        switch(statusString){
            case "openPayment": temp = OrderStatus.openPayment;
                break;
            case "payed": temp = OrderStatus.payed;
                break;
            case "delivered": temp = OrderStatus.delivered;
                break;
            case "pending": temp = OrderStatus.pending;
                break;
            case "hold": temp = OrderStatus.hold;
                break;
            case "canceled": temp = OrderStatus.canceled;
                break;
        }

        return temp;
    }
}
