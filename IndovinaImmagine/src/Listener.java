import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {
    Item item;
    Item.RandomNumber r;
    String name;
    public Listener(Item item, Item.RandomNumber r){
        this.item = item;
        this.r = r;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        switch (item.type.getName()) {
            case "Overworld" -> {
                r.setMin(0);
                r.setMax(10);
                r.setExtraction();
                item.setImageType("Overworld");
                item.startGame();
            }
            case "Nether" -> {
                r.setMin(10);
                r.setMax(20);
                r.setExtraction();
                item.setImageType("Nether");
                item.startGame();

            }
            case "End" -> {
                r.setMin(20);
                r.setMax(30);
                r.setExtraction();
                item.setImageType("End");
                item.startGame();
            }
            default -> {
                item.updateLabel();
                item.setButton();
                item.setImage();
                item.startGame();
            }
        }

        for(int i = 0; i < 4; i++) {
            if (item.rb[i].isSelected()) {
                if (item.rb[i].getText().equals(Item.IMAGE_NAME[item.image])) {
                    JOptionPane.showMessageDialog(null, "You guessed", "", JOptionPane.INFORMATION_MESSAGE);
                    item.counterWin++;
                } else {
                    JOptionPane.showMessageDialog(null, "You are wrong", "", JOptionPane.ERROR_MESSAGE);
                    item.counterLose++;
                }
            }
        }

        if((item.counterLose + item.counterWin) == 10) {
            FFile.write(name + ";" + item.win + ";" + item.lose, "src/Players.txt");
            item.setMenu();
        }
    }
}