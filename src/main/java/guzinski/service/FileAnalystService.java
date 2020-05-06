package guzinski.service;

import com.google.inject.ImplementedBy;
import guzinski.model.FileInputData;
import guzinski.model.FileOutputData;

import java.util.concurrent.CompletionStage;

@ImplementedBy(FileAnalystServiceImpl.class)
public interface FileAnalystService {

    CompletionStage<FileOutputData> processFile(FileInputData inputData);

}
