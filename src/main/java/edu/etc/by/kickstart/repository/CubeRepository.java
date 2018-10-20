package edu.etc.by.kickstart.repository;

import edu.etc.by.kickstart.comparator.CubeIdComparator;
import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.entity.CubeData;
import edu.etc.by.kickstart.observer.Supervised;
import edu.etc.by.kickstart.observer.Watcher;
import edu.etc.by.kickstart.specification.CubeAreaInRangeSpecification;
import edu.etc.by.kickstart.specification.CubeIdSpecification;
import edu.etc.by.kickstart.specification.CubeVolumeInRangeSpecification;
import edu.etc.by.kickstart.specification.Specification;
import edu.etc.by.kickstart.storage.CubeStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CubeRepository implements FigureRepository<Cube>, Supervised {

    private static final CubeIdComparator DEFAULT_COMPARATOR = new CubeIdComparator();
    private static final int ID_NOT_FOUND = -1;
    private static CubeRepository instance;
    private List<Cube> items;
    private List<Watcher> watchers = new ArrayList<>();

    private CubeRepository() {
        items = new ArrayList<>();
    }

    public static CubeRepository getInstance() {
        if (instance == null) {
            instance = new CubeRepository();
        }
        return instance;
    }

    public List<Cube> getAll() {
        return items;
    }

    @Override
    public void add(Cube item) {
        items.add(item);
    }

    @Override
    public void add(Iterable<Cube> cubeIterable) {
        for (Cube currentCube : cubeIterable) {
            items.add(currentCube);
        }
        notifySubscribers();
    }

    @Override
    public void update(Cube item) {
        int searchingId;
        searchingId = findIndexOfCubeById(item.getId());

        if (searchingId != ID_NOT_FOUND) {
            items.remove(searchingId);
            items.add(item);
        }
        notifySubscribers();
    }

    @Override
    public void remove(Cube item) {
        items.remove(item);
        notifySubscribers();
    }

    @Override
    public void remove(Specification specification) {
        if (isCubeIdSpec(specification)) {
            removeByIdSpec((CubeIdSpecification) specification);
        } else if (isCubeAreaInRangeSpec(specification)) {
            removeByAreaSpec((CubeAreaInRangeSpecification) specification);
        } else if (isCubeVolumeInRangeSpec(specification)) {
            removeByVolume((CubeVolumeInRangeSpecification) specification);
        }
        notifySubscribers();
    }


    @Override
    public void sort(Comparator<Cube> comparator) {
        items.sort(comparator);
    }

    @Override
    public List<Cube> query(Specification specification) {
        //TODO: Can we optimize code?
        List<Cube> returningCubeList = new ArrayList<>();

        if (isCubeIdSpec(specification)) {

            CubeIdSpecification idSpecification;
            idSpecification = (CubeIdSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);

                if (idSpecification.haveEqualId(currentCube.getId())) {
                    returningCubeList.add(currentCube);
                }

                i++;
            }
        } else if (isCubeAreaInRangeSpec(specification)) {

            CubeAreaInRangeSpecification areaInRangeSpecification;
            areaInRangeSpecification = (CubeAreaInRangeSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);
                CubeData data = CubeStorage.getInstance().getItemById(currentCube.getId());

                if (areaInRangeSpecification.isInRange(data.getSurfaceArea())) {
                    returningCubeList.add(currentCube);
                }

                i++;
            }

        } else if (isCubeVolumeInRangeSpec(specification)) {

            CubeVolumeInRangeSpecification volumeInRangeSpecification;
            volumeInRangeSpecification = (CubeVolumeInRangeSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);
                CubeData data = CubeStorage.getInstance().getItemById(currentCube.getId());

                if (volumeInRangeSpecification.isInRange(data.getVolume())) {
                    returningCubeList.add(currentCube);
                }
                i++;
            }

        }
        return returningCubeList;
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
    public void notifySubscribers() {
        this.sort(DEFAULT_COMPARATOR);
        for (Watcher watcher : watchers) {
            watcher.update(items);
        }
    }

    private int findIndexOfCubeById(int id) {
        int searchingId = ID_NOT_FOUND;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                searchingId = i;
            }
        }

        return searchingId;
    }

    private void removeByVolume(CubeVolumeInRangeSpecification specification) {
        CubeVolumeInRangeSpecification volumeInRangeSpecification;
        volumeInRangeSpecification = specification;

        int i = 0;
        while (i < items.size()) {

            Cube currentCube = items.get(i);
            CubeData data = CubeStorage.getInstance().getItemById(currentCube.getId());

            if (volumeInRangeSpecification.isInRange(data.getVolume())) {
                items.remove(currentCube);
                i--;
            }

            i++;
        }
    }

    private void removeByAreaSpec(CubeAreaInRangeSpecification specification) {
        CubeAreaInRangeSpecification areaInRangeSpecification;
        areaInRangeSpecification = specification;

        int i = 0;
        while (i < items.size()) {

            Cube currentCube = items.get(i);
            CubeData data = CubeStorage.getInstance().getItemById(currentCube.getId());
            i++;

            if (areaInRangeSpecification.isInRange(data.getSurfaceArea())) {
                items.remove(currentCube);
                i--;
            }

        }
    }

    private void removeByIdSpec(CubeIdSpecification specification) {
        CubeIdSpecification idSpecification;
        idSpecification = specification;

        int i = 0;
        while (i < items.size()) {

            Cube currentCube = items.get(i);

            if (idSpecification.haveEqualId(currentCube.getId())) {
                items.remove(i);
                i--;
            }

            i++;
        }
    }

    private boolean isCubeIdSpec(Specification specification) {
        return specification.getClass().equals(CubeIdSpecification.class);
    }

    private boolean isCubeAreaInRangeSpec(Specification specification) {
        return specification.getClass().equals(CubeAreaInRangeSpecification.class);
    }

    private boolean isCubeVolumeInRangeSpec(Specification specification) {
        return specification.getClass().equals(CubeVolumeInRangeSpecification.class);
    }
}
