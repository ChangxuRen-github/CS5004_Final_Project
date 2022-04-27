package main;

import entity.Entity;

public class CollisionChecker {
    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean checkCollisionWithTiles(Entity entity) {
        boolean hasCollision = false;
        // calculate the coordinates of solid area
        int entityLeftX = entity.getX() + entity.getSolidArea().x;
        int entityRightX = entityLeftX + entity.getSolidArea().width;
        int entityTopY = entity.getY() + entity.getSolidArea().y;
        int entityBottomY = entityTopY + entity.getSolidArea().height;

        // calculate the row and col numbers
        int entityLeftCol = entityLeftX / gamePanel.tileSize;
        int entityRightCol = entityRightX / gamePanel.tileSize;
        int entityTopRow = entityTopY / gamePanel.tileSize;
        int entityBottomRow = entityBottomY / gamePanel.tileSize;

        switch (entity.getDirection()) {
            //in java new vision , switch statement has changes. do not need "break" sign  and :
            case UP -> {

                entityTopRow = (entityTopY - entity.getSpeed().getYComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityTopRow, entityLeftCol)
                        || gamePanel.tileManager.isSolidTile(entityTopRow, entityRightCol)) {
                    hasCollision = true;
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomY + entity.getSpeed().getYComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityBottomRow, entityLeftCol)
                        || gamePanel.tileManager.isSolidTile(entityBottomRow, entityRightCol)) {
                    hasCollision = true;
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftX - entity.getSpeed().getXComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityTopRow, entityLeftCol)
                        || gamePanel.tileManager.isSolidTile(entityBottomRow, entityLeftCol)) {
                    hasCollision = true;
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightX + entity.getSpeed().getXComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityTopRow, entityRightCol)
                        || gamePanel.tileManager.isSolidTile(entityBottomRow, entityRightCol)) {
                    hasCollision = true;
                }
            }
        }

        return hasCollision;
    }

    public boolean checkCollisionWithEntity(Entity missile, Entity target) {
        boolean hasCollision = false;
        // get missile's solid area position
        missile.getSolidArea().x = missile.getSolidArea().x + missile.getX();
        missile.getSolidArea().y = missile.getSolidArea().y + missile.getY();

        // get target's solid area position
        target.getSolidArea().x = target.getSolidArea().x + target.getX();
        target.getSolidArea().y = target.getSolidArea().y + target.getY();

        missile.getSolidArea().x += missile.getSpeed().getXComponent();
        missile.getSolidArea().y += missile.getSpeed().getYComponent();


        if (missile.getSolidArea().intersects(target.getSolidArea())) {
            hasCollision = true;
        }

        missile.restoreDefaultSolidArea();
        target.restoreDefaultSolidArea();
        return hasCollision;
    }
}
