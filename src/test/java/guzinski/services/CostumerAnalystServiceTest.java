package guzinski.services;

import guzinski.model.Costumer;
import guzinski.model.FileInputData;
import guzinski.service.CostumerAnalystService;
import guzinski.service.CostumerAnalystServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CostumerAnalystServiceTest {

    private CostumerAnalystService service;

    @Before
    public void setUp() throws Exception {
        service = new CostumerAnalystServiceImpl();
    }


    @Test
    public void testNumbersOfCostumersEmptyList() {
        var input = FileInputData.builder()
            .costumers(costumersListMockEmpty())
            .build();

        var number = service.findNumberOfCostumers(input);

        assertThat(number, equalTo(0L));;
    }

    @Test
    public void testNumbersOfCostumers() {
        var input = FileInputData.builder()
            .costumers(costumersListMock())
            .build();

        var number = service.findNumberOfCostumers(input);

        assertThat(number, equalTo(3L));;
    }

    @Test
    public void testNumbersOfCostumersWithRepeated() {
        var input = FileInputData.builder()
            .costumers(costumersListMockRepeated())
            .build();

        var number = service.findNumberOfCostumers(input);

        assertThat(number, equalTo(2L));;
    }

    private PVector<Costumer> costumersListMock() {
        return TreePVector
            .singleton(Costumer.builder()
                .name("Luciano")
                .documentNumber("123456")
                .businessArea("Financial")
                .build())
            .plus(Costumer.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .businessArea("It")
                .build())
            .plus(Costumer.builder()
                .name("Emanuel")
                .documentNumber("12344234")
                .businessArea("Banking")
                .build());
    }


    private PVector<Costumer> costumersListMockRepeated() {
        return TreePVector
            .singleton(Costumer.builder()
                .name("Luciano")
                .documentNumber("123456")
                .businessArea("Financial")
                .build())
            .plus(Costumer.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .businessArea("It")
                .build())
            .plus(Costumer.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .businessArea("It")
                .build());
    }


    private PVector<Costumer> costumersListMockEmpty() {
        return TreePVector.empty();
    }

}
