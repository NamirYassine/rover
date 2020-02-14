package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.CanNotStepException;
import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class PositionTest {

    public Plateau getPlateau(int maxX, int maxY) throws PlateauException {
        return new Plateau(maxX, maxY);
    }

    public Point getPoint(int x, int y) throws PointNotInPlateauException, PlateauException {
        return new Point(x, y, getPlateau(x*2, y*2));
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void canNotStep(Direction direction) throws PointNotInPlateauException, PlateauException {
        Position position = new Position(getPoint(0,0), direction);
        Assertions.assertFalse(position.canStep());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void canStep(Direction direction) throws PointNotInPlateauException, PlateauException {
        Position position = new Position(getPoint(1,1), direction);
        Assertions.assertTrue(position.canStep());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void canNotStepException(Direction direction) throws PointNotInPlateauException, PlateauException {
        Position position = new Position(getPoint(0,0), direction);
        Assertions.assertThrows(CanNotStepException.class, () -> position.step());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void step(Direction direction) throws PointNotInPlateauException, PlateauException, CanNotStepException {
        Position position = new Position(getPoint(1,1), direction);
        position.step();
        switch (direction) {
            case N: Assertions.assertAll(
                    () -> Assertions.assertEquals(1, position.getPoint().getX()),
                    () -> Assertions.assertEquals(2, position.getPoint().getY())
            ); break;
            case E: Assertions.assertAll(
                    () -> Assertions.assertEquals(2, position.getPoint().getX()),
                    () -> Assertions.assertEquals(1, position.getPoint().getY())
            ); break;
            case S: Assertions.assertAll(
                    () -> Assertions.assertEquals(1, position.getPoint().getX()),
                    () -> Assertions.assertEquals(0, position.getPoint().getY())
            ); break;
            case W: Assertions.assertAll(
                    () -> Assertions.assertEquals(0, position.getPoint().getX()),
                    () -> Assertions.assertEquals(1, position.getPoint().getY())
            ); break;
        }
    }

    @ParameterizedTest
    @EnumSource(Move.class)
    public void move(Move move) throws PointNotInPlateauException, PlateauException, CanNotStepException {
        Position position = new Position(getPoint(1,1), Direction.N);
        position.move(move);
        switch (move) {
            case L: Assertions.assertAll(
                    () -> Assertions.assertEquals(1, position.getPoint().getX()),
                    () -> Assertions.assertEquals(1, position.getPoint().getY()),
                    () -> Assertions.assertEquals(Direction.W, position.getDirection())
            ); break;
            case R: Assertions.assertAll(
                    () -> Assertions.assertEquals(1, position.getPoint().getX()),
                    () -> Assertions.assertEquals(1, position.getPoint().getY()),
                    () -> Assertions.assertEquals(Direction.E, position.getDirection())
            ); break;
            case M: Assertions.assertAll(
                    () -> Assertions.assertEquals(1, position.getPoint().getX()),
                    () -> Assertions.assertEquals(2, position.getPoint().getY()),
                    () -> Assertions.assertEquals(Direction.N, position.getDirection())
            ); break;
        }
    }
}
