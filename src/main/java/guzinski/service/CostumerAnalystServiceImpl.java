package guzinski.service;

import guzinski.model.FileInputData;

public class CostumerAnalystServiceImpl implements CostumerAnalystService {

    @Override
    public Long findNumberOfCostumers(FileInputData inputData) {
        return inputData.getCostumers()
            .stream()
            .distinct()
            .count();
    }
}
