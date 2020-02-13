package com.nasa.mars.rover;

import com.nasa.mars.rover.model.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class DirectionTest {


    @ParameterizedTest
    @EnumSource(Direction.class)
    public void turn(Direction direction) {
        switch (direction) {
            case N: Assertions.assertAll(
                    () -> Assertions.assertEquals(Direction.W, direction.turnLeft()),
                    () -> Assertions.assertEquals(Direction.E, direction.turnRight())
            ); break;
            case E: Assertions.assertAll(
                    () -> Assertions.assertEquals(Direction.N, direction.turnLeft()),
                    () -> Assertions.assertEquals(Direction.S, direction.turnRight())
            ); break;
            case S: Assertions.assertAll(
                    () -> Assertions.assertEquals(Direction.E, direction.turnLeft()),
                    () -> Assertions.assertEquals(Direction.W, direction.turnRight())
            ); break;
            case W: Assertions.assertAll(
                    () -> Assertions.assertEquals(Direction.S, direction.turnLeft()),
                    () -> Assertions.assertEquals(Direction.N, direction.turnRight())
            ); break;
        }

    }
}
