package guzinski.model;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Costumer {
    String documentNumber;
    String name;
    String businessArea;
}
