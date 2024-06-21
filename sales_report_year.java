package provider.sales_report;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import base.headbar;

public class sales_report_year {
    static int passenger_cnt;
    static int tkt_cnt;
    static int fligh_cnt;
    static float sales;
    static JFrame frame;
    static String cmp_name;

    public static void report_year(JFrame frames,String cmp_names) {
        frame = frames;
        cmp_name = cmp_names;
        JPanel panel = new JPanel();
        
        panel = headbar.starter(panel);
        

        panel=daily(panel, cmp_name);
        // panel = content(panel);
        frame.setTitle("SALES REPORT");
        frame.add(panel);
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    public static void report_year(String cmp_names) {
        JFrame frame= new JFrame();
        JPanel panel = new JPanel();
        cmp_name = cmp_names;
        panel = headbar.starter(panel);
    

        panel=daily(panel, cmp_name);
        // panel = content(panel);
        frame.setTitle("SALES REPORT");
        frame.add(panel);
        frame.setSize(700,500);
    
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    static JPanel daily(JPanel panel, String cmp_name){
        buttons(panel);
        String yr_title = String.format("YEAR SALES- %s", cmp_name);
        JLabel title = new JLabel(yr_title);;
        JLabel day_lable = new JLabel("YEAR's Passenger count: ");
        JLabel no_tkts = new JLabel("Number of tickets Booked: ");
        JLabel flight_cnt = new JLabel("Number of flight run this YEAR: ");
        JLabel total_sales = new JLabel("Total Sales for YEAR: ");

        JLabel day_lable_ans = new JLabel("1000");
        JLabel no_tkts_ans = new JLabel("5000 ");
        JLabel flight_cnt_ans = new JLabel("10 ");
        JLabel total_sales_ans = new JLabel("1000000");

        Font font_upr = new Font("Arial", Font.BOLD, 16);
        Font font_lwr = new Font("Arial", Font.PLAIN, 14) ;

        day_lable.setFont(font_upr);
        no_tkts.setFont(font_upr);
        flight_cnt.setFont(font_upr);
        total_sales.setFont(font_upr);

        day_lable_ans.setFont(font_lwr);
        no_tkts_ans.setFont(font_lwr);
        flight_cnt_ans.setFont(font_lwr);
        total_sales_ans.setFont(font_lwr);
        title.setFont(font_upr);

        title.setBounds(250, 0, 1000, 100);
        
        day_lable.setBounds(150,100,500,50);
        no_tkts.setBounds(150,150,500,50);
        flight_cnt.setBounds(150,200,500,50);
        total_sales.setBounds(150,250,500,50);
        day_lable_ans.setBounds(450,100,500,50);
        no_tkts_ans.setBounds(450,150,500,50);
        flight_cnt_ans.setBounds(450,200,500,50);
        total_sales_ans.setBounds(450,250,500,50);

        panel.add(title);
        panel.add(day_lable);
        panel.add(no_tkts);
        panel.add(flight_cnt);
        panel.add(flight_cnt);
        panel.add(total_sales);
        
        // panel.add(title_ans);
        panel.add(day_lable_ans);
        panel.add(no_tkts_ans);
        panel.add(flight_cnt_ans);
        panel.add(flight_cnt_ans);
        panel.add(total_sales_ans);
        return panel;
    }
    static void connector(JPanel panel, String cmp_name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tigerfirejet",
             "root", "2077");
            Statement stmt = con.createStatement();
            
            String query = String.format("select * from year_sales where cmp_name='%s';",cmp_name);
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                
                passenger_cnt= rs.getInt(2);
                tkt_cnt= rs.getInt(3);
                fligh_cnt = rs.getInt(4);
                sales = rs.getFloat(5);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void buttons(JPanel panel){
        Color clr = Color.decode("#828282");
        JButton sr_d = new JButton("SALES REPORT - DAY");
        panel.add(sr_d);
        
        sr_d.setBounds(50, 400, 170,30);

        JButton sr_w = new JButton("SALES REPORT - MONTH");
        panel.add(sr_w);
        sr_w.setBounds(250, 400, 170,30);

        JButton sr_m = new JButton("SALES REPORT - MONTH");
        panel.add(sr_m);
        sr_m.setBounds(450, 400, 170,30);
        
        sr_d.setBackground(clr);
        sr_w.setBackground(clr);
        sr_m.setBackground(clr);

        sr_d.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                panel.removeAll();
                sales_report_day.report_day(frame,cmp_name);
            }
         });
         sr_m.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                panel.removeAll();
                sales_report_month.report_month(frame,cmp_name);
            }
         });
         sr_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                panel.removeAll();
                sales_repor_week.report_week(frame,cmp_name);
            }
         });
    }
    public static void main(String[] args) {
        report_year("DEF");
    }
}
