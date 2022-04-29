package edu.neu.entity;

import edu.neu.main.GamePanel;
import edu.neu.util.Constant;
import edu.neu.util.Direction;
import edu.neu.util.ImageResourceParser;
import edu.neu.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 *
 */

public class Target extends Entity {
    private static final int TARGET_POSITION_TOP_BOUND = 8;
    private static final int TARGET_POSITION_LEFT_BOUND = 13;
    private static final Random random = new Random();
    private boolean isExploded = false;
    private BufferedImage target, targetExploded;

    public Target(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
    protected void init() {
        direction = Direction.UP;
        speed = new Vector2D(0,0);
        getImage();
        setSolidArea();
        reset();
    }

    @Override
    public void reset() {
        setExploded(false);
        setRandomPosition();
    }

    private void setSolidArea() {
        solidArea = new Rectangle(
                0,
                0,
                gamePanel.tileSize,
                gamePanel.tileSize
        );
        defaultSolidArea = new Rectangle(solidArea);
    }

    private void setRandomPosition() {
        this.x = (random.nextInt(gamePanel.maxScreenCol - TARGET_POSITION_LEFT_BOUND - 2)
                + TARGET_POSITION_LEFT_BOUND) * gamePanel.tileSize;
        this.y = (random.nextInt(gamePanel.maxScreenRow - TARGET_POSITION_TOP_BOUND - 2)
                + TARGET_POSITION_TOP_BOUND) * gamePanel.tileSize;
    }

    private void getImage() {

        try {
            target = ImageResourceParser.getBufferedImage(this, Constant.RES_TARGET_PNG);
            targetExploded = ImageResourceParser.getBufferedImage(this, Constant.RES_TARGET_EXPLODED_PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = isExploded ? targetExploded : target;
        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public void destroyTarget() {
        setExploded(true);
    }
}
