// Program for login page
import com.mysql.cj.jdbc.StatementImpl;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import java.sql.*;
import java.util.StringTokenizer;

class Login extends JFrame
{
    JPanel login_panel;
    JPanel signInPanel;
    JPanel signUpPanel;
    JTabbedPane jtp;

    HomePage homeObj;

    public Login(HomePage homeObj)
    {
        this.homeObj = homeObj;

        login_panel = new JPanel(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();

        cons.insets = new Insets(2,2,4,4);

        // Labels
        JLabel email_lbl, password_lbl, title1, title2, signin_lbl, signup_lbl, error_lbl;

        JTextField email;
        JPasswordField password;

        JButton signin, join, home;

        signin_lbl = new JLabel("Sign In");
        signin_lbl.setFont(new Font("Arial", Font.PLAIN,26));
        signin_lbl.setForeground(Color.gray);

        email_lbl = new JLabel(" EMAIL");
        email_lbl.setFont(new Font("Arial", Font.PLAIN,12));
        email_lbl.setForeground(Color.lightGray);

        password_lbl = new JLabel("PASSWORD");
        password_lbl.setFont(new Font("Arial", Font.PLAIN,12));
        password_lbl.setForeground(Color.lightGray);

        signup_lbl = new JLabel("Sign Up");
        signup_lbl.setFont(new Font("Arial", Font.PLAIN,26));
        signup_lbl.setForeground(Color.gray);

        title1 = new JLabel(" Not a member of");
        title1.setFont(new Font("Arial", Font.ITALIC,15));
        title1.setForeground(Color.black);

        title2 = new JLabel("  TigerFirejet community");
        title2.setFont(new Font("Arial", Font.ITALIC,15));
        title2.setForeground(Color.black);

        error_lbl = new JLabel("Invalid!");
        error_lbl.setFont(new Font("Arial", Font.ITALIC,12));
        error_lbl.setForeground(Color.red);

        // Text area
        email = new JTextField(25);
        password = new JPasswordField(25);

        // Buttons
        signin = new JButton("Sign in");

        join = new JButton("Join");

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
        cons.gridy = 0;
        login_panel.add(signup_lbl,cons);

        cons.gridx = 1;
        cons.gridy = 1;
        login_panel.add(new JLabel(""),cons);

        cons.gridx = 1;
        cons.gridy = 2;
        login_panel.add(title1,cons);

        cons.gridx = 1;
        cons.gridy = 3;
        login_panel.add(title2,cons);

        cons.gridx = 1;
        cons.gridy = 4;
        login_panel.add(new JLabel(""),cons);

        cons.gridx = 1;
        cons.gridy = 5;
        login_panel.add(join,cons);

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

                    ResultSet rs = stmt.executeQuery("select * from user_details");
                    while(rs.next()) {
                        String em_database = rs.getString(1).trim();
                        String pass_database = rs.getString(2).trim();
                        System.out.println("Length of password :"+pass_database.length());
                        System.out.println("database :"+em_database+" "+pass_database);
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
                    callSignIn(em);
                }
                else
                {
                    System.out.println("invalid ");
                    error_lbl.setVisible(true);
                }
            }
        });

        join.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                callSignUp();
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

    // this method is called when sign in button is clicked
    void callSignIn(String em)
    {
        login_panel.setVisible(false);
        signInPanelMethod(em);
    }

    // this method called when join button is clicked
    void callSignUp()
    {
        login_panel.setVisible(false);
        signUpPanelMethod();
    }

    // sign in panel method
    void signInPanelMethod(String em)
    {
        jtp = new JTabbedPane();

        jtp.addTab("user details",new UserDetailsPanel(homeObj,this,em));
        jtp.addTab("Manage Booking",new ManageBookingPanel(homeObj,this,em));

        homeObj.add(jtp);
    }

    // sign up panel method
    void signUpPanelMethod()
    {
        signUpPanel = new JPanel(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();

        JTextField email, password, phone, address, dob;

        JLabel email_lbl, password_lbl, phone_lbl, address_lbl, dob_lbl, gender_lbl, title1, error_lbl;

        JComboBox dd, mm, yyyy, gender;

        JButton create, back;

        title1 = new JLabel("Enter the details ");
        title1.setFont(new Font("Arial", Font.PLAIN,25));
        title1.setForeground(Color.gray);

        email_lbl = new JLabel("Email");
        email_lbl.setFont(new Font("Arial", Font.PLAIN,16));
        email_lbl.setForeground(Color.gray);

        password_lbl = new JLabel("Password");
        password_lbl.setFont(new Font("Arial", Font.PLAIN,16));
        password_lbl.setForeground(Color.gray);

        phone_lbl = new JLabel("Phone");
        phone_lbl.setFont(new Font("Arial", Font.PLAIN,16));
        phone_lbl.setForeground(Color.gray);

        address_lbl = new JLabel("Address");
        address_lbl.setFont(new Font("Arial", Font.PLAIN,16));
        address_lbl.setForeground(Color.gray);

        dob_lbl = new JLabel("Date of Birth");
        dob_lbl.setFont(new Font("Arial", Font.PLAIN,16));
        dob_lbl.setForeground(Color.gray);

        gender_lbl = new JLabel("Gender");
        gender_lbl.setFont(new Font("Arial", Font.PLAIN,16));
        gender_lbl.setForeground(Color.gray);

        error_lbl = new JLabel("provide all details");
        error_lbl.setFont(new Font("Arial", Font.ITALIC,16));
        error_lbl.setForeground(Color.red);

        email = new JTextField(25);
        password = new JTextField(25);
        phone = new JTextField(25);
        address = new JTextField(25);
        dob = new JTextField(25);

        int i;
        dd = new JComboBox();
        for(i=1;i<32;i++)
            dd.addItem(i);

        mm = new JComboBox();
        for(i=1;i<13;i++)
            mm.addItem(i);

        yyyy = new JComboBox();
        for(i=1980;i<2024;i++)
            yyyy.addItem(i);

        gender = new JComboBox();
        gender.addItem("Male");
        gender.addItem("Female");
        gender.addItem("others");

        create = new JButton("Create");
        back = new JButton("Go Back");

        cons.gridwidth = 2;
        cons.anchor = GridBagConstraints.CENTER;

        cons.gridx = 0;
        cons.gridy = 0;
        signUpPanel.add(title1,cons);

        cons.gridx = 0;
        cons.gridy = 1;
        signUpPanel.add(new JLabel("---------------------------------------------"),cons);

        cons.insets = new Insets(5,5,5,5);
        cons.ipady = 10;
        cons.anchor = GridBagConstraints.WEST;
        cons.gridwidth = 1;

        cons.gridx = 0;
        cons.gridy = 2;
        signUpPanel.add(email_lbl,cons);

        cons.gridx = 0;
        cons.gridy = 3;
        signUpPanel.add(password_lbl,cons);

        cons.gridx = 0;
        cons.gridy = 4;
        signUpPanel.add(phone_lbl,cons);

        cons.gridx = 0;
        cons.gridy = 5;
        signUpPanel.add(address_lbl,cons);

        cons.gridx = 0;
        cons.gridy = 6;
        signUpPanel.add(dob_lbl,cons);

        cons.gridx = 0;
        cons.gridy = 7;
        signUpPanel.add(gender_lbl,cons);

        cons.gridwidth = 3;

        cons.gridx = 1;
        cons.gridy = 2;
        signUpPanel.add(email,cons);

        cons.gridx = 1;
        cons.gridy = 3;
        signUpPanel.add(password,cons);

        cons.gridx = 1;
        cons.gridy = 4;
        signUpPanel.add(phone,cons);


        cons.gridx = 1;
        cons.gridy = 5;
        signUpPanel.add(address,cons);

        cons.gridwidth = 1;

        cons.fill = GridBagConstraints.NONE;
        cons.gridx = 1;
        cons.gridy = 6;
        signUpPanel.add(dd,cons);

        cons.gridx = 2;
        cons.gridy = 6;
        signUpPanel.add(mm,cons);

        cons.gridx = 3;
        cons.gridy = 6;
        signUpPanel.add(yyyy,cons);

        cons.gridx = 1;
        cons.gridy = 7;
        signUpPanel.add(gender,cons);

        cons.gridx = 1;
        cons.gridy = 8;
        signUpPanel.add(new JLabel("  "),cons);

        cons.gridx = 1;
        cons.gridy = 9;
        signUpPanel.add(create,cons);

        cons.gridx = 3;
        cons.gridy = 9;
        signUpPanel.add(back,cons);

        cons.gridx = 0;
        cons.gridy = 9;
        signUpPanel.add(error_lbl,cons);
        error_lbl.setVisible(false);

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    // connecting to mysql database
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet?user=root&password=2077");
                    Statement stmt = con.createStatement();
                    PreparedStatement ps;
                    String em, pass, ph, addr, date, gen;

                    em = email.getText();
                    pass = password.getText();
                    ph = phone.getText();
                    addr = address.getText();
                    date = Integer.toString((int) dd.getSelectedItem()) + " " + Integer.toString((int) mm.getSelectedItem()) + " " + Integer.toString((int) yyyy.getSelectedItem());
                    gen = (String)gender.getSelectedItem();

                    if (em.equals("") || pass.equals("") || ph.equals("") || addr.equals("") || gen.equals("")) {
                        System.out.println("No");
                        error_lbl.setVisible(true);
                    }
                    else
                    {
                        ps = con.prepareStatement("INSERT INTO `tigerfirejet`.`user_details` (`email`, `password`, `phone_no`, `address`, `dob`, `gender`) VALUES (?,?,?,?,?,?)");
                        ps.setString(1,em);
                        ps.setString(2,pass);
                        ps.setString(3,ph);
                        ps.setString(4,addr);
                        ps.setString(5,date);
                        ps.setString(6,gen);
                        int na = ps.executeUpdate();
                        System.out.println(na+" row inserted");
                        create.setEnabled(false);
                    }
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println("Error occurred while executing");
                    e.printStackTrace();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                goBack();
            }
        });

        signUpPanel.setSize(500,500);
        signUpPanel.setVisible(true);

        homeObj.add(signUpPanel);
    }

    void goBack()
    {
        homeObj.remove(signUpPanel);
        login_panel.setVisible(true);
    }

    void goHome()
    {
        homeObj.remove(login_panel);
        homeObj.homePanel.setVisible(true);
    }
}

