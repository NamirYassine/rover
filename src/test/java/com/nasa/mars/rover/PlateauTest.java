package com.nasa.mars.rover;


import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.model.Plateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlateauTest {


    @Test
    public void createPlateauWithXLowerThan0() {
        Assertions.assertThrows(PlateauException.class, () -> new Plateau(-3, 0));
    }

    @Test
    public void createPlateauWithYLowerThan0() {
        Assertions.assertThrows(PlateauException.class, () -> new Plateau(0, -2));
    }

    @Test
    public void createPlateauOkWith0() throws PlateauException {
        Plateau plateau = new Plateau(0, 0);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, plateau.getMaxX()),
                () -> Assertions.assertEquals(0, plateau.getMaxY())
        );
    }

    @Test
    public void createPointOk() throws PlateauException {
        Plateau plateau = new Plateau(2, 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, plateau.getMaxX()),
                () -> Assertions.assertEquals(4, plateau.getMaxY())
        );
    }

}
