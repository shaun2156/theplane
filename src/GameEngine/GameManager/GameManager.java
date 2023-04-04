/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine.GameManager;

import GameEngine.TKNode.Scene;
import com.sun.glass.ui.Size;
import com.sun.javafx.geom.Vec2d;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author Tdh4vn
 */
public class GameManager {
    private Size winSize;
    private String winTitle;
    private Stack<Scene> stackScene;
    public Size getWinSize() {
        return winSize;
    }

    public String getWinTitle() {
        return winTitle;
    }
    private static GameManager _sharePointer;
    
    private GameManager(){
        stackScene = new Stack<Scene>();
        winSize = new Size(480, 640);
        winTitle = new String("TechKids");
        
    }
    
    public static GameManager getInstance(){
        if(_sharePointer == null){
            _sharePointer = new GameManager();
            return _sharePointer;
        } else {
            return _sharePointer;
        }
    }
    
    public Scene topScene(){
        return stackScene.lastElement();
    }
    public void popScene(){
        stackScene.pop();
    }
    public void pushScene(Scene item){
        if(item != null){
            stackScene.push(item);
        }
    }

}
