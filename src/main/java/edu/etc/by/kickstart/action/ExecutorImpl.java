package edu.etc.by.kickstart.action;

import edu.etc.by.kickstart.entity.Point;
import edu.etc.by.kickstart.entity.Square;

public class ExecutorImpl implements Executor {

    private static final int SQUARED = 2;

    @Override
    public double calcDistance(Point validBeginPoint, Point validEndPoint) {
        double squareDifferenceXCoordinates = Math.pow((validBeginPoint.getX() - validEndPoint.getX()), SQUARED);
        double squareDifferenceYCoordinates = Math.pow((validBeginPoint.getY() - validEndPoint.getY()), SQUARED);
        double squareDifferenceZCoordinates = Math.pow((validBeginPoint.getZ() - validEndPoint.getZ()), SQUARED);

        return Math.sqrt(squareDifferenceXCoordinates + squareDifferenceYCoordinates
                + squareDifferenceZCoordinates);
    }

    public boolean haveEqualSize(Square firstValidSquare, Square secondValidSquare) {
        Point rightBotCornerFirstSquare;
        Point rightTopCornerFirstSquare;
        Point rightBotCornerSecondSquare;
        Point rightTopCornerSecondSquare;

        rightBotCornerFirstSquare = firstValidSquare.getRightBotCorner();
        rightTopCornerFirstSquare = firstValidSquare.getRightTopCorner();
        rightBotCornerSecondSquare = secondValidSquare.getRightBotCorner();
        rightTopCornerSecondSquare = secondValidSquare.getRightTopCorner();

        return calcDistance(rightBotCornerFirstSquare, rightTopCornerFirstSquare) ==
                calcDistance(rightBotCornerSecondSquare, rightTopCornerSecondSquare);
    }

    @Override
    public boolean haveEqualSize(Square firstValidSquare, Square secondValidSquare, Square thirdValidSquare) {
        return haveEqualSize(firstValidSquare, secondValidSquare) &&
                haveEqualSize(secondValidSquare, thirdValidSquare);
    }
}