class UserDetailsPanel extends JPanel
{
    Login loginObj;
    HomePage homeObj;
    UserDetailsPanel(HomePage homeObj,Login loginObj, String em)
    {
        this.loginObj = loginObj;
        this.homeObj = homeObj;

        this.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();

        cons.insets = new Insets(2,2,5,5);

        cons.ipady = 10;
        cons.anchor = GridBagConstraints.WEST;

        String pass="", ph="", addr="", dob="", gen="";

        JLabel email_lbl1, phone_lbl1, address_lbl1, dob_lbl1, gender_lbl1, title1;
        JLabel email_lbl2, phone_lbl2, address_lbl2, dob_lbl2, gender_lbl2;

        JButton back;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet?user=root&password=2077");
            Statement stmt = con.createStatement();
            PreparedStatement ps;

            ps = con.prepareStatement("select * from user_details where email=?");
            ps.setString(1,em);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                pass = rs.getString(2);
                ph = rs.getString(3);
                addr = rs.getString(4);
                dob = rs.getString(5);
                gen = rs.getString(6);
            }

            con.close();
        }
        catch (Exception e)
        {
            System.out.println("error in mysql");
            e.printStackTrace();
        }

        title1 = new JLabel("User Details");
        title1.setFont(new Font("Arial", Font.BOLD,25));
        title1.setForeground(Color.blue);

        email_lbl1 = new JLabel("EMAIL");
        email_lbl1.setFont(new Font("Arial", Font.PLAIN,15));
        email_lbl1.setForeground(Color.gray);

        phone_lbl1 = new JLabel("PHONE NO");
        phone_lbl1.setFont(new Font("Arial", Font.PLAIN,15));
        phone_lbl1.setForeground(Color.gray);

        address_lbl1 = new JLabel("ADDRESS");
        address_lbl1.setFont(new Font("Arial", Font.PLAIN,15));
        address_lbl1.setForeground(Color.gray);

        dob_lbl1 = new JLabel("DATE OF BIRTH");
        dob_lbl1.setFont(new Font("Arial", Font.PLAIN,15));
        dob_lbl1.setForeground(Color.gray);

        gender_lbl1 = new JLabel("GENDER");
        gender_lbl1.setFont(new Font("Arial", Font.PLAIN,15));
        gender_lbl1.setForeground(Color.gray);

        // displaying details retrieved from database
        email_lbl2 = new JLabel(": "+em);
        email_lbl2.setFont(new Font("Arial", Font.PLAIN,15));
        email_lbl2.setForeground(Color.black);

        phone_lbl2 = new JLabel(": "+ph);
        phone_lbl2.setFont(new Font("Arial", Font.PLAIN,15));
        phone_lbl2.setForeground(Color.black);

        address_lbl2 = new JLabel(": "+addr);
        address_lbl2.setFont(new Font("Arial", Font.PLAIN,15));
        address_lbl2.setForeground(Color.black);

        dob_lbl2 = new JLabel(": "+dob);
        dob_lbl2.setFont(new Font("Arial", Font.PLAIN,15));
        dob_lbl2.setForeground(Color.black);

        gender_lbl2 = new JLabel(": "+gen);
        gender_lbl2.setFont(new Font("Arial", Font.PLAIN,15));
        gender_lbl2.setForeground(Color.black);

        back = new JButton("Go Back");
        back.setFont(new Font("Arial", Font.PLAIN,15));
        back.setForeground(Color.darkGray);

        cons.gridwidth = 2;

        cons.gridx = 0;
        cons.gridy = 0;
        this.add(title1,cons);

        cons.gridwidth = 1;

        cons.gridx = 0;
        cons.gridy = 1;
        this.add(email_lbl1,cons);

        cons.gridx = 0;
        cons.gridy = 2;
        this.add(phone_lbl1,cons);

        cons.gridx = 0;
        cons.gridy = 3;
        this.add(address_lbl1,cons);

        cons.gridx = 0;
        cons.gridy = 4;
        this.add(dob_lbl1,cons);

        cons.gridx = 0;
        cons.gridy = 5;
        this.add(gender_lbl1,cons);

        cons.gridx = 1;
        cons.gridy = 1;
        this.add(email_lbl2,cons);

        cons.gridx = 1;
        cons.gridy = 2;
        this.add(phone_lbl2,cons);

        cons.gridx = 1;
        cons.gridy = 3;
        this.add(address_lbl2,cons);

        cons.gridx = 1;
        cons.gridy = 4;
        this.add(dob_lbl2,cons);

        cons.gridx = 1;
        cons.gridy = 5;
        this.add(gender_lbl2,cons);

        cons.gridx = 1;
        cons.gridy = 6;
        this.add(new JLabel(""),cons);

        cons.gridx = 1;
        cons.gridy = 7;
        this.add(back,cons);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack(loginObj);
                System.out.println("back is pressed");
            }
        });
    }

    void goBack(Login loginObj)
    {
//        loginObj.login_panel.remove(loginObj.jtp);
//        loginObj.login_panel.setVisible(true);

        homeObj.remove(loginObj.jtp);
        loginObj.login_panel.setVisible(true);
    }
}

