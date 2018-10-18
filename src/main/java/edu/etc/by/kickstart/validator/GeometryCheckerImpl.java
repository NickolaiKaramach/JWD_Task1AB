package edu.etc.by.kickstart.validator;

import edu.etc.by.kickstart.action.ExecutorImpl;
import edu.etc.by.kickstart.creator.CubeCreatorImpl;
import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.entity.Point;
import edu.etc.by.kickstart.entity.Square;
import edu.etc.by.kickstart.exception.BuilderException;

import java.util.List;

public class GeometryCheckerImpl implements GeometryChecker {

    private static final String REGEX_DOUBLE = "[-+]?[0-9]+(\\.[0-9]+)?";
    private static final String REGEX_SPACE = "(\\s)+";
    private static final int DOUBLES_IN_CUBE_CORNERS = 24;
    private static final CubeCreatorImpl cubeBuilder = new CubeCreatorImpl();
    private static final ExecutorImpl executor = new ExecutorImpl();

    public boolean isValidCubeData(List<String> data) {
        boolean result = true;

        for (String line : data) {
            if (!isValidStringForCube(line)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean isValidCube(Cube cube) {
        Square topSquare;
        Square botSquare;
        topSquare = cube.getTopSquare();
        botSquare = cube.getBotSquare();

        Square sideSquare = new Square(topSquare.getLeftTopCorner(), topSquare.getLeftBotCorner(),
                botSquare.getLeftBotCorner(), botSquare.getLeftTopCorner());

        boolean isValid = false;

        if (((isValidSquare(topSquare)) && (isValidSquare(botSquare))) && isValidSquare(sideSquare)) {
            isValid = executor.haveEqualSize(topSquare, botSquare, sideSquare);
        }
        return isValid;
    }

    @Override
    public boolean isValidSquare(Square square) {
        Point rightBotCorner;
        Point leftBotCorner;
        Point rightTopCorner;
        Point leftTopCorner;

        rightBotCorner = square.getRightBotCorner();
        leftBotCorner = square.getLeftBotCorner();
        rightTopCorner = square.getRightTopCorner();
        leftTopCorner = square.getLeftTopCorner();

        return ((executor.calcDistance(rightBotCorner, leftBotCorner) ==
                executor.calcDistance(rightBotCorner, rightTopCorner)) &&
                (executor.calcDistance(leftTopCorner, leftBotCorner) ==
                        executor.calcDistance(leftTopCorner, rightTopCorner))) &&
                (executor.calcDistance(leftTopCorner, rightTopCorner) ==
                        executor.calcDistance(leftBotCorner, rightBotCorner));
    }

    public boolean isValidStringForCube(String data) {
        int lastId = CubeCreatorImpl.getCurrentCubeId();
        boolean isValid = true;
        if (!data.isEmpty()) {
            String[] doubleCoordinates = data.split(REGEX_SPACE);
            Double[] allPointsCoordinates = new Double[DOUBLES_IN_CUBE_CORNERS];

            if ((doubleCoordinates.length == DOUBLES_IN_CUBE_CORNERS)) {
                for (int i = 0; i < DOUBLES_IN_CUBE_CORNERS; i++) {

                    String currentDouble = doubleCoordinates[i];
                    if (!currentDouble.matches(REGEX_DOUBLE)) {
                        isValid = false;
                    } else {
                        allPointsCoordinates[i] = Double.valueOf(currentDouble);
                    }
                }
            } else {
                isValid = false;
            }

            if (isValid) {
                isValid = isValidGeometricDataForCube(allPointsCoordinates);
            }
        } else {
            isValid = false;
        }
        CubeCreatorImpl.setCurrentCubeId(lastId);
        return isValid;
    }

    private boolean isValidGeometricDataForCube(Double[] allPointsCoordinates) {
        boolean isValid;
        try {
            isValid = isValidCube(cubeBuilder.createFromValidData(allPointsCoordinates));
        } catch (BuilderException e) {
            isValid = false;
        }
        return isValid;
    }
}
