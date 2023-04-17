import javax.swing.*;
import java.awt.*;

public class Item {
    public JRadioButton[] rb = new JRadioButton[4];
    public JButton btnConfirm;
    public JButton btnStart;
    public ButtonGroup bg;
    public final static String[] IMAGE_NAME = {"Cobblestone", "Dirt", "Grass Block", "Gravel", "Glass", "Oak Log", "Oak Plank", "Sand", "Stone", "Water", "Ancient Debris", "Basalt", "Block of Quartz", "Glowstone", "Lava", "Nether Portal", "Netherrack", "Red Nether Brick", "Soul Sand", "Warped Wart Block", "Chorus Flower", "Dragon Egg", "Dragon Head", "End Portal", "End Portal Frame", "End Rod", "End Stone", "End Stone Bricks", "Purpur Block", "Shulker Box"};
    private final static String[] TYPE_NAME = {"Overworld", "Nether", "End"};
    public int image;
    public JLabel lblImg;
    public JLabel lblNickname;
    public JPanel pnlStart;
    public JPanel pnlOption;
    public JPanel pnlConfirm;
    public JPanel pnlCounter;
    public JPanel pnlMenu;
    public JLabel win;
    public JLabel lose;
    public JTextField txtNickname;
    public final JComboBox<String> type;
    private final JFrame frm;
    private final Listener listener;
    public int counterWin = 0;
    public int counterLose = 0;
    private final RandomNumber r = new RandomNumber();
    private String difficulty = "";
    public Item(JFrame frm){
        this.frm = frm;
        bg = new ButtonGroup();
        btnConfirm = new JButton("Confirm");
        btnStart = new JButton("Start");
        type = new JComboBox<>(TYPE_NAME);
        txtNickname = new JTextField();
        pnlCounter = new JPanel(new GridLayout(1, 2));
        win = new JLabel("Right: " + counterWin);
        lose = new JLabel("Wrong: " + counterLose);
        lblNickname = new JLabel("Nickname");
        pnlOption = new JPanel(new GridLayout(4,1));
        pnlMenu = new JPanel(new GridLayout(3, 1));
        pnlConfirm = new JPanel();
        pnlStart = new JPanel(new BorderLayout());
        lblImg = new JLabel();
        listener = new Listener(this, r);
        setMenu();
    }
    public void setMenu(){
        pnlMenu.add(lblNickname);
        pnlMenu.add(txtNickname);
        pnlMenu.add(type);
        setStart();
        frm.add(pnlMenu, BorderLayout.NORTH);
        frm.add(pnlStart, BorderLayout.SOUTH);
    }
    public void setStart(){
        pnlStart.add(btnStart, BorderLayout.EAST);
        btnStart.addActionListener(listener);
    }
    public void startGame(){
        image = r.random();
        setConfirm();
        setButton();
        setImage();
        for(int i = 0; i < 4; i++)
            rb[i] = new JRadioButton();
        pnlCounter.add(win);
        pnlCounter.add(lose);
        frm.add(pnlOption, BorderLayout.WEST);
        frm.add(pnlConfirm, BorderLayout.SOUTH);
        frm.add(pnlCounter, BorderLayout.NORTH);
    }
    public void setButton(){
        int position = (int)(Math.random()*4);
        int temp;

        for(int i = 0; i < 4; i++){
            temp = r.random();
            if(temp != image && i != position){
                rb[i].setText(IMAGE_NAME[temp]);
                pnlOption.add(rb[i]);
                bg.add(rb[i]);
            }else if(i == position){
                rb[i].setText(IMAGE_NAME[image]);
                pnlOption.add(rb[i]);
                bg.add(rb[i]);
            }
        }
        r.resetExtraction();
        bg.clearSelection();
    }
    public void setConfirm(){
        pnlConfirm.add(btnConfirm);
        btnConfirm.addActionListener(listener);
    }
    public void setImageType(String m) {
        difficulty = m;
    }
    public void setImage(){
        switch (difficulty) {
            case "Overworld" -> {
                lblImg.setIcon(new ImageIcon("src/img/overworld/" + IMAGE_NAME[image] + ".png"));
                frm.add(lblImg, BorderLayout.CENTER);
            }
            case "Nether" -> {
                lblImg.setIcon(new ImageIcon("src/img/nether/" + IMAGE_NAME[image] + ".png"));
                frm.add(lblImg, BorderLayout.CENTER);
            }
            case "End" -> {
                lblImg.setIcon(new ImageIcon("src/img/end/" + IMAGE_NAME[image] + ".png"));
                frm.add(lblImg, BorderLayout.CENTER);
            }
        }
    }
    public void updateLabel(){
        win.setText("Right: " + counterWin);
        lose.setText("Wrong: " + counterLose);
    }
    static class RandomNumber{
        private int max;
        private int min;
        private boolean[] extraction;
        public RandomNumber(){
            max = 0;
            min = 0;
        }
        public int random(){
            int r = (int)(Math.random()*(max - min) + min);
            boolean canDo = canDoExtraction();
            while(extraction[r] && canDo)
                r = (int)(Math.random()*(max - min) + min);
            extraction[r] = true;
            return r;
        }
        public void resetExtraction(){
            for(int i = 0; i < max - min; i++)
                extraction[i] = false;
        }
        public boolean canDoExtraction(){
            boolean canDo = false;
            for(int i = 0; i < max; i++) {
                if (!extraction[i]) {
                    canDo = true;
                    break;
                }
            }
            return canDo;
        }
        public void setMax(int max) {
            this.max = max;
        }
        public void setMin(int min) {
            this.min = min;
        }
        public void setExtraction() {
            extraction = new boolean[max - min];
            for(int i = 0; i < max - min; i++)
                extraction[i] = false;
        }
    }

}