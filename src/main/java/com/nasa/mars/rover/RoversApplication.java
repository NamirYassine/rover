package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.CanNotStepException;
import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.Rover;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class RoversApplication {

    public static void main(String[] args) throws IOException, PointNotInPlateauException, PlateauException {
        RoverFileRider demoFileReader = new RoverFileRider();
        List<Rover> rovers = demoFileReader.readFile(new FileInputStream(args[0]));

        for (int i=0; i<rovers.size(); i++) {
            String msg = "";
            Rover rover = rovers.get(i);
            try {
                rover.move();
                msg = rover.getPosition().toString();
            } catch (CanNotStepException e) {
                msg = "The " + (1+i) + "th rover blocked because can't step out of plateau.";
            } finally {
                System.out.println(msg);
            }
        }
    }

}
