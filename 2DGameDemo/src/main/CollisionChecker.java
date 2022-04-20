package main;

import entity.Entity;

public class CollisionChecker {
    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean check(Entity entity) {
        boolean hasCollision = false;
        // calculate the coordinates of solid area
        int entityLeftX = entity.x + entity.solidArea.getBounds().x;
        int entityRightX = entityLeftX + entity.solidArea.getBounds().width;
        int entityTopY = entity.y + entity.solidArea.getBounds().y;
        int entityBottomY = entityTopY + entity.solidArea.getBounds().height;

        // calculate the row and col numbers
        int entityLeftCol = entityLeftX / gamePanel.tileSize;
        int entityRightCol = entityRightX / gamePanel.tileSize;
        int entityTopRow = entityTopY / gamePanel.tileSize;
        int entityBottomRow = entityBottomY / gamePanel.tileSize;

        switch (entity.direction) {
            case UP -> {
                entityTopRow = (entityTopY - entity.speed.getYComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityTopRow, entityLeftCol)
                        || gamePanel.tileManager.isSolidTile(entityTopRow, entityRightCol)) {
                    hasCollision = true;
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomY + entity.speed.getYComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityBottomRow, entityLeftCol)
                        || gamePanel.tileManager.isSolidTile(entityBottomRow, entityRightCol)) {
                    hasCollision = true;
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftX - entity.speed.getXComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityTopRow, entityLeftCol)
                        || gamePanel.tileManager.isSolidTile(entityBottomRow, entityLeftCol)) {
                    hasCollision = true;
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightX + entity.speed.getXComponent()) / gamePanel.tileSize;
                if (gamePanel.tileManager.isSolidTile(entityTopRow, entityRightCol)
                        || gamePanel.tileManager.isSolidTile(entityBottomRow, entityRightCol)) {
                    hasCollision = true;
                }
            }
        }

        return hasCollision;
    }
}
