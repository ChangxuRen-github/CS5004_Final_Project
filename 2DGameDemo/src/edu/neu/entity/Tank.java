package edu.neu.entity;

import edu.neu.controller.TankAngle;
import edu.neu.controller.TankPower;
import edu.neu.main.GamePanel;
import edu.neu.main.KeyHandler;
import edu.neu.util.Constant;
import edu.neu.util.Direction;
import edu.neu.util.ImageResourceParser;
import edu.neu.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tank extends Entity {
    private KeyHandler keyHandler;
    private final CannonBall cannonBall = new CannonBall(gamePanel);

    private int gunAngle = TankAngle.INI_ANGLE;
    private int gunPower = TankPower.INI_POWER;

    private BufferedImage tankOne, tankTwo;

    public Tank(GamePanel gamePanel) {
        super(gamePanel);
    }

    protected void init() {
        this.keyHandler = gamePanel.keyHandler;
        this.speed = new Vector2D(0, 1);
        setSolidArea();
        getImage();
        reset();
    }

    @Override
    public void reset() {
        this.direction = Direction.RIGHT;
        this.x = 3 * gamePanel.tileSize;
        this.y = 21 * gamePanel.tileSize;
    }

    /**
     * Set solid area for collision detection
     */
    private void setSolidArea() {
        solidArea = new Rectangle(
                3 * gamePanel.scale,
                8 * gamePanel.scale,
                11 * gamePanel.scale,
                6 * gamePanel.scale
        );
        defaultSolidArea = new Rectangle(solidArea);
    }

    /**
     * Get the images for tank
     */
    private void getImage() {

        try {
            tankOne = ImageResourceParser.getBufferedImage(this, Constant.RES_TANK_ONE_PNG);
            tankTwo = ImageResourceParser.getBufferedImage(this, Constant.RES_TANK_TWO_PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        if (keyHandler.leftPressed
                || keyHandler.rightPressed) {

            direction = keyHandler.leftPressed ? Direction.LEFT : Direction.RIGHT;

            // check collision
            collisionWithTileOn = gamePanel.collisionChecker.checkCollisionWithTiles(this);

            if (!collisionWithTileOn) {
                switch (direction) {
                    case LEFT -> x -= speed.getXComponent();
                    case RIGHT -> x += speed.getXComponent();
                }
            }
        }

        // when the cannonBall is alive update the cannonBall
        if (cannonBall.isAlive()) {
            cannonBall.update();
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;

        if (this.gunAngle <= 45) {
            image = tankOne;
        } else {
            image = tankTwo;
        }

        if (cannonBall.isAlive()) {
            cannonBall.draw(graphics2D);
        }
        // draw the tank on the panel
        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void fire() {
        if (cannonBall.isAlive()) return;

        //TODO: This Algo is not working properly
        cannonBall.setSpeed(new Vector2D((int)(gunPower * 0.35), Math.toRadians(gunAngle)));
        cannonBall.setX(this.x);
        cannonBall.setY(this.y);
        cannonBall.setAlive(true);
    }

    public int getGunAngle() {
        return gunAngle;
    }

    public void setGunAngle(int gunAngle) {
        this.gunAngle = gunAngle;
    }

    public int getGunPower() {
        return gunPower;
    }

    public void setGunPower(int gunPower) {
        this.gunPower = gunPower;
    }
}
