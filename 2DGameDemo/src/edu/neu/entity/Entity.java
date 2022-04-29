package edu.neu.entity;

import edu.neu.main.GamePanel;
import edu.neu.util.Direction;
import edu.neu.util.Vector2D;

import java.awt.*;

public abstract class Entity {
    protected final GamePanel gamePanel;
    protected int x;
    protected int y;
    protected Vector2D speed;
    protected Direction direction;
    protected Rectangle solidArea;
    protected Rectangle defaultSolidArea;
    protected boolean collisionWithTileOn = false;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    protected abstract void init();

    public abstract void reset();

    public abstract void update();

    public abstract void draw(Graphics2D graphics2D);

    protected boolean isOutOfBound() {
        return x < 0 || x > gamePanel.screenWidth || y < 0 || y > gamePanel.screenHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2D getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2D speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isCollisionWithTileOn() {
        return collisionWithTileOn;
    }

    public void setCollisionWithTileOn(boolean collisionWithTileOn) {
        this.collisionWithTileOn = collisionWithTileOn;
    }

    public void restoreDefaultSolidArea() {
        solidArea.x = defaultSolidArea.x;
        solidArea.y = defaultSolidArea.y;
    }
}
