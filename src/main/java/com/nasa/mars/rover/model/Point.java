package com.nasa.mars.rover.model;

import com.nasa.mars.rover.exception.PointNotInPlateauException;

public class Point {

    private int x;
    private int y;
    private final Plateau plateau;

    public Point(int x, int y, Plateau plateau) throws PointNotInPlateauException {
        if (isInPlateau(x, y, plateau)) { throw new PointNotInPlateauException("The point is not in the plateau"); }
        this.x = x;
        this.y = y;
        this.plateau = plateau;
    }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public Plateau getPlateau() { return plateau; }

    @Override
    public String toString() { return x + " " + y; }

    private boolean isInPlateau(int x, int y, Plateau plateau) {
        return x < plateau.minX || y < plateau.minY || x > plateau.getMaxX() || y > plateau.getMaxY();
    }
}
