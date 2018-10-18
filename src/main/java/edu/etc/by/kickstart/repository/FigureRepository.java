package edu.etc.by.kickstart.repository;

import edu.etc.by.kickstart.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface FigureRepository<FigureType> {
    void add(FigureType item);

    void add(Iterable<FigureType> items);

    void update(FigureType item);

    void remove(FigureType item);

    void remove(Specification specification);

    void sort(Comparator<FigureType> comparator);

    List<FigureType> query(Specification specification);

}
