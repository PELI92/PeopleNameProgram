package com.peli.coding.test.input;

import java.io.File;
import java.util.Optional;

public class FileManager {

    public static File getFile(String resourceName) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Optional<File> optionalFile = Optional.of(new File(classLoader.getResource(resourceName).getFile()));
        return optionalFile.orElseThrow(() -> new Exception(String.format("Resource not found: %s", resourceName)));
    }

}
