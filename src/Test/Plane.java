/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import GameEngine.GameManager.GameManager;
import GameEngine.TKNode.Sprite;
import com.sun.glass.ui.Size;
import com.sun.javafx.geom.Vec2d;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tdh4vn
 */
public class Plane extends Sprite implements Collidable {
    ExecutorService executor = Executors.newFixedThreadPool(20);
    Map<Integer, KeyPressingEvent> keyPresses = new HashMap<>();
    private boolean fireReleased = true;

    public Plane() throws IOException {
        super();
        this.setTexture("PLANE1.png");
        this.setPosition(240, 320);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KEY IS PRESSED: " + e.getKeyCode());
                KeyPressingEvent sustainableKey = null;
                switch (e.getKeyCode()) {
                    case 37:
                    case 65:
                        sustainableKey = new KeyPressingEvent(Plane.this, -2, 0);
                        break;
                    case 38:
                    case 87:
                        sustainableKey = new KeyPressingEvent(Plane.this, 0, -2);
                        break;
                    case 39:
                    case 68:
                        sustainableKey = new KeyPressingEvent(Plane.this, +2, 0);
                        break;
                    case 40:
                    case 83:
                        sustainableKey = new KeyPressingEvent(Plane.this, 0, +2);
                        break;
                }
                if (sustainableKey != null && !keyPresses.containsKey(e.getKeyCode())) {
                    keyPresses.put(e.getKeyCode(), sustainableKey);
                    executor.submit(sustainableKey);
                }

                if (e.getKeyCode() == 32) {
                    try {
                        Plane.this.fire();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPresses.getOrDefault(e.getKeyCode(), new KeyPressingEvent()).stop = true;
                keyPresses.remove(e.getKeyCode());
                if (e.getKeyCode() == 32) {
                    Plane.this.fireReleased = true;
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    private void fire() throws IOException {
        if(fireReleased) {
            fireReleased = false;
            LayerBattle.getInstance().addChild(new Bullet((float) this.getPosition().x + (this.getContentSize().width/2), (float) this.getPosition().y, true));
        }
    }

    @Override
    public void collide(Collidable c) {
        this.blown();
    }

    private void blown() {
        System.out.println("Blown away");
    }

    @Override
    public Vec2d currentPosition() {
        return this.getPosition();
    }

    @Override
    public Size contentSize() {
        return getContentSize();
    }

    private static class KeyPressingEvent implements Runnable {
        public Plane plane;
        public boolean stop = false;

        public float dx;
        public float dy;

        public KeyPressingEvent(Plane plane, float dx, float dy) {
            this.plane = plane;
            this.dx = dx;
            this.dy = dy;
        }

        public KeyPressingEvent() {

        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    float finalX = Math.max(
                            Math.min(
                                    GameManager.getInstance().getWinSize().width - (float) plane.getContentSize().width,
                                    (float) plane.getPosition().x + dx),
                            0);
                    float finalY = Math.max(
                            Math.min(
                                    GameManager.getInstance().getWinSize().height - plane.getContentSize().height,
                                    (float) plane.getPosition().y + dy),
                            0);
                    plane.setPosition(finalX, finalY);
                    Thread.sleep(1000 / 60);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }


}
