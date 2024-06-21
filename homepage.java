package provider;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import base.headbar;

public class homepage {
    public static String company_name;
    static int trip_count;
    static float revenue_data;
    public static JFrame frame;
    public static void home_page(String cpy_name) {
        company_name = cpy_name;
        frame= new JFrame();
        JPanel panel = new JPanel();
        
        
        panel = headbar.starter(panel);
    
        connector(cpy_name);
        panel=title(panel);
        panel = content(panel);
        frame.add(panel);
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    public static void home_page(JFrame frame,String cpy_name) {
        JPanel panel = new JPanel();
        
        company_name = cpy_name;
        panel = headbar.starter(panel);
    
        connector(cpy_name);
        panel=title(panel);
        panel = content(panel);
        frame.add(panel);
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static JPanel title(JPanel panel){
        JLabel jl = new JLabel("Welcome User "+company_name);
    
        jl.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(jl, BorderLayout.CENTER.toString());
        jl.setBounds(250, 0, 1000, 100);
        return panel;
    }
    static JPanel content(JPanel panel){
        
        JLabel cpy_name = new JLabel("Company Name: ");
        JLabel trips = new JLabel("Number of trips made: ");
        JLabel revenue = new JLabel("Revenue Generated: ");

        JLabel cpy_name_ans = new JLabel(company_name);
        JLabel trips_ans = new JLabel(Integer.toString(trip_count));
        JLabel revenue_ans = new JLabel(Float.toString(revenue_data));
        cpy_name.setFont(new Font("Arial", Font.BOLD, 16));
        trips.setFont(new Font("Arial", Font.BOLD, 16));
        revenue.setFont(new Font("Arial", Font.BOLD, 16));
        cpy_name_ans.setFont(new Font("Arial", Font.PLAIN, 16));
        trips_ans.setFont(new Font("Arial", Font.PLAIN, 16));
        revenue_ans.setFont(new Font("Arial", Font.PLAIN, 16));
        
        cpy_name.setBounds(150,100,500,50);
        trips.setBounds(150,200,500,50);
        revenue.setBounds(150,300,500,50);
        cpy_name_ans.setBounds(450,100,500,50);
        trips_ans.setBounds(450,200,500,50);
        revenue_ans.setBounds(450,300,500,50);

        panel.add(cpy_name, BorderLayout.CENTER);
        panel.add(trips, BorderLayout.CENTER);
        panel.add(revenue, BorderLayout.CENTER);

        panel.add(cpy_name_ans, BorderLayout.CENTER);
        panel.add(trips_ans, BorderLayout.CENTER);
        panel.add(revenue_ans, BorderLayout.CENTER);

        return panel;
    }
    
    static void connector(String cmp_name){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet", "root", "2077");
            Statement stmt = con.createStatement();
            
            String query = String.format("select * from homepage where cmp_name='%s';",cmp_name);
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                company_name = rs.getString(1);
                trip_count= rs.getInt(2);
                revenue_data= rs.getFloat(3);
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        home_page("ABC");
    }
}  