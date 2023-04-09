/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.windows;

import engine.manager.GameManager;
import engine.tknode.Scene;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.IOException;

/**
 * @author Tdh4vn
 */
public class GameWindows extends Frame implements Runnable {

    private static GameWindows _sharePointer;
    private Image image;
    private Graphics second;

    private GameWindows() {
        super();
        this.setTitle(GameManager.getInstance().getWinTitle());
        this.setFocusable(true);
        this.setSize(GameManager.getInstance().getWinSize().width, GameManager.getInstance().getWinSize().height);
        this.setVisible(true);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });


    }

    public static GameWindows getInstance() {
        return _sharePointer;
    }

    public static GameWindows create() throws IOException {
        Scene scene = new Scene();
        GameManager.getInstance().pushScene(scene);
        _sharePointer = new GameWindows();

        return _sharePointer;
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        GameManager.getInstance().topScene().draw(g);
    }

    @Override
    public void run() {
        while (true) {
            GameManager.getInstance().topScene().update(1 / 60);
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        Thread mainThread = new Thread(this);
        mainThread.start();
    }
}
