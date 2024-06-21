import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Guidelines {
    JPanel guidelines_panel;
    HomePage homeObj;

    JLabel title, l1, l2, l3, l4;

    JTextArea t1, t2, t3, t4;

    String str;
    public Guidelines(HomePage homeObj)
    {
        this.homeObj = homeObj;

        guidelines_panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        JButton back = new JButton("Home");

        title = new JLabel("Important guidelines");
        title.setFont(new Font("Arial", Font.BOLD,26));
        title.setForeground(Color.BLUE);

        l1 = new JLabel("1. Locate the buckle");
        l1.setFont(new Font("Arial", Font.BOLD,20));
        l1.setForeground(Color.BLUE);

        l2 = new JLabel("2. Insert and secure the buckle");
        l2.setFont(new Font("Arial", Font.BOLD,20));
        l2.setForeground(Color.BLUE);

        l3 = new JLabel("3. Adjust the strap for a comfortable fit");
        l3.setFont(new Font("Arial", Font.BOLD,20));
        l3.setForeground(Color.BLUE);

        l4 = new JLabel("4. Releasing the seat belt");
        l4.setFont(new Font("Arial", Font.BOLD,20));
        l4.setForeground(Color.BLUE);


        str = "The seat belt buckle is typically located either on the seat itself or on the armrest.\n" +
                "Take a moment to identify its position before attempting to fasten your seat belt.";
        t1 = new JTextArea(100,100);
        t1.setFont(new Font("Arial", Font.PLAIN,16));
        t1.setForeground(Color.darkGray);
        t1.setEditable(false);
        t1.setText(str);

        str = "Take the metal end of the seat belt and insert it into the buckle until you hear a distinct click. \n" +
             "This sound indicates that the seat belt is properly secured.";
        t2 = new JTextArea(100,100);
        t2.setFont(new Font("Arial", Font.PLAIN,16));
        t2.setForeground(Color.darkGray);
        t2.setEditable(false);
        t2.setText(str);

        str = "To adjust the seat belt as per your comfort, you can adjust the strap by lifting up the flap \n" +
                "present on the seatbelt. You should ensure that it should neither very loose so you are free\n" +
                "to move nor be very tight and you are able to breathe properly.";
        t3 = new JTextArea(100,100);
        t3.setFont(new Font("Arial", Font.PLAIN,16));
        t3.setForeground(Color.darkGray);
        t3.setEditable(false);
        t3.setText(str);

        str = "Releasing the seat belt is the exact opposite of fastening it in the first place. You can also \n" +
                 "lift the flap and untie the seatbelt or can use the release button present in some seatbelts.";
        t4 = new JTextArea(100,100);
        t4.setFont(new Font("Arial", Font.PLAIN,16));
        t4.setForeground(Color.darkGray);
        t4.setEditable(false);
        t4.setText(str);

        cons.gridx = 0;
        cons.gridy = 0;
        guidelines_panel.add(title,cons);

        cons.gridx = 0;
        cons.gridy = 1;
        guidelines_panel.add(new JLabel(" "),cons);

        cons.gridx = 0;
        cons.gridy = 2;
        guidelines_panel.add(l1,cons);

        cons.gridx = 0;
        cons.gridy = 3;
        guidelines_panel.add(t1,cons);

        cons.gridx = 0;
        cons.gridy = 4;
        guidelines_panel.add(l2,cons);

        cons.gridx = 0;
        cons.gridy = 5;
        guidelines_panel.add(t2,cons);

        cons.gridx = 0;
        cons.gridy = 6;
        guidelines_panel.add(l3,cons);

        cons.gridx = 0;
        cons.gridy = 7;
        guidelines_panel.add(t3,cons);

        cons.gridx = 0;
        cons.gridy = 8;
        guidelines_panel.add(l4,cons);

        cons.gridx = 0;
        cons.gridy = 9;
        guidelines_panel.add(t4,cons);

        cons.gridx = 0;
        cons.gridy = 10;
        guidelines_panel.add(new JLabel(" "),cons);

        cons.gridx = 0;
        cons.gridy = 11;
        guidelines_panel.add(back,cons);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeObj.remove(guidelines_panel);
                homeObj.homePanel.setVisible(true);
            }
        });

        guidelines_panel.setSize(700,500);
        guidelines_panel.setVisible(true);
        homeObj.add(guidelines_panel);
    }
}
