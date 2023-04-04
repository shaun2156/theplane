/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine.TKNode;

import com.sun.glass.ui.Size;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Tdh4vn
 */
public class Sprite extends Node{
    private BufferedImage texture;
    private Size contentSize;

    public void setTexture(String img) {
        try {
            texture = ImageIO.read(new File("Resouces/" + img));
            contentSize = new Size(texture.getWidth(), texture.getHeight());
        } catch (Exception e) {
            texture = null;
        }
    }

    public Size getContentSize() {
        return contentSize;
    }
    public Sprite(){
        super();
    }
    public Sprite(String dirImg) throws IOException{
        super();
        try {
            texture = ImageIO.read(new File("Resouces/" + dirImg));
        } catch (Exception e) {
            texture = null;
        }
        
    }
    
    public static Sprite create(String dirImg) throws IOException{
        Sprite _sharePointer = new Sprite(dirImg);
        _sharePointer.contentSize = new Size(_sharePointer.texture.getWidth()
                , _sharePointer.texture.getHeight());
        return _sharePointer;
    }
    
    @Override
    public void draw(Graphics g){
        if(texture != null){
            g.drawImage(texture, (int)this.getPosition().x, (int)this.getPosition().y, null);
        }
        for(Node x: this.getVecChild()){
            x.draw(g);
        }
    }
}
