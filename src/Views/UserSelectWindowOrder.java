package Views;

import Logic.OrderHandler;
import Model.Customer;
import Model.Manga;
import Model.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserSelectWindowOrder {

    // Daten
    String selectedManga;
    int band;
    String selectedUser;

    // Elemente
    JFrame userSelectWindow = new JFrame();
    JPanel windowPanel = new JPanel();
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JButton bestellen = new JButton("Bestellen");
    JButton abbrechen = new JButton("abbrechen");
    JLabel empty = new JLabel("   ");
    JLabel info;
    public JComboBox<String> userSelect;

    public UserSelectWindowOrder(String selectedManga, int band, List<Customer> customerData, Manga.MangaStatus status, TemplateTabelView targetView){

        this.selectedManga = selectedManga;
        this.band = band;

        // Set Window
        userSelectWindow.setSize(400,300);
        userSelectWindow.setVisible(true);
        userSelectWindow.setLocationRelativeTo(null);
        userSelectWindow.getContentPane().setBackground(new Color(121, 156, 164) );

        info = new JLabel(selectedManga + " Band " + band + "       ");
        userSelectWindow.setTitle("Einzel-Bestellung");
        userSelectWindow.add(windowPanel);

        // Combobox befüllem
        String[] customerNames = new String[customerData.size()];
        for (int i = 0; i < customerData.size(); i++) {
            customerNames[i] = customerData.get(i).getcName();
        }
        userSelect = new JComboBox<>(customerNames);

        // Layout
        windowPanel.setBackground(new Color(121, 156, 164));
        gbl.setConstraints(windowPanel, gbc);
        windowPanel.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        windowPanel.add(info, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        windowPanel.add(userSelect, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        windowPanel.add(empty, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        windowPanel.add(empty, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        windowPanel.add(bestellen, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        windowPanel.add(abbrechen, gbc);

        // Button Listener anhängen
        ListenerButtons(status.toString(), targetView);

        windowPanel.setVisible(true);
    }

    public void ListenerButtons(String mangaStatus, TemplateTabelView targetView) {

        bestellen.addActionListener(e -> {

            // Daten vorbereiten
            String user = userSelect.getSelectedItem().toString();
            int id = userSelect.getSelectedIndex() + 1; // +1 da id in Datenbank nicht bei 0 beginnt

            // Bestellstatus festlegen
            Order.OrderStatus orderstatus = Order.OrderStatus.hold; // default
            switch (mangaStatus){
                case "Announced" : orderstatus = Order.OrderStatus.pending;
                    break;
                case "Available" : orderstatus = Order.OrderStatus.openPayment;
                    break;
                case "RePrint" : orderstatus = Order.OrderStatus.pending;
                    break;
                case "OutOfPrint" : orderstatus = Order.OrderStatus.hold;
                    break;
            }

            // Bestellung in DB eintragen
            OrderHandler.insertOrderDB(selectedManga,band,orderstatus,id);

            // Bestellung eintragen (ist nur temporär) // nachträgliche änderungen wirken nicht
            String[] order = new String[]{selectedManga, String.valueOf(band), user, orderstatus.toString()};
            targetView.addRow(order);

            // Beendet das Fenster nach der Bestellung
            userSelectWindow.dispose();
        });

        abbrechen.addActionListener( e -> {
            userSelectWindow.dispose();
        });
    }
}
