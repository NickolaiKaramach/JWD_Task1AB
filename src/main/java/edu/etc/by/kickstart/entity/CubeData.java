package edu.etc.by.kickstart.entity;


import edu.etc.by.kickstart.action.CubeActionImpl;
import edu.etc.by.kickstart.observer.Watcher;

import java.io.Serializable;

public class CubeData implements Watcher, Serializable {

    private static final long serialVersionUID = -2174955228907297350L;
    private final static CubeActionImpl actions = new CubeActionImpl();

    private double volume;
    private double surfaceArea;

    //TODO: is it correct?
    private Cube observable;

    public CubeData(Cube cube) {
        observable = cube;
        cube.subscribe(this);
        volume = actions.calcVolume(cube);
        surfaceArea = actions.calcSurfaceArea(cube);
    }


    @Override
    public void update() {
        volume = actions.calcVolume(observable);
        surfaceArea = actions.calcSurfaceArea(observable);
    }

    public Cube getObservable() {
        return observable;
    }

    public double getVolume() {
        return volume;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        CubeData cubeData = (CubeData) obj;
        return this.surfaceArea == cubeData.getSurfaceArea() &&
                this.volume == cubeData.getVolume();
    }

    @Override
    public int hashCode() {
        int result = 17;
        return (int) (result * 43 + surfaceArea * 13 + volume * 53);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "volume=" + volume +
                ", surfaceArea=" + surfaceArea +
                '}';
    }
}
