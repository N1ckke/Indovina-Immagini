import javax.swing.*;
import java.awt.*;

public class Interface {
    Item item;
    JFrame frm;
    public Interface(){
        frm = new JFrame();
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frm.setLayout(new BorderLayout());
        item = new Item(frm);
        frm.setSize(800,800);
        frm.setVisible(true);
    }
}
