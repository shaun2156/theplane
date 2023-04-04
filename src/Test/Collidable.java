package Test;

import com.sun.javafx.geom.Vec2d;
import com.sun.glass.ui.Size;

public interface Collidable {
    void collide(Collidable c);

    Vec2d currentPosition();

    Size contentSize();

    default Vec2d centerPosition() {
        return new Vec2d(currentPosition().x + (contentSize().width / 2), currentPosition().y + (contentSize().height / 2));
    }

    default boolean canCollide(Collidable c) {
        return Math.abs(this.centerPosition().x - c.centerPosition().x) < (this.contentSize().width/2 + c.contentSize().width/2)
                && Math.abs(this.centerPosition().y - c.centerPosition().y) < (this.contentSize().height/2 + c.contentSize().height/2);
    }
}
