package edu.etc.by.kickstart.comparator;

import edu.etc.by.kickstart.entity.Cube;

import java.util.Comparator;

public class CubeVolumeComparator implements Comparator<Cube> {
    private static final int POSITIVE_NUM = 10;
    private static final int NEGATIVE_NUM = -10;

    @Override
    public int compare(Cube a, Cube b) {
        if ((a == null) || (b == null)) {
            throw new NullPointerException();
        }

        double firstCubeVolume = a.getVolume();
        double secondCubeVolume = b.getVolume();

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
