package edu.etc.by.kickstart.entity;

import java.io.Serializable;

public class Cube implements Serializable {

    private static final long serialVersionUID = 5661641435474626181L;
    private Square topSquare;
    private Square botSquare;
    private int id;
    private CubeData cubeDataLink;

    public Cube(int id, Square topSquare, Square botSquare) {
        this.id = id;
        this.topSquare = topSquare;
        this.botSquare = botSquare;
    }

    public int getId() {
        return id;
    }

    public CubeData getCubeDataLink() {
        return cubeDataLink;
    }

    public void setCubeDataLink(CubeData cubeDataLink) {
        this.cubeDataLink = cubeDataLink;
    }

    //TODO: Do we need it?
    public void setId(int id) {
        this.id = id;
    }

    public Square getTopSquare() {
        return topSquare;
    }

    public void setTopSquare(Square topSquare) {
        this.topSquare = topSquare;
    }

    public Square getBotSquare() {
        return botSquare;
    }

    public void setBotSquare(Square botSquare) {
        this.botSquare = botSquare;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Cube cube = (Cube) obj;
        return (this.topSquare.equals(cube.topSquare) &&
                this.botSquare.equals(cube.botSquare)) &&
                (id == cube.getId());
    }

    @Override
    public int hashCode() {
        //TODO: Replace with typical HashCode()
        int result = 11;
        result = 53 * result + this.topSquare.hashCode() + this.botSquare.hashCode() + id * 13;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": id(" + this.id + ") {" +
                this.topSquare + "," + this.botSquare +
                '}';
    }
}
