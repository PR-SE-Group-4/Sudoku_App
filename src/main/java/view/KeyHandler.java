package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("GOOOOO");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("GOOOOO");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("GOOOOO");
    }
}
