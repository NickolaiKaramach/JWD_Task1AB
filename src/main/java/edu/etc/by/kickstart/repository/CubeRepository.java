package edu.etc.by.kickstart.repository;

import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.specification.CubeAreaInRangeSpecification;
import edu.etc.by.kickstart.specification.CubeIdSpecification;
import edu.etc.by.kickstart.specification.CubeVolumeInRangeSpecification;
import edu.etc.by.kickstart.specification.Specification;
import edu.etc.by.kickstart.storage.CubeStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CubeRepository implements FigureRepository<Cube> {

    private static final int ID_NOT_FOUND = -1;
    private static CubeRepository instance;
    private static List<Cube> items;

    private CubeRepository() {
        items = new ArrayList<>();
    }

    public static CubeRepository getInstance() {
        if (instance == null) {
            //TODO: Multi-thread problem there!
            instance = new CubeRepository();
        }
        return instance;
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
    }

    @Override
    public void update(Cube item) {
        int searchingId;
        searchingId = findIndexOfCubeById(item.getId());

        if (searchingId != ID_NOT_FOUND) {
            items.remove(searchingId);
            items.add(item);
        }
    }

    @Override
    public void remove(Cube item) {
        items.remove(item);
        CubeStorage.getInstance().remove(item.getCubeData());
    }

    @Override
    public void remove(Specification specification) {
        //TODO: is it a stable code?
        if (specification.getClass().equals(CubeIdSpecification.class)) {

            CubeIdSpecification idSpecification;
            idSpecification = (CubeIdSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);
                if (idSpecification.haveEqualId(currentCube.getId())) {
                    items.remove(i);
                    CubeStorage.getInstance().remove(currentCube.getCubeData());
                    i--;
                }
                i++;
            }
        } else if (specification.getClass().equals(CubeAreaInRangeSpecification.class)) {

            CubeAreaInRangeSpecification areaInRangeSpecification;
            areaInRangeSpecification = (CubeAreaInRangeSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);
                i++;
                if (areaInRangeSpecification.isInRange(currentCube.getSurfaceArea())) {
                    items.remove(currentCube);
                    CubeStorage.getInstance().remove(currentCube.getCubeData());
                    i--;
                }
            }

        } else if (specification.getClass().equals(CubeVolumeInRangeSpecification.class)) {
            CubeVolumeInRangeSpecification volumeInRangeSpecification;
            volumeInRangeSpecification = (CubeVolumeInRangeSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);

                if (volumeInRangeSpecification.isInRange(currentCube.getSurfaceArea())) {
                    items.remove(currentCube);
                    CubeStorage.getInstance().remove(currentCube.getCubeData());
                    i--;
                }
                i++;
            }

        }
    }


    @Override
    public void sort(Comparator<Cube> comparator) {
        items.sort(comparator);
    }

    @Override
    public List<Cube> query(Specification specification) {
        //TODO: Can we optimize code?
        List<Cube> returningCubeList = new ArrayList<>();

        if (specification.getClass().equals(CubeIdSpecification.class)) {

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
        } else if (specification.getClass().equals(CubeAreaInRangeSpecification.class)) {

            CubeAreaInRangeSpecification areaInRangeSpecification;
            areaInRangeSpecification = (CubeAreaInRangeSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);

                if (areaInRangeSpecification.isInRange(currentCube.getSurfaceArea())) {
                    returningCubeList.add(currentCube);
                }
                i++;
            }

        } else if (specification.getClass().equals(CubeVolumeInRangeSpecification.class)) {
            CubeVolumeInRangeSpecification volumeInRangeSpecification;
            volumeInRangeSpecification = (CubeVolumeInRangeSpecification) specification;

            int i = 0;
            while (i < items.size()) {
                Cube currentCube = items.get(i);

                if (volumeInRangeSpecification.isInRange(currentCube.getVolume())) {
                    returningCubeList.add(currentCube);
                }
                i++;
            }

        }
        return returningCubeList;
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
}
