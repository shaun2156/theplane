/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine.TKNode;

import GameEngine.Windows.GameWindows;
import Test.Bullet;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author Tdh4vn
 */
public class Node {
    private Vec2d position;
    private Vec2d anchorPoint;
    private Vector<Node> vecChild;

    public Vector<Node> getVecChild() {
        return vecChild;
    }

    public Vec2d getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position = new Vec2d(
                Math.max(x, 0),
                Math.max(y, 0));
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    public Vec2d getAnchorPoint() {
        return anchorPoint;
    }

    public void setAnchorPoint(Vec2d anchorPoint) {
        this.anchorPoint = anchorPoint;
    }

    public void setAnchorPoint(float x, float y) {
        this.anchorPoint = new Vec2d(x, y);
    }

    public Node() {
        position = new Vec2d(0, 0);
        anchorPoint = new Vec2d(0.5, 0.5);
        vecChild = new Vector<Node>();

    }

    public static Node create() {
        return new Node();
    }

    public void update(float dt) {

    }

    public void draw(Graphics g) {
        for (Node x : vecChild) {
            x.draw(g);
        }
    }

    public void addChild(Node node) {
        vecChild.add(node);
    }

    public void addKeyListener(KeyListener e) {
        GameWindows.getInstance().addKeyListener(e);
    }

}
