package com.nasa.mars.rover.model;

import com.nasa.mars.rover.exception.CanNotStepException;

public class Position {

    private Point point;
    private Direction direction;

    public Position(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return point + " " + direction;
    }

    public boolean canStep() {
        return (Direction.N.equals(direction) && point.getY() + 1 <= point.getPlateau().getMaxY()) ||
                (Direction.E.equals(direction) && point.getX() + 1 <= point.getPlateau().getMaxX()) ||
                (Direction.S.equals(direction) && point.getY() - 1 >= Plateau.minY) ||
                (Direction.W.equals(direction) && point.getX() - 1 >= Plateau.minX);
    }

    public void step() throws CanNotStepException {
        if (canStep()) {
            switch (direction) {
                case N : point.setY(point.getY() + 1); break;
                case E : point.setX(point.getX() + 1); break;
                case S : point.setY(point.getY() - 1); break;
                case W : point.setX(point.getX() - 1); break;
            }
        } else {
            throw new CanNotStepException("Can't step in this direction.");
        }
    }

    public void move(Move to) throws CanNotStepException {
        switch (to) {
            case R: direction = direction.turnRight(); break;
            case L: direction = direction.turnLeft(); break;
            case M: step(); break;
        }
    }
}
