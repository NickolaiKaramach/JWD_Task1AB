package edu.etc.by.kickstart.comparator;

import edu.etc.by.kickstart.entity.CubeData;

import java.util.Comparator;

public class CubeDataIdComparator implements Comparator<CubeData> {
    private static final int POSITIVE_NUM = 10;
    private static final int NEGATIVE_NUM = -10;

    @Override
    public int compare(CubeData a, CubeData b) {

        int firstCubeId = a.getCubeId();
        int secondCubeId = b.getCubeId();

        if (firstCubeId == secondCubeId) {
            return 0;
        }

        if (firstCubeId > secondCubeId) {
            return POSITIVE_NUM;
        } else {
            return NEGATIVE_NUM;
        }
    }
}
