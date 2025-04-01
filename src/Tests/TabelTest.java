package Tests;

import Model.Manga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TabelTest {

    JFrame testFrame = new JFrame();
    JTable testTable;
    JPanel testPanel = new JPanel();
    static DefaultTableModel defaultTableModel;

    public TabelTest(){
        testFrame.setSize(800,600);
        testFrame.setLocationRelativeTo(null);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testFrame.add(testPanel);
        initPanel();
        testFrame.setVisible(true);
    }

    public void initPanel(){
        String[] columNames = {"ID", "Manga", "Nummer"};
        defaultTableModel = new DefaultTableModel(columNames, 0);

        testTable = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(testTable);
        testPanel.add(scrollPane);
    }


    public static void main(String[] args) {
        TabelTest testView = new TabelTest();
/*
        List<Manga> mangaListe = new ArrayList<>();
        mangaListe.add(new Manga("Dragon Test", 3));
        mangaListe.add(new Manga("Dragon Ball", 1));
        mangaListe.add(new Manga("Dragon Quest", 7));
        mangaListe.add(new Manga("Dan Da Dan", 17));
        mangaListe.add(new Manga("Gleipnir", 14));

        String[] row1 = {Integer.toString(1) ,mangaListe.getFirst().getName(), Integer.toString(mangaListe.getFirst().getNumber())};
        defaultTableModel.addRow(row1);
        */

    }
}
