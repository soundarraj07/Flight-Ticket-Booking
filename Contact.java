import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Contact {
    JPanel contact_panel;
    HomePage homeObj;
    public Contact(HomePage homeObj)
    {
        this.homeObj = homeObj;

        contact_panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        JButton back = new JButton("Home");

        JLabel title = new JLabel("Contact details");
        title.setFont(new Font("Arial", Font.BOLD,34));
        title.setForeground(Color.BLUE);

        JTextArea details = new JTextArea(100,100);
        details.setFont(new Font("Arial", Font.PLAIN,20));
        details.setForeground(Color.darkGray);
        details.setEditable(false);

        String str = "\nPh. no 1 : 1223346448\n";
        str += "\nph. no 2 : 5346734235\n";
        str += "\nemail : support.tigerfirejet@gmail.com\n";
        str += "\nPlace : Coimbatore, Tamilnadu\n";

        details.setText(str);

        cons.gridx = 0;
        cons.gridy = 0;
        contact_panel.add(title,cons);

        cons.gridx = 0;
        cons.gridy = 1;
        contact_panel.add(new JLabel(" "),cons);

        cons.gridx = 0;
        cons.gridy = 2;
        contact_panel.add(details,cons);

        cons.gridx = 0;
        cons.gridy = 3;
        contact_panel.add(new JLabel(" "),cons);

        cons.gridx = 0;
        cons.gridy = 4;
        contact_panel.add(back,cons);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeObj.remove(contact_panel);
                homeObj.homePanel.setVisible(true);
            }
        });

        contact_panel.setSize(700,500);
        contact_panel.setVisible(true);
        homeObj.add(contact_panel);
    }
}
