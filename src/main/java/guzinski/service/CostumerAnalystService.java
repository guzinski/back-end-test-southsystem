package guzinski.service;

import com.google.inject.ImplementedBy;
import guzinski.model.FileInputData;

@ImplementedBy(CostumerAnalystServiceImpl.class)
public interface CostumerAnalystService {

    Long findNumberOfCostumers(FileInputData inputData);

}
