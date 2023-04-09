/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.manager;

/**
 * @author Tdh4vn
 */
public class GameRunner {
    private static GameRunner _sharePointer;

    public static GameRunner getInstance() {
        if (_sharePointer == null) {
            _sharePointer = new GameRunner();
        }
        return _sharePointer;
    }
}
