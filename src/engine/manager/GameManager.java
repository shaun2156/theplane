/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.manager;

import com.sun.glass.ui.Size;
import engine.tknode.Scene;

import java.util.Stack;

/**
 * @author Tdh4vn
 */
public class GameManager {
    private static GameManager _sharePointer;
    private final Size winSize;
    private final String winTitle;
    private final Stack<Scene> stackScene;

    private GameManager() {
        stackScene = new Stack<>();
        winSize = new Size(480, 640);
        winTitle = "PlaneShooter";
    }

    public static GameManager getInstance() {
        if (_sharePointer == null) {
            _sharePointer = new GameManager();
        }
        return _sharePointer;
    }

    public Size getWinSize() {
        return winSize;
    }

    public String getWinTitle() {
        return winTitle;
    }

    public Scene    topScene() {
        return stackScene.lastElement();
    }

    public void popScene() {
        stackScene.pop();
    }

    public void pushScene(Scene item) {
        if (item != null) {
            stackScene.push(item);
        }
    }

}
