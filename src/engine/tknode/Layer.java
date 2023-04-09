/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.tknode;

import java.awt.*;

/**
 * @author Tdh4vn
 */
public class Layer extends Node {
    public Layer() {
        super();
    }

    @Override
    public void draw(Graphics g) {
        for (Node x : this.getVecChild()) {
            x.draw(g);
        }
    }

}
