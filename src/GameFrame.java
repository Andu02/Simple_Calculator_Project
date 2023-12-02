import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GameFrame(){

        new JFrame();
        this.setSize(500, 500);
        this.add(new GamePanel());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TicTacToe");
        this.setFocusable(true);
        this.setVisible(true);
    }

}
