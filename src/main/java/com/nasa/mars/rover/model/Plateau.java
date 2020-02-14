package com.nasa.mars.rover.model;

import com.nasa.mars.rover.exception.PlateauException;

public class Plateau {

    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;
    private final int maxX;
    private final int maxY;

    public Plateau(int maxX, int maxY) throws PlateauException {
        if (maxX < MIN_X || maxY < MIN_Y) {
            throw new PlateauException("The x and y must be greater than 0 to create the plateau.");
        }
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
