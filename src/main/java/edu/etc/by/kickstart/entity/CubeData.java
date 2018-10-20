package edu.etc.by.kickstart.entity;


import edu.etc.by.kickstart.action.CubeActionImpl;
import edu.etc.by.kickstart.observer.Watcher;

import java.io.Serializable;

public class CubeData implements Serializable, Watcher {

    private static final long serialVersionUID = -329834820366083356L;
    private final static CubeActionImpl actions = new CubeActionImpl();

    private double volume;
    private double surfaceArea;

    //TODO: is it correct?
    private int cubeId;

    public CubeData(Cube cube) {
        cubeId = cube.getId();
        volume = actions.calcVolume(cube);
        surfaceArea = actions.calcSurfaceArea(cube);
        cube.setCubeDataLink(this);
    }

    public int getCubeId() {
        return cubeId;
    }

    public void setCubeId(int cubeId) {
        this.cubeId = cubeId;
    }


    public double getVolume() {
        return volume;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    @Override
    public void update(Object observable) {
        if (Object.class.equals(Cube.class)) {
            Cube cube = (Cube) observable;
            volume = actions.calcVolume(cube);
            surfaceArea = actions.calcSurfaceArea(cube);
            cubeId = cube.getId();
        }
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
