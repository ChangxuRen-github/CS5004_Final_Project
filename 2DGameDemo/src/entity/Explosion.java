package entity;

import main.GamePanel;
import util.Constant;
import util.Direction;
import util.ImageResourceParser;

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
            explosion = ImageResourceParser.getBufferedImage(Constant.RES_TARGET_EXPLODED_PNG);
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
