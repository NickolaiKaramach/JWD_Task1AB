package edu.etc.by.kickstart.comparator;

import edu.etc.by.kickstart.entity.Cube;

import java.util.Comparator;

public class CubeAreaComparator implements Comparator<Cube> {
    private static final int POSITIVE_NUM = 10;
    private static final int NEGATIVE_NUM = -10;

    @Override
    public int compare(Cube a, Cube b) {

        double firstCubeArea = a.getCubeDataLink().getSurfaceArea();
        double secondCubeArea = b.getCubeDataLink().getSurfaceArea();

        if (firstCubeArea == secondCubeArea) {
            return 0;
        }

        if (firstCubeArea > secondCubeArea) {
            return POSITIVE_NUM;
        } else {
            return NEGATIVE_NUM;
        }
    }
}
