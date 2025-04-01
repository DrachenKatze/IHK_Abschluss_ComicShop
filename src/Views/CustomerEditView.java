package Views;

import javax.swing.*;
import java.awt.*;

public class CustomerEditView {

    JFrame statusSelectWindow = new JFrame();
    JPanel panel = new JPanel();
    TemplateTabelView view;


    //todo - unterschieden anlegen oder bearbeiten

    public CustomerEditView(){

        this.view = view;

        statusSelectWindow.setSize(300,300);
        statusSelectWindow.setVisible(true);
        statusSelectWindow.setLocationRelativeTo(null);
        statusSelectWindow.getContentPane().setBackground(new Color(121, 156, 164) );

        panel.setVisible(true);
    }


}
