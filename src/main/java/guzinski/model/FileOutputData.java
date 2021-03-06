package guzinski.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FileOutputData {
    Long numberOfCustomers;
    Long numberOfSellers;
    String mostExpensiveSaleId;
    String worseSellerName;
    String outputFileName;
}
