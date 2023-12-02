import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private JLabel status = new JLabel("STATUS: Playing");
    private JButton[] buttons = new JButton[10];

    private ArrayList<Integer> playerPositions = new ArrayList<>();

    private ArrayList<Integer> cpuPositions = new ArrayList<>();

    private Random rand = new Random();

    private final String BUTTON_FONT = "Courier";
    private final Color BUTTON_BACKGROUND = Color.white;

    GamePanel() {

        new JPanel();
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(new GridLayout(4, 3));
        this.setBackground(new Color(32, 32, 32));

        for (int i = 1; i <= 9; i++) {
            this.add(buttons[i] = new JButton(""));
            buttons[i].setFont(new Font(BUTTON_FONT, Font.BOLD, 40));
            buttons[i].setBackground(BUTTON_BACKGROUND);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            buttons[i].addActionListener(this);
        }

        status.setForeground(Color.white);
        this.add(status);

    }

    public void turn(ActionEvent e, String user) {


        if (user.equals("player")) {
            for (int i = 1; i <= 9; i++) {
                if (e.getSource() == buttons[i] && buttons[i].getText().isEmpty()) {
                    buttons[i].setText("X");
                    buttons[i].setEnabled(false);
                    playerPositions.add(i);
                }
            }
        } else {
            int i = rand.nextInt(1, 10);
            while (!buttons[i].getText().isEmpty()) {
                i = rand.nextInt(1, 10);
            }
            buttons[i].setText("O");
            buttons[i].setEnabled(false);
            cpuPositions.add(i);
        }
    }

    public void color(List<Integer> l, Color color) {

        for (int pos : l)
            buttons[pos].setBackground(color);
    }

    public boolean checkWinner() {

        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> topCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> botCol = Arrays.asList(3, 6, 9);
        List<Integer> firstDiag = Arrays.asList(1, 5, 9);
        List<Integer> secondDiag = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(topCol);
        winning.add(midCol);
        winning.add(botCol);
        winning.add(firstDiag);
        winning.add(secondDiag);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                System.out.println("Caca");
                status.setText("STATUS: You win!");
                color(l, Color.green);
                return true;
            } else if (cpuPositions.containsAll(l)) {
                status.setText("STATUS: You lose!");
                color(l, Color.red);
                return true;
            } else if (cpuPositions.size() + playerPositions.size() == 9) {
                status.setText("STATUS: Tie!");
                return true;
            }
        }

        return false;
    }

    public void disableAllButtons() {

        for (int i = 1; i <= 9; i++) {
            buttons[i].setEnabled(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        turn(e, "player");

        if (checkWinner()) {
            disableAllButtons();
        }
        else {
            turn(e, "cpu");
            if (checkWinner()) {
                disableAllButtons();
            }
        }
    }
}

