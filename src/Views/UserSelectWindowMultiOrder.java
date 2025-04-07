package Views;

import Logic.OrderHandler;
import Model.Customer;
import Model.Inventory;
import Model.Manga;
import Model.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserSelectWindowMultiOrder {

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
    JButton abbrechen = new JButton("Abbrechen");
    JLabel empty = new JLabel("   ");
    JLabel info;
    public JComboBox<String> userSelect;

    public UserSelectWindowMultiOrder(String selectedManga, List<Customer> customerData, List<Inventory> inventoryList, TemplateTabelView targetView){

        this.selectedManga = selectedManga;

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
        ListenerButtons(selectedManga, targetView, inventoryList);

        windowPanel.setVisible(true);
    }

    public void ListenerButtons(String selectedManga, TemplateTabelView targetView, List<Inventory> inventoryList) {

        bestellen.addActionListener(e -> {

            // Daten vorbereiten
            String customer = userSelect.getSelectedItem().toString();
            int id = userSelect.getSelectedIndex() + 1; // +1 da id in Datenbank nicht bei 0 beginnt

            // Daten für den Bestell-String
            String name;
            int band;
            Order.OrderStatus orderStatus;

            // Durchläuft die Inventory-liste um Bestellungen auszulösen
            for (int i = 0; i < inventoryList.size(); i++){

                // Test ob es ein Macht  gibt.
                if (inventoryList.get(i).getName().equals(selectedManga) &&
                        inventoryList.get(i).getiCondition() == Inventory.Condition.New){

                    name = inventoryList.get(i).getName();
                    band = inventoryList.get(i).getNumber();
                    Manga.MangaStatus mangaStatus = inventoryList.get(i).getStatus();
                    orderStatus = Order.getOrderStatus(mangaStatus.toString());

                    // Testausgabe
                    System.out.println(name + " " + band + " " + customer + " " + orderStatus.toString() );

                    // Bestellung in DB eintragen
                    OrderHandler.insertOrderDB(selectedManga,band,orderStatus,id);

                    // Bestellung eintragen (ist nur temporär) // nachträgliche änderungen wirken nicht
                    String[] order = new String[]{selectedManga, String.valueOf(band), customer, orderStatus.toString()};
                    targetView.addRow(order);
                }
            }

            // Beendet das Fenster nach der Bestellung
            userSelectWindow.dispose();
        });

        abbrechen.addActionListener( e -> {
            userSelectWindow.dispose();
        });
    }
}
