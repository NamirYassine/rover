package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoverFileRider {

    public List<Rover> readFile(InputStream fileLocation) throws IOException, PointNotInPlateauException, PlateauException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileLocation));
        String fileLine;
        int i = 0;
        Plateau plateau = null;
        Position position = null;
        List<Rover> rovers = new ArrayList<>();

        while ((fileLine = bufferedReader.readLine()) != null) {
            fileLine = fileLine.replaceAll(" ", "").toUpperCase();

            if (i == 0) {
                plateau = readPlateau(fileLine);
            } else {
                if (i % 2 != 0) {
                    position = readPosition(fileLine, plateau);
                } else {
                    rovers.add(new Rover(position, readMoves(fileLine)));
                    position = null;
                }
            }
            i++;
        }

        bufferedReader.close();
        return rovers;
    }

    public Plateau readPlateau(String input) throws PlateauException {
        return new Plateau(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    }

    public Position readPosition(String input, Plateau plateau) throws PointNotInPlateauException {// a revoir
        return new Position(new Point(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)), plateau), Direction.valueOf(input.charAt(2) + ""));
    }

    public List<Move> readMoves(String input) {
        return input.chars().mapToObj(item -> Move.valueOf((char)item + "")).collect(Collectors.toList());
    }

}
