import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bookticket.*;
public class HomePage extends JFrame
{
    JPanel homePanel;

    JButton login, bookTicket, guidelines, provider, contact, search, close;

    JLabel title, message1, message2;

    Image img;

    public HomePage()
    {
        homePanel = new JPanel(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        
        cons.insets = new Insets(5,5,5,5);

//        img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Downloads\\back2.jpg");

        title = new JLabel("Welcome to TigerFirejet airways");
        title.setFont(new Font("Arial", Font.BOLD,22));
        title.setForeground(Color.blue);

        message1 = new JLabel("Travel across the world");
        message1.setFont(new Font("Arial", Font.PLAIN,24));
        message1.setForeground(Color.darkGray);

        message2 = new JLabel("in our flight");
        message2.setFont(new Font("Arial", Font.PLAIN,24));
        message2.setForeground(Color.darkGray);

        login = new JButton("login");
        login.setFont(new Font("Arial", Font.PLAIN,16));
        login.setForeground(Color.darkGray);

        bookTicket = new JButton("Book Tickets");
        bookTicket.setFont(new Font("Arial", Font.PLAIN,16));
        bookTicket.setForeground(Color.darkGray);

        provider = new JButton("Provider");
        provider.setFont(new Font("Arial", Font.PLAIN,16));
        provider.setForeground(Color.darkGray);

        guidelines = new JButton("Guidelines");
        guidelines.setFont(new Font("Arial", Font.PLAIN,16));
        guidelines.setForeground(Color.darkGray);

        contact = new JButton("Contact");
        contact.setFont(new Font("Arial", Font.PLAIN,16));
        contact.setForeground(Color.darkGray);

        search = new JButton("Search");
        search.setFont(new Font("Arial", Font.PLAIN,16));
        search.setForeground(Color.darkGray);

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN,16));
        close.setForeground(Color.red);


        cons.gridwidth = 2;

        cons.gridx = 0;
        cons.gridy = 0;
        homePanel.add(title,cons);

        cons.gridx = 0;
        cons.gridy = 1;
        homePanel.add(new JLabel(" "),cons);

//        cons.gridwidth = 1;

        cons.gridx = 3;
        cons.gridy = 2;
        homePanel.add(login,cons);

        cons.gridx = 3;
        cons.gridy = 3;
        homePanel.add(provider,cons);

        cons.gridx = 3;
        cons.gridy = 4;
        homePanel.add(search,cons);

        cons.gridx = 3;
        cons.gridy = 5;
        homePanel.add(guidelines,cons);

        cons.gridx = 1;
        cons.gridy = 2;
        homePanel.add(bookTicket,cons);

        cons.gridx = 3;
        cons.gridy = 6;
        homePanel.add(contact,cons);

        cons.gridwidth = 2;

        cons.gridx = 0;
        cons.gridy = 4;
        homePanel.add(new JLabel(" "),cons);

        cons.gridx = 1;
        cons.gridy = 5;
        homePanel.add(message1,cons);

        cons.gridx = 1;
        cons.gridy = 6;
        homePanel.add(message2,cons);

        cons.gridx = 1;
        cons.gridy = 7;
        homePanel.add(close,cons);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callLogin();
            }
        });

        provider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callVerifyProvider();
            }
        });

        bookTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callBooking();
            }
        });

        contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callContact();
            }
        });

        guidelines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callGuidelines();
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callSearch();
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(homePanel);
        homePanel.setSize(700,500);
        homePanel.setVisible(true);
    }

//    public void paintComponent(Graphics g) {
//        super.paint(g);
//        g.drawImage(img, 0, 0, null);
//    }

    public void callLogin()
    {
        homePanel.setVisible(false);
        Login log = new Login(this);
    }

    public void callBooking()
    {
        FlightTicketBooking f = new FlightTicketBooking();
        f.setVisible(true);
    }

    public void callVerifyProvider()
    {
        homePanel.setVisible(false);
        VerifyProvider p = new VerifyProvider(this);
    }

    public void callSearch()
    {
        SearchFlight s = new SearchFlight();
//        this.add(s.jPanel1);
        s.setVisible(true);
    }

    public void callContact()
    {
        homePanel.setVisible(false);
        Contact co = new Contact(this);
    }

    public void callGuidelines()
    {
        homePanel.setVisible(false);
        Guidelines g = new Guidelines(this);
    }
}

//class HomePage
//{
//    public static void main(String args[])
//    {
//        HomePageStart h = new HomePageStart();
//        h.setSize(700,500);
//        h.setVisible(true);
//        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//}