class ManageBookingPanel extends JPanel
{
    Login loginObj;
    HomePage homeObj;
    String em="";
    ManageBookingPanel(HomePage homeObj,Login loginObj, String em)
    {
        this.loginObj = loginObj;
        this.homeObj = homeObj;
        this.em = em;

        this.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();

        cons.insets = new Insets(2,2,5,5);

        cons.ipady = 10;
        cons.anchor = GridBagConstraints.WEST;

        JLabel flight1, from1, to1, startDate1, startTime1, reachDate1, reachTime1, adult1, children1, pnr1, title1, title2, cancelled_lbl;
        JLabel flight2, from2, to2, startDate2, startTime2, reachDate2, reachTime2, adult2, children2, pnr2;

        String from="", to="", start="", startDate="", startTime="", reach="", reachDate="", reachTime="", adult="", children="", pnr="", flightName = "";

        JButton back, cancel;

        boolean booked = false;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet?user=root&password=2077");
            Statement stmt = con.createStatement();
            PreparedStatement ps;

            ps = con.prepareStatement("select * from booking_details where email=?");
            ps.setString(1,em);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                from = rs.getString(2);
                to = rs.getString(3);
                start = rs.getString(4);
                reach = rs.getString(5);
                adult = rs.getString(8);
                children = rs.getString(9);
                pnr = rs.getString(12);
                flightName = rs.getString(13);

                StringTokenizer st = new StringTokenizer(start);
                while(st.hasMoreTokens()) {
                    startDate = st.nextToken();
                    startTime = st.nextToken();
                }

                st = new StringTokenizer(reach);
                while(st.hasMoreTokens()) {
                    reachDate = st.nextToken();
                    reachTime = st.nextToken();
                }

                booked = true;
            }


