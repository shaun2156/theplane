/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.windows;

import plane_shooter.LayerBattle;
import engine.manager.GameManager;

import java.io.IOException;

/**
 * @author Tdh4vn
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GameWindows gameWindows = GameWindows.create();
        LayerBattle layer1 = new LayerBattle();
        GameManager.getInstance().topScene().addChild(layer1);
        gameWindows.start();
    }
}
