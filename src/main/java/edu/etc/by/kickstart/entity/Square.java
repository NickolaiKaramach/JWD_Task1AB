package edu.etc.by.kickstart.entity;

import java.io.Serializable;

public class Square implements Serializable {
    private static final long serialVersionUID = 5102062782888486030L;
    private Point leftTopCorner;
    private Point rightTopCorner;
    private Point rightBotCorner;
    private Point leftBotCorner;

    public Square(Point leftTopCorner, Point rightTopCorner, Point rightBotCorner, Point leftBotCorner) {
        this.leftTopCorner = leftTopCorner;
        this.rightTopCorner = rightTopCorner;
        this.rightBotCorner = rightBotCorner;
        this.leftBotCorner = leftBotCorner;
    }


    public Point getLeftTopCorner() {
        return leftTopCorner;
    }

    public Point getRightTopCorner() {
        return rightTopCorner;
    }

    public Point getRightBotCorner() {
        return rightBotCorner;
    }

    public Point getLeftBotCorner() {
        return leftBotCorner;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ " + this.leftTopCorner + "," + this.rightTopCorner + "," +
                this.rightBotCorner + "," + this.leftBotCorner + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Square square = (Square) obj;
        return this.leftTopCorner.equals(square.getLeftTopCorner()) &&
                this.rightTopCorner.equals(square.getRightTopCorner()) &&
                this.rightBotCorner.equals(square.getRightBotCorner()) &&
                this.leftBotCorner.equals(square.getLeftBotCorner());
    }

    @Override
    public int hashCode() {
        //TODO: Is it correct to use HashCode() to every obj inside
        int result = 17;
        result = 13 * result + this.leftTopCorner.hashCode() + this.rightTopCorner.hashCode() +
                this.leftBotCorner.hashCode() + this.rightBotCorner.hashCode();
        return result;
    }
}
