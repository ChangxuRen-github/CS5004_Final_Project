package entity;

import main.GamePanel;
import main.KeyHandler;
import util.Direction;
import util.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tank extends Entity{
    private final GamePanel gamePanel;
    private KeyHandler keyHandler;
    private static final int MAX_GUN_ANGLE = 90;
    private static final int MIN_GUN_ANGLE = 0;
    private static final int MAX_GUN_POWER = 100;
    private static final int MIN_GUN_POWER = 0;
    public int gunAngle = 0;
    public int gunPower = 0;

    BufferedImage tankOne, tankTwo;

    public Tank(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    private void init() {
        this.direction = Direction.RIGHT;
        this.keyHandler = gamePanel.keyHandler;
        setSolidArea();
        setDefaultValues();
        getImage();
    }

    private void setSolidArea() {
        solidArea = new Rectangle(
                3 * gamePanel.scale,
                8 * gamePanel.scale,
                11 * gamePanel.scale,
                6 * gamePanel.scale
        );
    }

    public void setDefaultValues() {
        // set the initial position of the player
        this.x = 100;
        this.y = 435;
        this.speed = new Vector2D(0, 1);
    }

    public void getImage() {

        try {

            tankOne = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tank/tankOne.png")));
            tankTwo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tank/tankTwo.png")));


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
            collisionOn = gamePanel.collisionChecker.check(this);

            if (!collisionOn) {
                switch (direction) {
                    case LEFT -> x -= speed.getXComponent();
                    case RIGHT -> x += speed.getXComponent();
                }
            }
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

    }
}
