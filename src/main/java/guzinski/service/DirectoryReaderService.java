package guzinski.service;

import com.google.inject.ImplementedBy;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@ImplementedBy(DirectoryReaderServiceImpl.class)
public interface DirectoryReaderService {

    List<Path> read(String path);

}
