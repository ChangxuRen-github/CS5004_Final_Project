package entity;

import util.Direction;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int x;
    public int y;
    public Vector2D speed;
    public Direction direction;
    public Shape solidArea;
    public boolean collisionOn = false;

    public abstract void update();

    public abstract void draw(Graphics2D graphics2D);
}
