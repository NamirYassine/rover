package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.CanNotStepException;
import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.Rover;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class RoversApplication {

    public static void main(String[] args) throws IOException, CanNotStepException, PointNotInPlateauException, PlateauException {
        RoverFileRider demoFileReader = new RoverFileRider();
        List<Rover> rovers = demoFileReader.readFile(new FileInputStream(args[0]));

        for (Rover rover : rovers) {
            rover.move();
            System.out.println(rover.getPosition());
        }
    }

}
