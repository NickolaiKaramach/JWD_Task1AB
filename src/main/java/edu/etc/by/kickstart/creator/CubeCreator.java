package edu.etc.by.kickstart.creator;

import edu.etc.by.kickstart.entity.Cube;
import edu.etc.by.kickstart.exception.BuilderException;

public interface CubeCreator {
    Cube createFromValidData(Double[] allCornersCoordinates) throws BuilderException;
}
