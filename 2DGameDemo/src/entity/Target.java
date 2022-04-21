package entity;

import main.GamePanel;
import util.Direction;
import util.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Target extends Entity{
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
                16 * gamePanel.scale,
                16 * gamePanel.scale
        );
        defaultSolidArea = new Rectangle(solidArea);
    }

    private void setRandomPosition() {
        this.x = (random.nextInt(gamePanel.maxScreenCol - TARGET_POSITION_LEFT_BOUND - 2) + TARGET_POSITION_LEFT_BOUND) * gamePanel.tileSize;
        this.y = (random.nextInt(gamePanel.maxScreenRow - TARGET_POSITION_TOP_BOUND - 2) + TARGET_POSITION_TOP_BOUND) * gamePanel.tileSize;
    }

    private void getImage() {

        try {

            target = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/target/target.png")));
            targetExploded = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/target/targetExploded.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {}

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
