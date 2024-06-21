package provider.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import base.headbar;

public class company_profile {
    static String name, address;
    static int flight_cnt;
    static float revenue;
    static JFrame frame;
    public static void profile_cmp(String cmp_name) {
        frame = new JFrame();
        JPanel panel = new JPanel();

        panel = headbar.starter(panel);
        panel = title(panel);
        connector(panel, cmp_name);
        panel = content(panel);
        buttons(panel);
        
        frame.add(panel);
        frame.setTitle("COMPANY PROFILE");
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void profile_cmp(JFrame frames, String cmp_name) {
        frame = frames;
        JPanel panel = new JPanel();
        buttons(panel);
        panel = headbar.starter(panel);
        panel = title(panel);
        connector(panel, cmp_name);
        panel = content(panel);
        

        frame.add(panel);
        frame.setTitle("COMPANY PROFILE");
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static JPanel title(JPanel panel){
        JLabel title = new JLabel("COMPANY PROFILE");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(250, 50, 500, 50);
        panel.add(title);
        return panel;
    }
    static JPanel content(JPanel panel){
        JLabel cmp = new JLabel("COMPANY NAME: ");
        JLabel add = new JLabel("COMPANY ADDRESS: ");
        JLabel net_revenue = new JLabel("TOTAL REVENUE GENERATED: ");
        JLabel flight = new JLabel("NO OF FLIGHTS MANAGED   :");

        JLabel cmp_ans = new JLabel(name);
        JLabel add_ans = new JLabel(address);
        JLabel net_revenue_ans = new JLabel(Float.toString(revenue));
        JLabel flight_ans = new JLabel(Integer.toString(flight_cnt));

        
        Font font_upr = new Font("Arial", Font.BOLD, 16);
        Font font_lwr = new Font("Arial", Font.PLAIN, 14);

        cmp.setBounds(150,100,500,50);
        add.setBounds(150,150,500,50);
        net_revenue.setBounds(150,200,500,50);
        flight.setBounds(150,250,500,50);

        cmp_ans.setBounds(450,100,500,50);
        add_ans.setBounds(450,150,500,50);
        net_revenue_ans.setBounds(450,200,500,50);
        flight_ans.setBounds(450,250,500,50);

        panel.add(cmp);
        panel.add(add);
        panel.add(net_revenue);
        panel.add(flight);
        panel.add(cmp_ans);
        panel.add(add_ans);
        panel.add(net_revenue_ans);       
        panel.add(flight_ans);

        cmp.setFont(font_upr);
        add.setFont(font_upr);
        flight.setFont(font_upr);
        net_revenue.setFont(font_upr);

        cmp_ans.setFont(font_lwr);
        add_ans.setFont(font_lwr);
        flight_ans.setFont(font_lwr);
        net_revenue_ans.setFont(font_lwr);
        return panel;
    }
    static void connector(JPanel panel, String cmp_name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet",
             "root", "2077");
            Statement stmt = con.createStatement();
            
            String query = String.format("select * from cmp_profile where admin_name='%s';",cmp_name);
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                name = rs.getString(1);
                address = rs.getString(2);
                flight_cnt = rs.getInt(4);
                revenue = rs.getFloat(3);
            }
            
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    static void buttons(JPanel panel){
        Color clr = Color.decode("#828282");
        
        JButton user_profile = new JButton("USER PROFILE");
        panel.add(user_profile);
        user_profile.setBounds(250, 400, 150,30);
        user_profile.setBackground(clr);
        // user_profile.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent ae){
        //         panel.removeAll();
        //         company_profile.profile_cmp(frame, "DEF");
        //     }
        // });
       user_profile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae){
            panel.removeAll();
            user_details.details_user(frame, "ABC");
        }
       }); 
    }  
}
