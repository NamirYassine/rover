package com.nasa.mars.rover.model;

import com.nasa.mars.rover.exception.CanNotStepException;

import java.util.List;

public class Rover {

    private Position position;
    private final List<Move> moves;

    public Rover(Position position, List<Move> moves) {
        this.position = position;
        this.moves = moves;
    }

    public Position getPosition() {
        return position;
    }

    public void move() throws CanNotStepException {
        for (Move move: moves) {
            position.move(move);
        }
    }
}
