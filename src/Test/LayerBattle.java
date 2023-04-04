/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import GameEngine.TKNode.Layer;
import GameEngine.TKNode.Sprite;
import java.io.IOException;

/**
 *
 * @author Tdh4vn
 */
public class LayerBattle extends Layer{
    public LayerBattle() throws IOException{
        super();
        Sprite background = Sprite.create("Background.png");
        this.addChild(background);
        Plane su = new Plane();
        this.addChild(su);
    }
    
}
