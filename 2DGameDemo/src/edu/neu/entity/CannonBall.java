package edu.neu.entity;

import edu.neu.main.GamePanel;
import edu.neu.util.Constant;
import edu.neu.util.Direction;
import edu.neu.util.ImageResourceParser;
import edu.neu.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CannonBall extends Entity {
    private static final int MAX_LIFE = 500;
    private static final Vector2D GRAVITY = new Vector2D(1, 0);

    private boolean isAlive = false;
    private int life = 0;
    private int updateCounter = 0;



    private BufferedImage connonballImage;

    public CannonBall(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
    protected void init() {
        getImage();
        setSolidArea();
        reset();
    }

    @Override
    public void reset() {
        direction = Direction.UP;
        setAlive(false);
        life = 0;
        updateCounter = 0;
    }

    public void getImage() {
        try {
            connonballImage = ImageResourceParser.getBufferedImage(this, Constant.RES_CANNONBALL_PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setSolidArea() {
        solidArea = new Rectangle(
                5 * gamePanel.scale,
                6 * gamePanel.scale,
                6 * gamePanel.scale,
                6 * gamePanel.scale
        );
        defaultSolidArea = new Rectangle(solidArea);
    }

    @Override
    // update frequency is about 60Hz
    public void update() {

        if (isOutOfBound() || life >= MAX_LIFE) {
            // TODO
            // play some sound or something to alert the player
            reset();
            return;
        }

        if (gamePanel.collisionChecker.checkCollisionWithTiles(this)) {
            //reset();
            //TODO
            Explosion explosion = new Explosion(gamePanel, getX(), getY());
            gamePanel.explosionList.add(explosion);
            System.out.println("Hit the Tiles");
            reset();

            return;
        }

        if (gamePanel.collisionChecker.checkCollisionWithEntity(this, gamePanel.target)) {
            reset();
            gamePanel.target.destroyTarget();
            return;
        }

        // update the life of the connonball
        life++;
        updateCounter++;
        x += speed.getXComponent();
        y += speed.getYComponent();

        direction = speed.getYComponent() >= 0 ? Direction.UP : Direction.DOWN;

        // update the speed of the connonball every 1/4 seconds
        if (updateCounter >= 5) {
            // -1/2*g*(t^2)
            double life_second = (life/60.0);
            int ySpeed = (int) (0.5 * 9.8 * (life_second*life_second));
            speed.setYComponent(speed.getYComponent() + ySpeed);
            //DEBUG
            //System.out.println(speed);
            updateCounter = 0;
        }


    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(connonballImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
