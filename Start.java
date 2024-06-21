import javax.swing.JFrame;
public class Start {
    public static void main(String[] args) {
        HomePage obj = new HomePage();
        obj.setTitle("TigerFirejet");
        obj.setSize(700,500);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
