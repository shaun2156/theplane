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
public class Bullet extends Sprite implements Collidable {
    boolean currentPlayer;
    public Bullet(float x, float y, boolean currentPlayer) throws IOException {
        super();
        this.setTexture("DAN.png");
        this.currentPlayer = currentPlayer;
        float finalX = x - this.getContentSize().width / 2;
        this.setPosition(finalX, y + this.getContentSize().height * (currentPlayer ? -1 : 1));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        float finalY = Math.max(
                                Math.min(
                                        GameManager.getInstance().getWinSize().height - (float) Bullet.this.getContentSize().height,
                                        (float)  Bullet.this.getPosition().y + (currentPlayer ? -10 : 10)),
                                0);
                        if(Bullet.this.getPosition().y == finalY) {
                            LayerBattle.getInstance().removeChild(Bullet.this);
                            return;
                        }
                        Bullet.this.setPosition(finalX, finalY);
                        Thread.sleep(1000 / 60);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }).start();

    }

    @Override
    public void collide(Collidable c) {
        if(c instanceof Bullet) {
            Bullet otherBullet = (Bullet) c;
            if(otherBullet.currentPlayer == ((Bullet) c).currentPlayer) return;
        }
        LayerBattle.getInstance().removeChild(Bullet.this);
    }

    @Override
    public Vec2d currentPosition() {
        return getPosition();
    }

    @Override
    public Size contentSize() {
        return getContentSize();
    }
}
