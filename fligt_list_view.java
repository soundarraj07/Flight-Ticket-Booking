package provider;
import java.awt.Color;
import base.headbar;

import javax.swing.*;
public class fligt_list_view {
    public static void flight_list(JFrame frame){
        JLabel label = new JLabel("FLIGHT LIST VIEW IS CURRENTLY UNAVAIABLE RIGHT NOW- WILL BE AVAIABLE IN NEXT UPDATE");
        JLabel sry = new JLabel("SORRY FOR INCONVENIENCE");
        JPanel panel = new JPanel();
        panel = headbar.starter(panel);
        
        label.setBounds(100, 100, 1000, 100);
        sry.setBounds(100, 200, 1000, 100);
        panel.add(label);
        panel.add(sry);
        frame.add(panel);
        panel.setBackground(Color.WHITE);
        frame.setSize(700,500);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        flight_list(frame);
    }
}
