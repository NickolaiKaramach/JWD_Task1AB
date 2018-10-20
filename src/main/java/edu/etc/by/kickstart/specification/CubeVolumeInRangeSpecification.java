package edu.etc.by.kickstart.specification;

public class CubeVolumeInRangeSpecification implements Specification {
    private double minRange;
    private double maxRange;

    public CubeVolumeInRangeSpecification(double minRange, double maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public boolean isInRange(double value) {
        return ((minRange <= value) &&
                (value <= maxRange));
    }
}
