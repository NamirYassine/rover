package com.nasa.mars.rover;

import com.nasa.mars.rover.exception.PlateauException;
import com.nasa.mars.rover.exception.PointNotInPlateauException;
import com.nasa.mars.rover.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoverFileRider {

    public static List<Rover> readFile(InputStream fileLocation) throws IOException, PointNotInPlateauException, PlateauException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileLocation));
        String fileLine;
        int i = 0;
        Plateau plateau = null;
        Position position = null;
        List<Rover> rovers = new ArrayList<>();

        while ((fileLine = bufferedReader.readLine()) != null) {
            // to tolerate spaces and Case
            fileLine = fileLine.replace(" ", "").toUpperCase();

            switch (getLine(i)) {
                case PLATEAU: plateau = readPlateau(fileLine); break;
                case POSITION: position = readPosition(fileLine, plateau); break;
                case MOVES: rovers.add(new Rover(position, readMoves(fileLine)));
                    position = null; break;
            }
            i++;
        }

        bufferedReader.close();
        return rovers;
    }

    public static Line getLine(int index) {
        if (index == 0) {
            // first line is for plateau
            return Line.PLATEAU;
        } else if (index % 2 != 0) {
            // second line for position
            return Line.POSITION;
        } else {
            //last line for moves
            return Line.MOVES;
        }
    }

    public static Plateau readPlateau(String input) throws PlateauException {
        int maxX = Character.getNumericValue(input.charAt(0));
        int maxY = Character.getNumericValue(input.charAt(1));
        try {
            return new Plateau(maxX, maxY);
        } catch (PlateauException e) {
            throw new PlateauException("The Plateau (" + maxX + "," + maxY + ") must be greater than (" + Plateau.MIN_X + "," + Plateau.MIN_Y + ").");
        }
    }

    public static Position readPosition(String input, Plateau plateau) throws PointNotInPlateauException {
        int x = Character.getNumericValue(input.charAt(0));
        int y = Character.getNumericValue(input.charAt(1));
        char d = input.charAt(2);

        try {
            return new Position(new Point(x, y, plateau), Direction.valueOf(d + ""));
        } catch (PointNotInPlateauException e) {
            throw new PointNotInPlateauException("The point (" + x + "," + y +") can't be in the plateau (" +
                    Plateau.MIN_X + "-" + plateau.getMaxX()+ "," + Plateau.MIN_Y + "-" +plateau.getMaxY() +").");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Direction : " + d + " is not in : " + Arrays.asList(Direction.values()));
        }
    }

    public static List<Move> readMoves(String input) {
        try {
            return Arrays.stream(input.split("")).map(Move::valueOf).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Moves : " + input + " must be in : " + Arrays.asList(Move.values()));
        }
    }
}
