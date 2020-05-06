package guzinski.service;

import com.google.inject.ImplementedBy;
import guzinski.model.FileInputData;
import guzinski.model.Order;

@ImplementedBy(OrderAnalystServiceImpl.class)
public interface OrderAnalystService {

    String findExpensiveSaleId(FileInputData inputData);

    double orderAmount(Order order);
}
