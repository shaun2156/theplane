package plane_shooter;

import com.sun.glass.ui.Size;
import com.sun.javafx.geom.Vec2d;

public interface Collidable {
    void collide(Collidable c);

    Vec2d currentPosition();

    Size contentSize();

    default Vec2d centerPosition() {
        return new Vec2d(currentPosition().x + ((double) contentSize().width / 2), currentPosition().y + ((double) contentSize().height / 2));
    }

    default boolean canCollide(Collidable c) {
        return Math.abs(this.centerPosition().x - c.centerPosition().x) < ((double) this.contentSize().width / 2 + (double) c.contentSize().width / 2 + 2)
                && Math.abs(this.centerPosition().y - c.centerPosition().y) < ((double) this.contentSize().height / 2 + (double) c.contentSize().height / 2 + 2);
    }
}
