package guzinski.service;

import com.google.inject.ImplementedBy;
import guzinski.model.FileInputData;

@ImplementedBy(CustomerAnalystServiceImpl.class)
public interface CustomerAnalystService {

    Long findNumberOfCustomers(FileInputData inputData);

}
