package edu.neu.main;

import edu.neu.entity.Entity;

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

    public boolean checkCollisionWithEntity(Entity cannonball, Entity target) {
        boolean hasCollision = false;
        // get cannonball's solid area position
        cannonball.getSolidArea().x = cannonball.getSolidArea().x + cannonball.getX();
        cannonball.getSolidArea().y = cannonball.getSolidArea().y + cannonball.getY();

        // get target's solid area position
        target.getSolidArea().x = target.getSolidArea().x + target.getX();
        target.getSolidArea().y = target.getSolidArea().y + target.getY();

        cannonball.getSolidArea().x += cannonball.getSpeed().getXComponent();
        cannonball.getSolidArea().y += cannonball.getSpeed().getYComponent();


        if (cannonball.getSolidArea().intersects(target.getSolidArea())) {
            hasCollision = true;
        }

        cannonball.restoreDefaultSolidArea();
        target.restoreDefaultSolidArea();
        return hasCollision;
    }
}
