package guzinski.service;

import lombok.SneakyThrows;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class DirectoryReaderServiceImpl implements DirectoryReaderService {

    private PVector<String> readFiles = TreePVector.empty();

    @SneakyThrows
    @Override
    public List<Path> read(String pathDir) {
        return Files.walk(Paths.get(pathDir))
            .filter(Files::isRegularFile)
            .filter(path -> path.toString().toLowerCase().endsWith(".dat"))
            .filter(path -> !readFiles.contains(path.toString().toLowerCase()))
            .peek(path -> readFiles = readFiles.plus(path.toString().toLowerCase()))
            .collect(Collectors.toList());
    }
}
