package com.nasa.mars.rover;


import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.model.Plateau;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PlateauTest {


    @Test(expected = PlateauException.class)
    public void createPlateauWithXLowerThan0() throws PlateauException {
        Plateau plateau = new Plateau(-3, 0);
    }

    @Test(expected = PlateauException.class)
    public void createPlateauWithYLowerThan0() throws PlateauException {
        Plateau plateau = new Plateau(0, -2);
    }

    @Test
    public void createPlateauOkWith0() throws PlateauException {
        Plateau plateau = new Plateau(0, 0);
        Assertions.assertAll(
                () -> Assert.assertEquals(0, plateau.getMaxX()),
                () -> Assert.assertEquals(0, plateau.getMaxY())
        );
    }

    @Test
    public void createPointOk() throws PlateauException {
        Plateau plateau = new Plateau(2, 4);
        Assertions.assertAll(
                () -> Assert.assertEquals(2, plateau.getMaxX()),
                () -> Assert.assertEquals(4, plateau.getMaxY())
        );
    }

}
