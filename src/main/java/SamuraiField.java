import javax.swing.*;
import java.awt.*;



public class SamuraiField extends NinesquareField {

    NinesquareField ninesquare1;
    NinesquareField ninesquare2;
    NinesquareField ninesquare3;
    NinesquareField ninesquare4;
    NinesquareField ninesquare5;

    JPanel panel;

    public SamuraiField() {
        panel = new JPanel();
        ninesquare1 = new NinesquareField();
        panel.add(ninesquare1);

        panel.setSize(270,270);
        panel.setLayout(null);


    //this.addMouseListener(new NinesquareField.SudokuPanelMouseAdapter());


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));

        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        graphics.setColor(new Color(0.0f, 0.0f, 0.0f));

        createComponent(graphics);

    }
}
