package guzinski.service;

import com.google.inject.ImplementedBy;
import guzinski.model.FileInputData;

@ImplementedBy(SellerAnalystServiceImpl.class)
public interface SellerAnalystService {

    Long findNumberOfSellers(FileInputData inputData);

    String findWorseSellerName(FileInputData inputData);

}
