package Views;

import Logic.OrderHandler;
import Model.Customer;
import Model.Manga;
import Model.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserSelectWindow {

    // Daten
    String selectedManga;
    int band;
    String selectedUser;

    // Elemente
    JFrame userSelectWindow = new JFrame();
    JPanel windowPanel = new JPanel();
    JButton bestellen = new JButton("Bestellen");
    JButton abbrechen = new JButton("abbrechen");
    JLabel info;
    public JComboBox<String> userSelect;

    public UserSelectWindow(String selectedManga, int band, List<Customer> customerData, Manga.MangaStatus status){

        // Set Window
        userSelectWindow.setSize(400,300);
        userSelectWindow.setVisible(true);
        userSelectWindow.setLocationRelativeTo(null);
        userSelectWindow.getContentPane().setBackground(new Color(121, 156, 164) );
        userSelectWindow.add(windowPanel);

        this.selectedManga = selectedManga;
        this.band = band;
        info = new JLabel(selectedManga + " Band " + band + " bestellen");

        // Combobox befüllem
        String[] customerNames = new String[customerData.size()];
        for (int i = 0; i < customerData.size(); i++) {
            customerNames[i] = customerData.get(i).getcName();
        }
        userSelect = new JComboBox<>(customerNames);

        //todo Layout

        // Elemente hinzufügen
        windowPanel.add(info);
        windowPanel.add(userSelect);
        windowPanel.add(bestellen);
        windowPanel.add(abbrechen);
        ListenerButtons(status.toString());

        windowPanel.setVisible(true);
    }

    public void ListenerButtons(String mangaStatus) {

        bestellen.addActionListener(e -> {

            // todo  Bestellung in View eintragen
            // Bestellung in der OrderList anlegen // wird ev nicht benötiggt
            // OrderHandler.addOrder(selectedManga,band,userSelect.getSelectedItem().toString());
            // targetview.initview();

            // Bestellung in DB eintragen

            // Daten vorbereiten
            String user = userSelect.getSelectedItem().toString();
            int id = userSelect.getSelectedIndex() + 1; // +1 da id in Datenbank nicht bei 0 beginnt

            // Bestellstatus festlegen
            Order.BestellStatus orderstatus = Order.BestellStatus.hold; // default

            switch (mangaStatus){
                case "Announced" : orderstatus = Order.BestellStatus.pending;
                    break;
                case "Available" : orderstatus = Order.BestellStatus.openPayment;
                    break;
                case "RePrint" : orderstatus = Order.BestellStatus.pending;
                    break;
                case "OutOfPrint" : orderstatus = Order.BestellStatus.hold;
                    break;
            }

            OrderHandler.insertOrderDB(selectedManga,band,orderstatus,id);

            // Beendet das Fentser nach der Bestellung
            userSelectWindow.dispose();
        });

        abbrechen.addActionListener( e -> {
            userSelectWindow.dispose();
        });
    }

}
