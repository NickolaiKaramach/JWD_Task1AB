package edu.etc.by.kickstart.storage;

import java.util.List;

public interface Warehouse<FigureType> {
    void add(FigureType item);

    void add(Iterable<FigureType> items);

    void remove(FigureType item);

    FigureType getItemById(int id);

    List<FigureType> getAll();
}
