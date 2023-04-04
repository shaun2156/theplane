/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import GameEngine.GameManager.GameManager;
import GameEngine.TKNode.Sprite;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Tdh4vn
 */
public class Plane extends Sprite{
    ExecutorService executor = Executors.newFixedThreadPool(20);
    Map<Integer, KeyPressingEvent> keyPresses = new HashMap<>();
    public Plane() throws IOException{
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
                if (keyPresses.get(e.getKeyCode()) == null) {
                    switch (e.getKeyCode()) {
                        case 37:
                        case 65:
                            keyPresses.put(e.getKeyCode(), new KeyPressingEvent(Plane.this, -2, 0));
                            break;
                        case 38:
                        case 87:
                            keyPresses.put(e.getKeyCode(), new KeyPressingEvent(Plane.this, 0, -2));
                            break;
                        case 39:
                        case 68:
                            keyPresses.put(e.getKeyCode(), new KeyPressingEvent(Plane.this, +2, 0));
                            break;
                        case 40:
                        case 83:
                            keyPresses.put(e.getKeyCode(), new KeyPressingEvent(Plane.this, 0, +2));
                            break;
                        default:
                            return;
                    }
                    executor.submit(keyPresses.get(e.getKeyCode()));
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPresses.getOrDefault(e.getKeyCode(), new KeyPressingEvent()).stop = true;
                keyPresses.remove(e.getKeyCode());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
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
            while(!stop) {
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
                    Thread.sleep(1000/60);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
    

}
