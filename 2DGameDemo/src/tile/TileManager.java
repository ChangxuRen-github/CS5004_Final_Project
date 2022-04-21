package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    private static final int SKY = 0;
    private static final int BRICK = 1;
    private static final int GRASS = 2;
    private static final int CLOUD = 3;
    private static final int SKY_WITH_STARS = 4;

    private final GamePanel gamePanel;
    private Tile[] tiles;
    private int[][] map;
    private final String[] maps = {"/maps/map01.txt"};
    private int currentMap = 0;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    private void init() {
        tiles = new Tile[5];
        getTileImages();
        map = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        loadMap(maps[currentMap]);
    }

    public void getTileImages() {
        try {
            tiles[SKY] = new Tile();
            tiles[SKY].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sky/sky.png")));

            tiles[BRICK] = new Tile();
            tiles[BRICK].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/land/brick.png")));
            // set Brick  to be solid
            tiles[BRICK].isSolid = true;

            tiles[GRASS] = new Tile();
            tiles[GRASS].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/land/grass.png")));
            // set grass tile to be solid
            tiles[GRASS].isSolid = true;

            tiles[CLOUD] = new Tile();
            tiles[CLOUD].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sky/bigCloud.png")));

            tiles[SKY_WITH_STARS] = new Tile();
            tiles[SKY_WITH_STARS].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sky/skyWithStars.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * loads maps from res/maps directory
     */
    private void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for (int row = 0; row < gamePanel.maxScreenRow; row++) {
                String[] currentLine = bufferedReader.readLine().split(" ");
                for (int col = 0; col < gamePanel.maxScreenCol; col++) {
                    map[row][col] = Integer.parseInt(currentLine[col]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * change maps
     */
    private void changeMap() {
        currentMap = (currentMap + 1) % maps.length;
    }

    /**
     * draw tiles on the gamePanel
     * @param g2
     */
    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;

        for (int row = 0; row < gamePanel.maxScreenRow; row++) {
            for (int col = 0; col < gamePanel.maxScreenCol; col++, x += gamePanel.tileSize) {
                g2.drawImage(tiles[map[row][col]].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            x = 0;
            y += gamePanel.tileSize;
        }
    }

    public boolean isSolidTile(int row, int col) {
        if (row < 0 || row >= gamePanel.maxScreenRow || col < 0 || col >= gamePanel.maxScreenCol) {
            return false;
        }

        return tiles[map[row][col]].isSolid;
    }
}
