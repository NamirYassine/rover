package com.nasa.mars.rover.model;

public enum Direction {

    N,
    E,
    S,
    W;

    public Direction turnLeft() {
        switch (this) {
            case N : return Direction.W;
            case E : return Direction.N;
            case S : return Direction.E;
            case W : return Direction.S;
            default: return this;
        }
    }

    public Direction turnRight() {
        switch (this) {
            case N : return Direction.E;
            case E : return Direction.S;
            case S : return Direction.W;
            case W : return Direction.N;
            default: return this;
        }
    }
}
