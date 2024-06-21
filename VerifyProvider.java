import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import provider.homepage;

public class VerifyProvider {
    HomePage homeObj;
    JPanel login_panel;
    public VerifyProvider(HomePage homeObj)
    {
        this.homeObj = homeObj;

        login_panel = new JPanel(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();

        cons.insets = new Insets(2,2,4,4);

        // Labels
        JLabel email_lbl, password_lbl, signin_lbl, error_lbl;

        JTextField email;
        JPasswordField password;

        JButton signin, home;

        signin_lbl = new JLabel("Sign In");
        signin_lbl.setFont(new Font("Arial", Font.PLAIN,26));
        signin_lbl.setForeground(Color.gray);

        email_lbl = new JLabel(" EMAIL");
        email_lbl.setFont(new Font("Arial", Font.PLAIN,12));
        email_lbl.setForeground(Color.lightGray);

        password_lbl = new JLabel("PASSWORD");
        password_lbl.setFont(new Font("Arial", Font.PLAIN,12));
        password_lbl.setForeground(Color.lightGray);

        error_lbl = new JLabel("Invalid!");
        error_lbl.setFont(new Font("Arial", Font.ITALIC,12));
        error_lbl.setForeground(Color.red);

        // Text area
        email = new JTextField(25);
        password = new JPasswordField(25);

        // Buttons
        signin = new JButton("Sign in");

        home = new JButton("Home");

        // Arranging the components
        cons.anchor = GridBagConstraints.FIRST_LINE_START;
        cons.ipady = 10;

        cons.gridx = 0;
        cons.gridy = 0;
        login_panel.add(signin_lbl,cons);

        cons.anchor = GridBagConstraints.LINE_START;
        cons.gridx = 0;
        cons.gridy = 1;
        login_panel.add(email_lbl,cons);

        cons.gridx = 0;
        cons.gridy = 2;
        login_panel.add(email,cons);

        cons.gridx = 0;
        cons.gridy = 3;
        login_panel.add(email,cons);

        cons.gridx = 0;
        cons.gridy = 4;
        login_panel.add(password_lbl,cons);

        cons.gridx = 0;
        cons.gridy = 5;
        login_panel.add(password,cons);

        cons.gridx = 0;
        cons.gridy = 6;
        login_panel.add(new JLabel(""),cons);

        cons.anchor = GridBagConstraints.CENTER;

        cons.gridx = 0;
        cons.gridy = 7;
        login_panel.add(signin,cons);

        cons.gridheight = 2;
        cons.ipadx = 20;

        cons.gridx = 1;
        cons.gridy = 1;
        login_panel.add(new JLabel(""),cons);

        cons.gridx = 1;
        cons.gridy = 4;
        login_panel.add(new JLabel(""),cons);

        cons.gridx = 0;
        cons.gridy = 9;
        login_panel.add(error_lbl,cons);
        error_lbl.setVisible(false);

        cons.gridx = 1;
        cons.gridy = 9;
        login_panel.add(home,cons);

        signin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String em = email.getText();
                String pass = (String)password.getText();
                boolean verified = false;

                try {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet?user=root&password=2077");
                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery("select * from emp_details");
                    while(rs.next()) {
                        String em_database = rs.getString(1).trim();
                        String pass_database = rs.getString(2).trim();
//                        System.out.println("Length of password :"+pass_database.length());
//                        System.out.println("database :"+em_database+" "+pass_database);
                        if (em_database.equals(em) && pass_database.equals(pass)) {
                            verified = true;
                        }
                    }

                    con.close();
                }
                catch (Exception e)
                {
                    System.out.println("error in mysql");
                    e.printStackTrace();
                }

                if(verified)
                {
                    System.out.println("Email or password is correct");
                    email.setText("");
                    password.setText("");
                    callProvider(em);
                }
                else
                {
                    System.out.println("invalid ");
                    error_lbl.setVisible(true);
                }
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goHome();
            }
        });

        // customize login_panel
        login_panel.setSize(700,500);
        login_panel.setVisible(true);

        // add login_panel to JFrame
        homeObj.add(login_panel);
    }

    void callProvider(String em)
    {
        homepage pr = new homepage();
        pr.home_page("Provider");
    }

    void goHome()
    {
        homeObj.remove(login_panel);
        homeObj.homePanel.setVisible(true);
    }
}
