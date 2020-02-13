package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.Plateau;
import com.nasa.mars.rover.model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PointTest {
    Plateau plateau;

    @Before
    public void initTest() throws PlateauException {
        plateau = new Plateau(2,2);
    }

    public Plateau getPlateau(int maxX, int maxY) throws PlateauException {
        return new Plateau(maxX,maxY);
    }

    @Test(expected = PointNotInPlateauException.class)
    public void createPointWithXLowerThanPlateauMinX() throws PointNotInPlateauException {
        Point point = new Point(-3, 0, plateau);
    }

    @Test(expected = PointNotInPlateauException.class)
    public void createPointWithYLowerThanPlateauMinY() throws PointNotInPlateauException {
        Point point = new Point(3, -6, plateau);
    }

    @Test(expected = PointNotInPlateauException.class)
    public void createPointWithXGreaterThanPlateauMaxX() throws PointNotInPlateauException {
        Point point = new Point(3, 0, plateau);
    }

    @Test(expected = PointNotInPlateauException.class)
    public void createPointWithYGreaterThanPlateauMaxY() throws PointNotInPlateauException {
        Point point = new Point(0, 3, plateau);
    }

    @Test
    public void createPointOk() throws PointNotInPlateauException {
        Point point = new Point(0, 0, plateau);
        Assertions.assertAll(
                () -> Assert.assertEquals(0, point.getX()),
                () -> Assert.assertEquals(0, point.getY()),
                () -> Assert.assertEquals(plateau, point.getPlateau())
        );
    }
}
