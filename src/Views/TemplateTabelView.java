package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Model.Order;

public class TemplateTabelView {

    JPanel jPanel = new JPanel();
    static DefaultTableModel defaultTableModel;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    // Elemente fürs Panel
    JComboBox filter = new JComboBox();
    public JTable jTable;
    JScrollPane scrollPane;
    public JButton button1 = new JButton(" ");
    public JButton button2 = new JButton(" ");
    public JButton button3 = new JButton(" ");
    public JComboBox<Order.BestellStatus> statusSelect = new JComboBox<>(Order.BestellStatus.values());

    // Konstruktor - setzt die Farbe des TabellenRahmen
    public TemplateTabelView(){
        jPanel.setBackground(new Color(121, 156, 164)); // TabellenRahmen
    }

    // Panel initialisieren
    protected void initPanel() {
        filter.setPreferredSize(new Dimension(160, 40));

        //Button Funktionen


        // ExAMPLE
        /*
        button3.addActionListener(e -> {
            //fuktionalität
            JOptionPane.showMessageDialog(jPanel, "Button geklickt!");
        });
        */

    }

    // Tabelle initialisieren
    protected void initTabel(String[] columNames){
        defaultTableModel = new DefaultTableModel(columNames, 0);
        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);
    }

    // Layout festsetzen
    protected void setLayout(TemplateTabelView view){

        gbl.setConstraints(jPanel, gbc);
        jPanel.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        jPanel.add(filter,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanel.add(scrollPane,gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 50;
        jPanel.add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        jPanel.add(button2, gbc);
        jPanel.add(statusSelect, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        jPanel.add(button3, gbc);

    }
    // Zeile hinzufügen
    protected void addRow(String[] row){
        defaultTableModel.addRow(row);
    }

    public void initInventoryButtons(){
        button1 = new JButton("Verkaufen");
        button2 = new JButton("Bestand Erhöhen");
        button3 = new JButton("Bestellen");
        statusSelect.setVisible(false);
    }

    public void initItemOverviewButtons(){
        button1 = new JButton("Serie bestellen");
        button2 = new JButton("Serie Band hinzufügen");
        button3 = new JButton("Serie anlegen");
        statusSelect.setVisible(false);
    }

    public void initOrderStatusButtons(){
        button1 = new JButton("Stornieren");
        button2.setVisible(false);
        statusSelect.setVisible(true); // Status ändern
        button3.setVisible(false);
    }

    public void initCustomerAdministrationButtons(){
        button1 = new JButton("Kunde anlegen");
        button2 = new JButton("Kundendaten ändern");
        button3.setVisible(false);
        statusSelect.setVisible(false);
    }
}
