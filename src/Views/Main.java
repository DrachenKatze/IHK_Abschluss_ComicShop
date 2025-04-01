package Views;

import javax.swing.*;
import java.awt.*;

public class Main {

    JFrame mainWindow = new JFrame();
    ViewHandler views = new ViewHandler();

    public void initWindow(){

        mainWindow.setName("MangaShop");
        mainWindow.setSize(800,640);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.getContentPane().setBackground(new Color(121, 156, 164) );   // Hintergrund TabLeiste

        // Reiter initialisieren
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(141, 176, 184));     // Reiterfarbe
        tabbedPane.add("Bestand", addPanel(views.itemInventory.jPanel));
        tabbedPane.add("Programm Übersicht", addPanel(views.itemOverview.jPanel));
        tabbedPane.add("Bestell Status", addPanel(views.orderStatus.jPanel));
        tabbedPane.add("Kunden Verwaltung", addPanel(views.customerAdministration.jPanel));

        mainWindow.add(tabbedPane,BorderLayout.CENTER);
    }
    
    // Panel mit Inhalt werden an die Reiter angehangen
    private JComponent addPanel(JPanel viewPanel){
        JComponent panel = new JPanel();
        panel.setBackground(new Color(141, 176, 184));  // Hintergrund des Panels
        panel.add(viewPanel);
        return panel;
    }

    // ProgrammStart/ Hauptfenster initialisation
    public static void main(String[] args) {
        Main myWindow = new Main();
        myWindow.initWindow();
        myWindow.mainWindow.setVisible(true);
    }


}
