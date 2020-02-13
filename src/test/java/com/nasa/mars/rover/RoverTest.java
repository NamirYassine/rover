package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.CanNotStepException;
import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class RoverTest {

    public Position getPosition(int x, int y, int maxX, int maxY, Direction direction) throws PlateauException, PointNotInPlateauException {
        return new  Position(new Point(x,y, new Plateau(maxX,maxY)), direction);
    }

    public Rover getRover(List<Move> moves) throws PointNotInPlateauException, PlateauException {
        return new Rover(getPosition(1, 1, 2, 2, Direction.N), moves);
    }

    public Rover getRover(int x, int y, int maxX, int maxY, Direction direction, List<Move> moves) throws PointNotInPlateauException, PlateauException {
        return new Rover(getPosition(x, y, maxX, maxY, direction), moves);
    }

    @Test(expected = CanNotStepException.class)
    public void canNotStepException() throws PointNotInPlateauException, PlateauException, CanNotStepException {
        getRover(Arrays.asList(Move.M, Move.M, Move.M)).move();
    }

    @Test
    public void firstRoverTest() throws PointNotInPlateauException, PlateauException, CanNotStepException {
        Rover rover = getRover(1, 2, 5, 5, Direction.N,
                Arrays.asList(Move.L, Move.M, Move.L, Move.M, Move.L, Move.M, Move.L, Move.M, Move.M));
        rover.move();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, rover.getPosition().getPoint().getX()),
                () -> Assertions.assertEquals(3, rover.getPosition().getPoint().getY()),
                () -> Assertions.assertEquals(Direction.N, rover.getPosition().getDirection()),
                () -> Assertions.assertEquals("1 3 N", rover.getPosition().toString())
        );
    }

    @Test
    public void secondRoverTest() throws PointNotInPlateauException, PlateauException, CanNotStepException {
        Rover rover = getRover(3, 3, 5, 5, Direction.E,
                Arrays.asList(Move.M, Move.M, Move.R, Move.M, Move.M, Move.R, Move.M, Move.R, Move.R, Move.M));
        rover.move();
        Assertions.assertAll(
                () -> Assertions.assertEquals(5, rover.getPosition().getPoint().getX()),
                () -> Assertions.assertEquals(1, rover.getPosition().getPoint().getY()),
                () -> Assertions.assertEquals(Direction.E, rover.getPosition().getDirection()),
                () -> Assertions.assertEquals("5 1 E", rover.getPosition().toString())
        );
    }

}
