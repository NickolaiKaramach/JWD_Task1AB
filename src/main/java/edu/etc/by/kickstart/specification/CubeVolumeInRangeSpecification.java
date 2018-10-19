package edu.etc.by.kickstart.specification;

public class CubeVolumeInRangeSpecification implements Specification {
    private double min_range;
    private double max_range;

    public CubeVolumeInRangeSpecification(double min_range, double max_range) {
        this.min_range = min_range;
        this.max_range = max_range;
    }

    public boolean isInRange(double value) {
        return ((min_range <= value) &&
                (value <= max_range));
    }
}
