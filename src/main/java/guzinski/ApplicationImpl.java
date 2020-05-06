package guzinski;

import guzinski.service.DirectoryReaderService;
import guzinski.service.FileAnalystService;
import guzinski.service.FileParserService;

import javax.inject.Inject;

public class ApplicationImpl implements Application {
    private final String pathIn;
    private final String pathOut;
    private final DirectoryReaderService readerService;
    private final FileParserService fileParserService;
    private final FileAnalystService fileAnalystService;

    @Inject
    public ApplicationImpl(DirectoryReaderService readerService, FileParserService fileParserService, FileAnalystService fileAnalystService) {
        this.readerService = readerService;
        this.fileParserService = fileParserService;
        this.fileAnalystService = fileAnalystService;
        this.pathIn = System.getenv("IN_DIR");
        this.pathOut = System.getenv("OUT_DIR");
    }

    @Override
    public void start() {
        while (true) {
            readerService.read(pathIn)
                .stream()
                .map(fileParserService::readFile)
                .map(fileInputDataFuture -> fileInputDataFuture.thenCompose(fileAnalystService::processFile))
                .map(fileOutputDataFuture -> fileOutputDataFuture.thenApply(outputData -> fileParserService.saveFile(outputData, pathOut)))
                .forEach(fileNameFuture -> fileNameFuture.thenAccept(System.out::println));
        }
    }


}
