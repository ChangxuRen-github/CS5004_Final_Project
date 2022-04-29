package edu.neu.entity;

import edu.neu.main.GamePanel;
import edu.neu.util.Constant;
import edu.neu.util.ImageResourceParser;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion extends Entity {

    private BufferedImage explosion;
    private int x;
    private int y;

    public Explosion(GamePanel gamePanel, int x, int y) {
        super(gamePanel);
        this.x = x;
        this.y = y;
    }


    @Override
    protected void init() {
        getImage();
        reset();
    }

    public BufferedImage getImage() {
        try {
            explosion = ImageResourceParser.getBufferedImage(this, Constant.RES_TARGET_EXPLODED_PNG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return explosion;
    }

    @Override
    public void reset() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(explosion, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
