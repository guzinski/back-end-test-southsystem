package guzinski.model;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Customer {
    String documentNumber;
    String name;
    String businessArea;
}
