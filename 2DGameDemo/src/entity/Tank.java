package entity;

import main.GamePanel;
import main.KeyHandler;
import util.Constant;
import util.Direction;
import util.ImageResourceParser;
import util.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tank extends Entity{
    private KeyHandler keyHandler;
    private final Missile missile = new Missile(gamePanel);

    private static final int MAX_GUN_ANGLE = 80;
    private static final int MIN_GUN_ANGLE = 10;
    private static final int MAX_GUN_POWER = 80;
    private static final int MIN_GUN_POWER = 10;
    private int gunAngle = MIN_GUN_ANGLE;
    private int gunPower = MIN_GUN_POWER;

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
            tankOne = ImageResourceParser.getBufferedImage(Constant.RES_TANK_ONE_PNG);
            tankTwo = ImageResourceParser.getBufferedImage(Constant.RES_TANK_TWO_PNG);
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

        // when the missile is alive update the missile
        if (missile.isAlive()) {
            missile.update();
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

        if (missile.isAlive()) {
            missile.draw(graphics2D);
        }
        // draw the tank on the panel
        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void incrementGunAngle() {
        this.gunAngle += 5;
        this.gunAngle = Math.min(MAX_GUN_ANGLE, this.gunAngle);
    }

    public void decrementGunAngle() {
        this.gunAngle -= 5;
        this.gunAngle = Math.max(MIN_GUN_ANGLE, this.gunAngle);
    }

    public void incrementGunPower() {
        this.gunPower += 10;
        this.gunPower = Math.min(MAX_GUN_POWER, this.gunPower);
    }

    public void decrementGunPower() {
        this.gunPower -= 10;
        this.gunPower = Math.max(MIN_GUN_POWER, this.gunPower);
    }

    public void fire() {
        if (missile.isAlive()) return;

        //TODO: This Algo is not working properly
        missile.setSpeed(new Vector2D((int) (gunPower * 0.3), Math.toRadians(gunAngle)));
        missile.setX(this.x);
        missile.setY(this.y);
        missile.setAlive(true);
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
