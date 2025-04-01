package Views;

import Model.Customer;

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

    public UserSelectWindow(String selectedManga, int band, List<Customer> customerData){

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
        ListenerButtons();

        windowPanel.setVisible(true);
    }

    public void ListenerButtons() {

        bestellen.addActionListener(e -> {
            //todo
            // Bestellung anlegen
            // Bestellung in DB eintragen
        });

        abbrechen.addActionListener( e -> {
            userSelectWindow.dispose();
        });
    }

}
