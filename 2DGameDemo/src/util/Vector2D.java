package util;

public class Vector2D {
    private int yComponent;
    private int xComponent;

    public Vector2D(int magnitude, double angle) {
        //DEBUG
        //System.out.println("Magnitude is " + magnitude);
        //System.out.println("Angle is " + angle);
        // The pixel's index is upside down
        // The 0 started from top left
        setYComponent((int) (Math.sin(angle) * magnitude) * (-1));
        setXComponent((int) (Math.cos(angle) * magnitude));
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

    public void setYComponent(int yComponent) {
        this.yComponent = yComponent;
    }

    public void setXComponent(int xComponent) {
        this.xComponent = xComponent;
        this.xComponent = Math.max(this.xComponent, 4);
    }

    // DEBUG
    @Override
    public String toString() {
        return "Vector2D{" +
                "yComponent=" + yComponent +
                ", xComponent=" + xComponent +
                '}';
    }
}
