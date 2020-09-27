package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame() {
        setContentPane(new GameBoard());
        setTitle("Google T-Rex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
    }
}
