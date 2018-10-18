package edu.etc.by.kickstart.action;

import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.entity.Point;
import edu.etc.by.kickstart.entity.Square;

public class CubeActionImpl implements CubeAction {
    private static final ExecutorImpl executor = new ExecutorImpl();
    private static final int EDGE_AMOUNTS = 6;
    private static final int CUBED = 3;
    private static final int ZERO = 0;

    @Override
    public double calcSurfaceArea(Cube validCube) {
        Square validSquare;
        Point rightBotCorner;
        Point rightTopCorner;

        validSquare = validCube.getBotSquare();
        rightBotCorner = validSquare.getRightBotCorner();
        rightTopCorner = validSquare.getRightTopCorner();

        double edgeLength = executor.calcDistance(rightBotCorner, rightTopCorner);
        return EDGE_AMOUNTS * Math.pow(edgeLength, 2);
    }

    @Override
    public double calcVolume(Cube validCube) {
        Square validSquare;
        Point rightBotCorner;
        Point rightTopCorner;

        validSquare = validCube.getBotSquare();
        rightBotCorner = validSquare.getRightBotCorner();
        rightTopCorner = validSquare.getRightTopCorner();

        double edgeLength = executor.calcDistance(rightBotCorner, rightTopCorner);
        return Math.pow(edgeLength, CUBED);
    }

    @Override
    public boolean isBasedOnCoordinatePlane(Cube cube) {
        boolean isBased = false;

        Square botSquare = cube.getBotSquare();
        Square topSquare = cube.getTopSquare();
        if ((isBasedOnCoordinatePlane(botSquare) ||
                (isBasedOnCoordinatePlane(topSquare)))) {
            isBased = true;
        }
        return isBased;
    }

    private boolean isBasedOnCoordinatePlane(Square square) {
        boolean isBased = false;
        Point leftBotCorner = square.getLeftBotCorner();
        Point leftTopCorner = square.getLeftTopCorner();

        if ((((leftBotCorner.getX() == ZERO) &&
                (leftTopCorner.getX() == ZERO)) &&
                (leftBotCorner.getX() == ZERO)) &&
                (leftTopCorner.getX() == ZERO)) {
            isBased = true;

        } else if ((((leftBotCorner.getY() == ZERO) &&
                (leftTopCorner.getY() == ZERO)) &&
                (leftBotCorner.getY() == ZERO)) &&
                (leftTopCorner.getY() == ZERO)) {
            isBased = true;

        } else if ((((leftBotCorner.getZ() == ZERO) &&
                (leftTopCorner.getZ() == ZERO)) &&
                (leftBotCorner.getZ() == ZERO)) &&
                (leftTopCorner.getZ() == ZERO)) {
            isBased = true;
        }

        return isBased;
    }
}
