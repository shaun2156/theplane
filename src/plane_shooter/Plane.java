/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plane_shooter;

import com.sun.glass.ui.Size;
import com.sun.javafx.geom.Vec2d;
import engine.manager.GameManager;
import engine.tknode.Sprite;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * @author Tdh4vn
 */
public class Plane extends Sprite implements Collidable {

    int planeId;
    Random random = new Random();
    int goLeft = 0;
    int goRight = 0;
    int goUp = 0;
    int goDown = 0;
    private boolean fireReleased = true;
    private boolean destroyed = false;

    public Plane() {
        super();
        this.setTexture("PLANE1.png");
        this.setPosition(240, 320);
        planeId = random.nextInt();
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37:
                    case 65:
                        goLeft = 1;
                        break;
                    case 38:
                    case 87:
                        goUp = 1;
                        break;
                    case 39:
                    case 68:
                        goRight = 1;
                        break;
                    case 40:
                    case 83:
                        goDown = 1;
                        break;
                    case 32:
                        Plane.this.fire();
                        break;
                    default:
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37:
                    case 65:
                        goLeft = 0;
                        break;
                    case 38:
                    case 87:
                        goUp = 0;
                        break;
                    case 39:
                    case 68:
                        goRight = 0;
                        break;
                    case 40:
                    case 83:
                        goDown = 0;
                        break;
                    case 32:
                        Plane.this.fireReleased = true;
                        break;
                    default:
                }
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Plane.this.fire();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Plane.this.fireReleased = true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    private void fire() {
        if (fireReleased && !destroyed) {
            fireReleased = false;
            GameManager.getInstance().topScene().addChild(new Bullet(planeId, (float) this.getPosition().x + (this.getContentSize().width / 2f), (float) this.getPosition().y, true));
        }
    }

    @Override
    public void update(float dt) {
        float finalX = Math.max(
                Math.min(
                        (float) GameManager.getInstance().getWinSize().width - (float) this.getContentSize().width,
                        (float) this.getPosition().x + (goRight - goLeft) * 2),
                0);
        float finalY = Math.max(
                Math.min(
                        (float) GameManager.getInstance().getWinSize().height - this.getContentSize().height,
                        (float) this.getPosition().y + (goDown - goUp) * 2),
                0);
        this.setPosition(finalX, finalY);
    }

    @Override
    public void collide(Collidable c) {
        if (c instanceof Bullet) {
            Bullet b = (Bullet) c;
            if (b.sourceId == this.planeId) return;
        }
        this.blown();
    }

    private void blown() {
        destroyed = true;
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
}
