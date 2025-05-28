package models;

public class Coordinates {
    private long x;
    private Long y; // Значение поля должно быть больше -891, Поле не может быть null

    public Coordinates(long x, Long y) {
        setX(x);
        setY(y);
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(Long y) {
        if (y == null) {
            throw new IllegalArgumentException("Y coordinate cannot be null");
        }
        if (y <= -891) {
            throw new IllegalArgumentException("Y coordinate must be greater than -891");
        }
        this.y = y;
    }

    public long getX() { return x; }
    public Long getY() { return y; }
    
    @Override
    public String toString() {
        return "Coordinates{" +"x=" + x +", y=" + y + '}';
    }
}   