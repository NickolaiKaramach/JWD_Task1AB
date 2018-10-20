package edu.etc.by.kickstart.comparator;

import edu.etc.by.kickstart.entity.Cube;

import java.util.Comparator;

public class CubeVolumeComparator implements Comparator<Cube> {
    private static final int POSITIVE_NUM = 10;
    private static final int NEGATIVE_NUM = -10;

    @Override
    public int compare(Cube a, Cube b) {

        double firstCubeVolume = a.getCubeDataLink().getVolume();
        double secondCubeVolume = b.getCubeDataLink().getVolume();

        if (firstCubeVolume == secondCubeVolume) {
            return 0;
        }

        if (firstCubeVolume > secondCubeVolume) {
            return POSITIVE_NUM;
        } else {
            return NEGATIVE_NUM;
        }
    }
}
