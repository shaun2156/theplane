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

/**
 * @author Tdh4vn
 */
public class Bullet extends Sprite implements Collidable {
    boolean verticalDirection;

    int sourceId;

    public Bullet(int sourceId, float x, float y, boolean verticalDirection) {
        super();
        this.setTexture("BULLET.png");
        this.verticalDirection = verticalDirection;
        this.sourceId = sourceId;
        float finalX = x - (float) this.getContentSize().width / 2;
        this.setPosition(finalX, y + this.getContentSize().height * (verticalDirection ? -1 : 1));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        float finalY = Math.max(
                                Math.min(
                                        GameManager.getInstance().getWinSize().height - (float) Bullet.this.getContentSize().height,
                                        (float) Bullet.this.getPosition().y + (verticalDirection ? -10 : 10)),
                                0);
                        if (Bullet.this.getPosition().y == finalY) {
                            GameManager.getInstance().topScene().removeChild(Bullet.this);
                            return;
                        }
                        Bullet.this.setPosition(finalX, finalY);
                        Thread.sleep(1000 / 60);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }).start();

    }

    @Override
    public void collide(Collidable c) {
        if (c instanceof Bullet) {
            Bullet otherBullet = (Bullet) c;
            if (otherBullet.sourceId == this.sourceId) return;
        }
        if (c instanceof Plane) {
            Plane plane = (Plane) c;
            if (plane.planeId == this.sourceId) return;
        }
        GameManager.getInstance().topScene().removeChild(Bullet.this);
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
