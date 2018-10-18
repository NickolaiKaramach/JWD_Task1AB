package edu.etc.by.kickstart.storage;

import edu.etc.by.kickstart.entity.CubeData;

import java.util.ArrayList;
import java.util.List;

public class CubeStorage implements Warehouse<CubeData> {

    private static CubeStorage instance;
    private static List<CubeData> items;

    private CubeStorage() {
        items = new ArrayList<>();
    }

    public static CubeStorage getInstance() {
        if (instance == null) {
            //TODO: Multi-thread problem there!
            instance = new CubeStorage();
        }
        return instance;
    }

    @Override
    public void add(CubeData item) {
        items.add(item);
    }

    @Override
    public void add(Iterable<CubeData> itemsIterable) {
        for (CubeData currentCubeData : itemsIterable) {
            items.add(currentCubeData);
        }
    }

    @Override
    public void remove(CubeData item) {
        items.remove(item);
    }

    @Override
    public List<CubeData> getAll() {
        return items;
    }
}
