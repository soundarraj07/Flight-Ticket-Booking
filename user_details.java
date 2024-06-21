package provider.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import base.headbar;

public class user_details {
    static String name = null;
    static int num= 0;
    static String designation= null;
    static float Salary= 0f; 
    public static JFrame frame;

    static void details_user(String cmp_name) {
        frame = new JFrame();
        JPanel panel = new JPanel();
        
        connector(panel, cmp_name);
        panel = headbar.starter(panel);
        buttons(panel);
        panel = title(panel, cmp_name);
        panel = content(panel);
        
        
        frame.add(panel);
        frame.setTitle("USER DETAILS");
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void details_user(JFrame frames,String cmp_name) {
        frame = frames;
        JPanel panel = new JPanel();
        
        connector(panel, cmp_name);
        panel = headbar.starter(panel);
        panel = title(panel, cmp_name);
        panel = content(panel);
        buttons(panel);
        frame.add(panel);
        frame.setTitle("USER DETAILS");
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static JPanel title(JPanel panel, String cmp_name){
        JLabel title = new JLabel("USER DETAILS for "+cmp_name);

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(250, 50, 500, 50);
        panel.add(title);
        return panel;
    }   
    static JPanel content(JPanel panel){
        
        JLabel admin_name = new JLabel("ADMIN NAME: ");
        JLabel admin_num = new JLabel("ADMIN PHONE NUMBER: ");
        JLabel admin_desig = new JLabel("ADMIN DESIGNATION: ");
        JLabel admin_sal = new JLabel("ADMIN SALARY:");

        JLabel admin_name_ans = new JLabel(name);
        JLabel admin_num_ans = new JLabel(Integer.toString(num));
        JLabel admin_desig_ans = new JLabel(designation);
        JLabel admin_sal_ans = new JLabel(Float.toString(Salary));

        
        Font font_upr = new Font("Arial", Font.BOLD, 16);
        Font font_lwr = new Font("Arial", Font.PLAIN, 14) ;

        panel.add(admin_name);
        panel.add(admin_num);
        panel.add(admin_desig);
        panel.add(admin_sal);
        panel.add(admin_name_ans);
        panel.add(admin_num_ans);
        panel.add(admin_desig_ans);       
        panel.add(admin_sal_ans);

        admin_name.setFont(font_upr);
        admin_num.setFont(font_upr);
        admin_desig.setFont(font_upr);
        admin_sal.setFont(font_upr);

        admin_name_ans.setFont(font_lwr);
        admin_num_ans.setFont(font_lwr);
        admin_desig_ans.setFont(font_lwr);
        admin_sal_ans.setFont(font_lwr);

        admin_name.setBounds(150,100,500,50);
        admin_desig.setBounds(150,150,500,50);
        admin_num.setBounds(150,200,500,50);
        admin_sal.setBounds(150,250,500,50);

        admin_name_ans.setBounds(450,100,500,50);
        admin_desig_ans.setBounds(450,150,500,50);
        admin_num_ans.setBounds(450,200,500,50);
        admin_sal_ans.setBounds(450,250,500,50);
        return panel;
    }
    static void connector(JPanel panel, String cmp_name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet",
             "root", "2077");
            Statement stmt = con.createStatement();
            
            String query = String.format("select * from admin_ where cmp_name='%s';",cmp_name);
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                name = rs.getString(1);
                num = rs.getInt(2);
                designation = rs.getString(3);
                Salary = rs.getFloat(4);   
            }
                        
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    static void buttons(JPanel panel){
        Color clr = Color.decode("#828282");
        JButton cmp_profile = new JButton("COMPANY PROFILE");
        panel.add(cmp_profile);
        cmp_profile.setBounds(250, 400, 150,30);
        cmp_profile.setBackground(clr);

        cmp_profile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                panel.removeAll();
                company_profile.profile_cmp(frame, "DEF");
            }
        });
    }  
}