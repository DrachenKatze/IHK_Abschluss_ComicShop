package Old;

import Views.TemplateTabelView;

import javax.swing.*;
import java.awt.*;

public class StatusSelectWindow {

    JFrame statusSelectWindow = new JFrame();
    JPanel panel = new JPanel();
    JComboBox statusSelectBox;
    String[] selectedStatus;
    String[] selectedUser;
    TemplateTabelView view;

    public StatusSelectWindow(int selectedRow, TemplateTabelView view){

        this.view = view;

        statusSelectWindow.setSize(300,300);
        statusSelectWindow.setVisible(true);
        statusSelectWindow.setLocationRelativeTo(null);
        statusSelectWindow.getContentPane().setBackground(new Color(121, 156, 164) );

        //todo
        String[] classics = { "Norton", "Brough Superior", "Vincent", "Matchless" };
        statusSelectBox = new JComboBox(classics);

        statusSelectBox.setMaximumRowCount(3);
        statusSelectBox.addActionListener(
                e -> selectedStatus = (String[]) statusSelectBox.getSelectedItem());

        setStatus(selectedStatus, selectedRow);

        statusSelectWindow.add(panel);
        panel.add(statusSelectBox);
        panel.setVisible(true);
    }

    private void setStatus(String[] selectedStatus, int selectIndex) {
        view.jTable.setValueAt(selectedStatus,selectIndex,3);
    }
}
