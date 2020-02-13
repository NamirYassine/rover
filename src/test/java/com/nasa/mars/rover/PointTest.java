package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.Plateau;
import com.nasa.mars.rover.model.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PointTest {
    Plateau plateau;

    @BeforeEach
    public void initTest() throws PlateauException {
        plateau = new Plateau(2,2);
    }

    @Test
    public void createPointWithXLowerThanPlateauMinX() {
        Assertions.assertThrows(PointNotInPlateauException.class, () -> new Point(-3, 0, plateau));
    }

    @Test
    public void createPointWithYLowerThanPlateauMinY() {
        Assertions.assertThrows(PointNotInPlateauException.class, () -> new Point(3, -6, plateau));
    }

    @Test
    public void createPointWithXGreaterThanPlateauMaxX() {
        Assertions.assertThrows(PointNotInPlateauException.class, () -> new Point(3, 0, plateau));
    }

    @Test
    public void createPointWithYGreaterThanPlateauMaxY() {
        Assertions.assertThrows(PointNotInPlateauException.class, () -> new Point(0, 3, plateau));
    }

    @Test
    public void createPointOk() throws PointNotInPlateauException {
        Point point = new Point(0, 0, plateau);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, point.getX()),
                () -> Assertions.assertEquals(0, point.getY()),
                () -> Assertions.assertEquals(plateau, point.getPlateau())
        );
    }
}
