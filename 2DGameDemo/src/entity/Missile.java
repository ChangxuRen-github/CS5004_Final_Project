package entity;

import main.GamePanel;
import util.Direction;
import util.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Missile extends Entity{
    private static final int MAX_LIFE = 500;
    private static final Vector2D GRAVITY = new Vector2D(1, 0);

    private boolean isAlive = false;
    private int life = 0;
    private int updateCounter = 0;

    private BufferedImage missileImage;

    public Missile(GamePanel gamePanel) {
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
            missileImage = ImageIO.read(Objects.requireNonNull(new FileInputStream("./2DGameDemo/res/missile/missile.png")));
            //missileImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/missile/missile.png")));

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
    public void update() {
        if (isOutOfBound() || life >= MAX_LIFE) {
            // TODO
            // play some sound or something to alert the player
            reset();
            return;
        }

        if (gamePanel.collisionChecker.checkCollisionWithTiles(this)) {
            reset();
            //TODO
            //do something else
            return;
        }

        if (gamePanel.collisionChecker.checkCollisionWithEntity(this, gamePanel.target)) {
            reset();
            gamePanel.target.destroyTarget();
            return;
        }

        // update the life of the missile
        life++;
        updateCounter++;
        x += speed.getXComponent();
        y += speed.getYComponent();

        direction = speed.getYComponent() >= 0 ? Direction.UP : Direction.DOWN;

        // update the speed of the missile
        if (updateCounter >= 15) {
            speed.setYComponent(speed.getYComponent() + GRAVITY.getYComponent());
            //System.out.println(speed);
            updateCounter = 0;
        }


    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(missileImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
