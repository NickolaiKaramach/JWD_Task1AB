package edu.etc.by.kickstart;

import edu.etc.by.kickstart.comparator.CubeAreaComparator;
import edu.etc.by.kickstart.creator.CubeCreatorImpl;
import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.entity.CubeData;
import edu.etc.by.kickstart.parser.CubeParser;
import edu.etc.by.kickstart.receiver.FileDataReceiverImpl;
import edu.etc.by.kickstart.repository.CubeRepository;
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

        repository.subscribe(storage);
        repository.add(cubes);


        CubeAreaComparator comparator = new CubeAreaComparator();
        repository.sort(comparator);
        System.out.println("Cubes:\n" + repository.getAll());
        System.out.println("CubesData:\n" + storage.getAll());


    }
}
