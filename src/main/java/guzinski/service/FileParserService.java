package guzinski.service;

import com.google.inject.ImplementedBy;
import guzinski.model.FileInputData;
import guzinski.model.FileOutputData;

import java.nio.file.Path;
import java.util.concurrent.CompletionStage;

@ImplementedBy(FileParserServiceImpl.class)
public interface FileParserService {

    CompletionStage<FileInputData> readFile(Path path);

    String saveFile(FileOutputData outputData, String pathDir);

}
