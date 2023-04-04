/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine.GameManager;

import GameEngine.TKNode.Scene;
import Test.LayerBattle;
import java.io.IOException;

/**
 *
 * @author Tdh4vn
 */
public class GameRunner {
    private static GameRunner _sharePointer;
    public GameRunner(){
        
    }
    public void init() throws IOException{
        LayerBattle layer1 = new LayerBattle();
        GameManager.getInstance().topScene().addChild(layer1);
    }
    public static GameRunner getInstance(){
        if(_sharePointer == null){
            _sharePointer = new GameRunner();
        }
        return _sharePointer;
    }
}