            con.close();
        }
        catch (Exception e)
        {
            System.out.println("error in mysql");
            e.printStackTrace();
        }

        title1 = new JLabel("Booking Details");
        title1.setFont(new Font("Arial", Font.PLAIN, 26));
        title1.setForeground(Color.blue);

        flight1 = new JLabel("Flight");
        flight1.setFont(new Font("Arial", Font.ITALIC, 15));
        flight1.setForeground(Color.darkGray);

        from1 = new JLabel("From");
        from1.setFont(new Font("Arial", Font.ITALIC, 15));
        from1.setForeground(Color.darkGray);

        to1 = new JLabel("To");
        to1.setFont(new Font("Arial", Font.ITALIC, 15));
        to1.setForeground(Color.darkGray);

        startDate1 = new JLabel("Start Date");
        startDate1.setFont(new Font("Arial", Font.ITALIC, 15));
        startDate1.setForeground(Color.darkGray);

        startTime1 = new JLabel("Start Time");
        startTime1.setFont(new Font("Arial", Font.ITALIC, 15));
        startTime1.setForeground(Color.darkGray);

        reachDate1 = new JLabel("Reach Date");
        reachDate1.setFont(new Font("Arial", Font.ITALIC, 15));
        reachDate1.setForeground(Color.darkGray);

        reachTime1 = new JLabel("Reach Time");
        reachTime1.setFont(new Font("Arial", Font.ITALIC, 15));
        reachTime1.setForeground(Color.darkGray);

        adult1 = new JLabel("Adult");
        adult1.setFont(new Font("Arial", Font.ITALIC, 15));
        adult1.setForeground(Color.darkGray);

        children1 = new JLabel("Children");
        children1.setFont(new Font("Arial", Font.ITALIC, 15));
        children1.setForeground(Color.darkGray);

        pnr1 = new JLabel("PNR No");
        pnr1.setFont(new Font("Arial", Font.ITALIC, 15));
        pnr1.setForeground(Color.darkGray);

        title2 = new JLabel("No bookings available");
        title2.setFont(new Font("Arial", Font.ITALIC, 15));
        title2.setForeground(Color.darkGray);

        cancelled_lbl = new JLabel("Cancelled successfully");
        cancelled_lbl.setFont(new Font("Arial", Font.ITALIC, 15));
        cancelled_lbl.setForeground(Color.blue);

        flight2 = new JLabel(" : " + flightName);
        flight2.setFont(new Font("Arial", Font.ITALIC, 15));
        flight2.setForeground(Color.blue);

        from2 = new JLabel(" : " + from);
        from2.setFont(new Font("Arial", Font.ITALIC, 15));
        from2.setForeground(Color.blue);

        to2 = new JLabel(" : " + to);
        to2.setFont(new Font("Arial", Font.ITALIC, 15));
        to2.setForeground(Color.blue);

        startDate2 = new JLabel(": " + startDate);
        startDate2.setFont(new Font("Arial", Font.ITALIC, 15));
        startDate2.setForeground(Color.blue);

        startTime2 = new JLabel(" : " + startTime);
        startTime2.setFont(new Font("Arial", Font.ITALIC, 15));
        startTime2.setForeground(Color.blue);

        reachDate2 = new JLabel(" : " + reachDate);
        reachDate2.setFont(new Font("Arial", Font.ITALIC, 15));
        reachDate2.setForeground(Color.blue);

        reachTime2 = new JLabel(" : " + reachTime);
        reachTime2.setFont(new Font("Arial", Font.ITALIC, 15));
        reachTime2.setForeground(Color.blue);

        adult2 = new JLabel(" : " + adult);
        adult2.setFont(new Font("Arial", Font.ITALIC, 15));
        adult2.setForeground(Color.blue);

        children2 = new JLabel(" : " + children);
        children2.setFont(new Font("Arial", Font.ITALIC, 15));
        children2.setForeground(Color.blue);

        pnr2 = new JLabel(" : " + pnr);
        pnr2.setFont(new Font("Arial", Font.ITALIC, 15));
        pnr2.setForeground(Color.blue);

        back = new JButton("Go back");
        cancel = new JButton("Cancel booking");

        cons.gridx = 0;
        cons.gridy = 0;
        this.add(title1, cons);

        if(booked) {
            cons.gridwidth = 1;

            cons.gridx = 0;
            cons.gridy = 1;
            this.add(flight1,cons);

            cons.gridx = 0;
            cons.gridy = 2;
            this.add(from1, cons);

            cons.gridx = 0;
            cons.gridy = 3;
            this.add(to1, cons);

            cons.gridx = 0;
            cons.gridy = 4;
            this.add(startDate1, cons);

            cons.gridx = 0;
            cons.gridy = 5;
            this.add(startTime1, cons);

            cons.gridx = 0;
            cons.gridy = 6;
            this.add(reachDate1, cons);

            cons.gridx = 0;
            cons.gridy = 7;
            this.add(reachTime1, cons);

            cons.gridx = 0;
            cons.gridy = 8;
            this.add(adult1, cons);

            cons.gridx = 0;
            cons.gridy = 9;
            this.add(children1, cons);

            cons.gridx = 0;
            cons.gridy = 10;
            this.add(pnr1, cons);

            cons.gridx = 1;
            cons.gridy = 1;
            this.add(flight2, cons);

            cons.gridx = 1;
            cons.gridy = 2;
            this.add(from2, cons);

            cons.gridx = 1;
            cons.gridy = 3;
            this.add(to2, cons);

            cons.gridx = 1;
            cons.gridy = 4;
            this.add(startDate2, cons);

            cons.gridx = 1;
            cons.gridy = 5;
            this.add(startTime2, cons);

            cons.gridx = 1;
            cons.gridy = 6;
            this.add(reachDate2, cons);

            cons.gridx = 1;
            cons.gridy = 7;
            this.add(reachTime2, cons);

            cons.gridx = 1;
            cons.gridy = 8;
            this.add(adult2, cons);

            cons.gridx = 1;
            cons.gridy = 9;
            this.add(children2, cons);

            cons.gridx = 1;
            cons.gridy = 10;
            this.add(pnr2, cons);

            cons.gridx = 1;
            cons.gridy = 11;
            this.add(cancel, cons);

            cons.gridx = 1;
            cons.gridy = 0;
            this.add(cancelled_lbl, cons);
            cancelled_lbl.setVisible(false);

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean cancelled = false;
                    cancelled = cancelBooking(em);

                    if(cancelled)
                    {
                        cancelled_lbl.setVisible(true);
                        cancel.setEnabled(false);
                    }
                }
            });
        }
        else
        {
            cons.gridx = 0;
            cons.gridy = 1;
            this.add(title2, cons);
        }

        cons.gridx = 0;
        cons.gridy = 11;
        this.add(back, cons);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack(loginObj);
            }
        });
    }

    void goBack(Login loginObj)
    {
//        loginObj.login_panel.remove(loginObj.jtp);
//        loginObj.login_panel.setVisible(true);

        homeObj.remove(loginObj.jtp);
        loginObj.login_panel.setVisible(true);
    }

    boolean cancelBooking(String em)
    {
        boolean cancelled = false;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet?user=root&password=2077");

            PreparedStatement ps;

            ps = con.prepareStatement("DELETE FROM `tigerfirejet`.`booking_details` WHERE (`email` = ?);");
            ps.setString(1,em);

            int na = ps.executeUpdate();
            if(na > 0)
            {
                cancelled = true;
                System.out.println("Deleted successfully");
            }
            else
            {
                System.out.println("Cannot delete");
            }

            con.close();
        }
        catch(Exception e)
        {
            System.out.println("error in mysql");
            e.printStackTrace();
        }
        return cancelled;
    }
}

//public class Login
//{
//    public static void main(String args[])
//    {
//        LoginDemo obj = new LoginDemo();
//        obj.setTitle("Login page");
//        obj.setSize(700,500);
//        obj.setVisible(true);
//        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//}
