package guzinski.service;

import guzinski.model.FileInputData;
import guzinski.model.FileOutputData;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class FileAnalystServiceImpl implements FileAnalystService {

    private final CostumerAnalystService costumerAnalystService;
    private final OrderAnalystService orderAnalystService;
    private final SellerAnalystService sellerAnalystService;

    @Inject
    public FileAnalystServiceImpl(CostumerAnalystService costumerAnalystService,
                                  OrderAnalystService orderAnalystService,
                                  SellerAnalystService sellerAnalystService) {
        this.costumerAnalystService = costumerAnalystService;
        this.orderAnalystService = orderAnalystService;
        this.sellerAnalystService = sellerAnalystService;
    }


    @Override
    public CompletionStage<FileOutputData> processFile(FileInputData inputData) {
        return CompletableFuture.supplyAsync(() -> {
            var expensiveSaleId = orderAnalystService.findExpensiveSaleId(inputData);
            var numberOfCostumers = costumerAnalystService.findNumberOfCostumers(inputData);
            var numberOfSellers = sellerAnalystService.findNumberOfSellers(inputData);
            var worseSellerName = sellerAnalystService.findWorseSellerName(inputData);
            var outputFileName = this.getOutputFileName(inputData.getFileName());
            return FileOutputData.builder()
                    .mostExpensiveSaleId(expensiveSaleId)
                    .numberOfSellers(numberOfSellers)
                    .numberOfCostumers(numberOfCostumers)
                    .worseSellerName(worseSellerName)
                    .outputFileName(outputFileName)
                    .build();
        });
    }

    private String getOutputFileName(String inputFilename) {
        var fileName = inputFilename.replaceAll("\\.dat?$", "");
        return fileName.concat(".done.dat");
    }

}
