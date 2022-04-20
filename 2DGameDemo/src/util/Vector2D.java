package util;

public class Vector2D {
    private final int yComponent;
    private final int xComponent;

    public Vector2D(int magnitude, double angle) {
        yComponent = (int) (Math.sin(angle) * magnitude);
        xComponent = (int) (Math.cos(angle) * magnitude);
    }

    public Vector2D(int yComponent, int xComponent) {
        this.yComponent = yComponent;
        this.xComponent = xComponent;
    }

    public int getYComponent() {
        return yComponent;
    }

    public int getXComponent() {
        return xComponent;
    }
}
