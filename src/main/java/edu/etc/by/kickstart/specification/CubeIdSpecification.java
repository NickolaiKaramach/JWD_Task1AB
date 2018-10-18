package edu.etc.by.kickstart.specification;

public class CubeIdSpecification implements Specification {
    private int id;

    public CubeIdSpecification(int id) {
        this.id = id;
    }

    public boolean haveEqualId(int id) {
        return this.id == id;
    }
}
