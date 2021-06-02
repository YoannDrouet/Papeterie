package ihm;

import javax.swing.*;

public class PapeterieApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    JFrame app = new GUI();
                }
        );
    }
}
