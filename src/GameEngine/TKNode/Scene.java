/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine.TKNode;

import java.awt.Graphics;

/**
 *
 * @author Tdh4vn
 */
public class Scene extends Node{
    public Scene(){
        super();
    }
    @Override
    public void draw(Graphics g){
        for(Node x : this.getVecChild()){
            x.draw(g);
        }
    }
    public static Scene create(){
        return new Scene();
    }
    
    
}
