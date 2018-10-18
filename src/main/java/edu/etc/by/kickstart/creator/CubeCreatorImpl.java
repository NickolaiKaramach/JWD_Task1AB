package edu.etc.by.kickstart.creator;

import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.entity.Point;
import edu.etc.by.kickstart.entity.Square;
import edu.etc.by.kickstart.exception.BuilderException;

public class CubeCreatorImpl implements CubeCreator {
    private static final int DOUBLES_IN_CUBE_CORNERS = 24;
    private static final int DIMENSIONS = 3;
    private static final int SQUARE_AMOUNT = 2;
    private static int currentCubeId = 0;

    public static int getCurrentCubeId() {
        return currentCubeId;
    }

    public static void setCurrentCubeId(int currentCubeId) {
        CubeCreatorImpl.currentCubeId = currentCubeId;
    }

    @Override
    public Cube createFromValidData(Double[] allCornersCoordinates) throws BuilderException {

        if (allCornersCoordinates.length != DOUBLES_IN_CUBE_CORNERS) {
            throw new BuilderException("Unable to create cube due to incorrect data!");
        }

        Square[] squaresToBuild = createSquare(createPoints(allCornersCoordinates));
        Square topSquare = squaresToBuild[0];
        Square botSquare = squaresToBuild[1];

        return new Cube(currentCubeId++, topSquare, botSquare);
    }

    private Point[] createPoints(Double[] coordinates) {
        int pointsAmount = DOUBLES_IN_CUBE_CORNERS / DIMENSIONS;
        Point[] cornersPoints = new Point[pointsAmount];

        for (int i = 0; i < pointsAmount; i++) {
            Double x = coordinates[i * 3];
            Double y = coordinates[i * 3 + 1];
            Double z = coordinates[i * 3 + 2];
            cornersPoints[i] = new Point(x, y, z);
        }

        return cornersPoints;
    }

    private Square[] createSquare(Point[] cornersPoints) {
        Square[] finalSquares = new Square[SQUARE_AMOUNT];

        for (int i = 0; i < SQUARE_AMOUNT; i++) {
            Point leftTopCorner = cornersPoints[i * 4];
            Point rightTopCorner = cornersPoints[i * 4 + 1];
            Point rightBotCorner = cornersPoints[i * 4 + 2];
            Point leftBotCorner = cornersPoints[i * 4 + 3];

            finalSquares[i] = new Square(leftTopCorner, rightTopCorner,
                    rightBotCorner, leftBotCorner);
        }

        return finalSquares;
    }
}
