package guzinski.services;

import guzinski.model.Customer;
import guzinski.model.FileInputData;
import guzinski.service.CustomerAnalystService;
import guzinski.service.CustomerAnalystServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CustomerAnalystServiceTest {

    private CustomerAnalystService service;

    @Before
    public void setUp() {
        service = new CustomerAnalystServiceImpl();
    }


    @Test
    public void testNumbersOfcustomersEmptyList() {
        var input = FileInputData.builder()
            .customers(customersListMockEmpty())
            .build();

        var number = service.findNumberOfCustomers(input);

        assertThat(number, equalTo(0L));
    }

    @Test
    public void testNumbersOfCustomers() {
        var input = FileInputData.builder()
            .customers(customersListMock())
            .build();

        var number = service.findNumberOfCustomers(input);

        assertThat(number, equalTo(3L));
    }

    @Test
    public void testNumbersOfCustomersWithRepeated() {
        var input = FileInputData.builder()
            .customers(customersListMockRepeated())
            .build();

        var number = service.findNumberOfCustomers(input);

        assertThat(number, equalTo(2L));
    }

    private PVector<Customer> customersListMock() {
        return TreePVector
            .singleton(Customer.builder()
                .name("Luciano")
                .documentNumber("123456")
                .businessArea("Financial")
                .build())
            .plus(Customer.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .businessArea("It")
                .build())
            .plus(Customer.builder()
                .name("Emanuel")
                .documentNumber("12344234")
                .businessArea("Banking")
                .build());
    }


    private PVector<Customer> customersListMockRepeated() {
        return TreePVector
            .singleton(Customer.builder()
                .name("Luciano")
                .documentNumber("123456")
                .businessArea("Financial")
                .build())
            .plus(Customer.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .businessArea("It")
                .build())
            .plus(Customer.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .businessArea("It")
                .build());
    }


    private PVector<Customer> customersListMockEmpty() {
        return TreePVector.empty();
    }

}
