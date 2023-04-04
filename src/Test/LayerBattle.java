/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import GameEngine.TKNode.Layer;
import GameEngine.TKNode.Node;
import GameEngine.TKNode.Sprite;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Tdh4vn
 */
public class LayerBattle extends Layer{
    private static LayerBattle _mInstance;
    public LayerBattle() throws IOException{
        super();
        Sprite background = Sprite.create("Background.png");
        this.addChild(background);
        Plane su = new Plane();
        this.addChild(su);
        this._mInstance = this;
    }

    public static LayerBattle getInstance() {
        return _mInstance;
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

    Vector<Node> toRemove = new Vector<>();
    @Override
    public void draw(Graphics g){
        processCollision();
        for(Node x: this.getVecChild()){
            x.draw(g);
        }
        this.getVecChild().removeAll(toRemove);
        toRemove.clear();
    }
    public void removeChild(Node node) {
        toRemove.add(node);

    }
}
