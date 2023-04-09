/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.tknode;

import java.awt.*;
import java.util.Vector;

/**
 * @author Tdh4vn
 */
public class Scene extends Node {
    Vector<Node> toRemove = new Vector<>();

    public Scene() {
        super();
    }

    public static Scene create() {
        return new Scene();
    }

    @Override
    public void draw(Graphics g) {
        for (Node x : this.getVecChild()) {
            x.draw(g);
        }
        this.getVecChild().removeAll(toRemove);
        toRemove.clear();
    }

    public void removeChild(Node node) {
        toRemove.add(node);
    }
}
