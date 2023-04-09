/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plane_shooter;

import engine.tknode.Layer;
import engine.tknode.Node;
import engine.tknode.Sprite;

import java.io.IOException;

/**
 *
 * @author Tdh4vn
 */
public class LayerBattle extends Layer {
    public LayerBattle() throws IOException{
        super();
        Sprite background = Sprite.create("Background.png");
        this.addChild(background);
        Plane su = new Plane();
        this.addChild(su);
    }
    @Override
    public void update(float dt) {
        super.update(dt);
        processCollision();
    }

    public void processCollision() {
        for (int i = 0; i < this.getVecChild().size(); i++) {
            if(this.getVecChild().get(i) instanceof Collidable) {
                Collidable colliableA = (Collidable) this.getVecChild().get(i);
                for (int j = i + 1; j < this.getVecChild().size(); j++) {
                    if(this.getVecChild().get(j) instanceof Collidable) {
                        Collidable colliableB = (Collidable) this.getVecChild().get(j);
                        if(colliableA.canCollide(colliableB)) {
                            colliableA.collide(colliableB);
                            colliableB.collide(colliableA);
                        }
                    }
                }
            }
        }
    }

}
