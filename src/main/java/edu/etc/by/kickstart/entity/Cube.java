package edu.etc.by.kickstart.entity;

import edu.etc.by.kickstart.observer.Supervised;
import edu.etc.by.kickstart.observer.Watcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cube implements Supervised, Serializable {

    private static final long serialVersionUID = -7162632042163620279L;
    transient private List<Watcher> listeners = new ArrayList<>();
    private Square topSquare;
    private Square botSquare;
    private int id;

    public Cube(int id, Square topSquare, Square botSquare) {
        this.id = id;
        this.topSquare = topSquare;
        this.botSquare = botSquare;
    }

    @Override
    public void subscribe(Watcher watcher) {
        listeners.add(watcher);
    }

    @Override
    public void unsubscribe(Watcher watcher) {
        listeners.remove(watcher);
    }

    @Override
    public void notifySubscribers() {
        for (Watcher watcher : listeners) {
            watcher.update();
        }
    }

    public int getId() {
        return id;
    }

    //TODO: Do we need it?
    public void setId(int id) {
        this.id = id;
    }

    public CubeData getCubeData() {
        return (CubeData) listeners.get(0);
    }

    public Square getTopSquare() {
        return topSquare;
    }

    public void setTopSquare(Square topSquare) {
        this.topSquare = topSquare;
        notifySubscribers();
    }

    public Square getBotSquare() {
        return botSquare;
    }

    public void setBotSquare(Square botSquare) {
        this.botSquare = botSquare;
        notifySubscribers();
    }

    //TODO: Do we have another way to access CubeData?
    public double getSurfaceArea() {
        if (listeners.size() > 0) {
            return ((CubeData) listeners.get(0)).getSurfaceArea();
        }
        return 0.0;
    }

    public double getVolume() {
        if (listeners.size() > 0) {
            return ((CubeData) listeners.get(0)).getVolume();
        }
        return 0.0;
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
