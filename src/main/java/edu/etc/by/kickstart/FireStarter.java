package edu.etc.by.kickstart;

import edu.etc.by.kickstart.comparator.CubeVolumeComparator;
import edu.etc.by.kickstart.creator.CubeCreatorImpl;
import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.entity.CubeData;
import edu.etc.by.kickstart.parser.CubeParser;
import edu.etc.by.kickstart.receiver.FileDataReceiverImpl;
import edu.etc.by.kickstart.repository.CubeRepository;
import edu.etc.by.kickstart.specification.CubeAreaInRangeSpecification;
import edu.etc.by.kickstart.specification.CubeIdSpecification;
import edu.etc.by.kickstart.specification.CubeVolumeInRangeSpecification;
import edu.etc.by.kickstart.storage.CubeStorage;

import java.util.ArrayList;
import java.util.List;


public class FireStarter {
    public static void main(String[] args) throws Exception {
        CubeStorage storage = CubeStorage.getInstance();
        CubeRepository repository = CubeRepository.getInstance();

        FileDataReceiverImpl receiver = new FileDataReceiverImpl();
        CubeParser parser = new CubeParser();
        CubeCreatorImpl creator = new CubeCreatorImpl();

        Double[][] coordinates =
                parser.extractCubeBuildingData(receiver.readAll("repository/input.txt"));


        //Logic:
        List<Cube> cubes = new ArrayList<>();
        List<CubeData> cubeData = new ArrayList<>();

        for (Double[] currentLine : coordinates) {
            Cube createdCube = creator.createFromValidData(currentLine);

            cubes.add(createdCube);
            cubeData.add(new CubeData(createdCube));
        }

        repository.add(cubes);
        storage.add(cubeData);

        System.out.println(cubeData + "\n" + cubes);

        CubeIdSpecification specification1 = new CubeIdSpecification(2);
        CubeAreaInRangeSpecification specification2 = new CubeAreaInRangeSpecification(0, 54);
        CubeVolumeInRangeSpecification specification3 = new CubeVolumeInRangeSpecification(8, 8);


        repository.remove(specification1);

        CubeVolumeComparator cubeAreaComparator = new CubeVolumeComparator();
        repository.sort(cubeAreaComparator);

        System.out.println("Specification(id = 2):\n" + repository.query(specification1));
        System.out.println("Specification(area in [0,54]):\n" + repository.query(specification2));
        System.out.println("Specification(volume in [8,8]):\n" + repository.query(specification3));
        System.out.println(storage.getAll());
    }
}
