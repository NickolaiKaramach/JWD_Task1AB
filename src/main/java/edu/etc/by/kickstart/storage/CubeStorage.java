package edu.etc.by.kickstart.storage;

import edu.etc.by.kickstart.comparator.CubeDataIdComparator;
import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.entity.CubeData;
import edu.etc.by.kickstart.observer.Supervised;
import edu.etc.by.kickstart.observer.Watcher;
import edu.etc.by.kickstart.repository.CubeRepository;

import java.util.ArrayList;
import java.util.List;

public class CubeStorage implements Warehouse<CubeData>, Watcher, Supervised {

    private static final CubeDataIdComparator DEFAULT_COMPARATOR = new CubeDataIdComparator();
    private static CubeStorage instance;
    private List<Watcher> watchers = new ArrayList<>();
    private List<CubeData> items;

    private CubeStorage() {
        items = new ArrayList<>();
    }

    public static CubeStorage getInstance() {
        if (instance == null) {
            instance = new CubeStorage();
        }
        return instance;
    }

    @Override
    public void add(CubeData item) {
        items.add(item);
        watchers.add(item);
    }

    @Override
    public void add(Iterable<CubeData> itemsIterable) {
        for (CubeData currentCubeData : itemsIterable) {
            items.add(currentCubeData);
            watchers.add(currentCubeData);
        }
    }

    @Override
    public void remove(CubeData item) {
        items.remove(item);
    }

    @Override
    public CubeData getItemById(int id) {
        for (CubeData cubeData : items) {
            if (cubeData.getCubeId() == id) {
                return cubeData;
            }
        }
        return null;
    }

    @Override
    public List<CubeData> getAll() {
        return items;
    }

    @Override
    public void update(Object observable) {
        items.sort(DEFAULT_COMPARATOR);

        ArrayList<Cube> cubes = ((ArrayList<Cube>) observable);

        if (cubes.size() > items.size()) {
            addMissingCubeData(cubes);
        } else if (cubes.size() < items.size()) {
            removeExtraCubeData(cubes);
        } else {
            notifySubscribers(cubes);
        }

    }

    private void notifySubscribers(List<Cube> cubes) {
        for (int i = 0; i < cubes.size(); i++) {
            watchers.get(i).update(cubes.get(i));
        }
    }

    private void removeExtraCubeData(ArrayList<Cube> cubes) {
        int i = 0;
        int j = 0;
        while (i < cubes.size()) {
            while ((j < items.size()) && (cubes.get(i).getId() != items.get(j).getCubeId())) {
                remove(items.get(j));
            }
            j++;
            i++;
        }

        if (items.size() != cubes.size()) {

            int k = cubes.size();
            while (k < items.size()) {
                items.remove(k);
            }
        }
    }

    private void addMissingCubeData(ArrayList<Cube> cubes) {
        for (Cube currentCube : cubes) {
            CubeData currentCubeData = getItemById(currentCube.getId());

            if (currentCubeData == null) {

                CubeData newCubeData = new CubeData(currentCube);
                items.add(newCubeData);
                watchers.add(newCubeData);
            }
        }
    }

    @Override
    public void subscribe(Watcher watcher) {
        watchers.add(watcher);
    }

    @Override
    public void unsubscribe(Watcher watcher) {
        watchers.remove(watcher);
    }

    @Override
    //Unused method
    public void notifySubscribers() {
        notifySubscribers(CubeRepository.getInstance().getAll());
    }
}
