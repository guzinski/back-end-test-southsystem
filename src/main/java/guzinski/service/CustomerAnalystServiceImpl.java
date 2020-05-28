package guzinski.service;

import guzinski.model.FileInputData;

public class CustomerAnalystServiceImpl implements CustomerAnalystService {

    @Override
    public Long findNumberOfCustomers(FileInputData inputData) {
        return inputData.getCustomers()
            .stream()
            .distinct()
            .count();
    }
}
