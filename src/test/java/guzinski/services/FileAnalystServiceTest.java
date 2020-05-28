package guzinski.services;

import guzinski.model.FileInputData;
import guzinski.model.FileOutputData;
import guzinski.service.CustomerAnalystService;
import guzinski.service.FileAnalystService;
import guzinski.service.FileAnalystServiceImpl;
import guzinski.service.OrderAnalystService;
import guzinski.service.SellerAnalystService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.pcollections.TreePVector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;

public class FileAnalystServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule()
        .strictness(Strictness.STRICT_STUBS);

    @Mock
    private OrderAnalystService orderAnalystService;

    @Mock
    private CustomerAnalystService customerAnalystService;

    @Mock
    private SellerAnalystService sellerAnalystService;

    private FileAnalystService service;

    @Before
    public void setUp() {
        service = new FileAnalystServiceImpl(customerAnalystService, orderAnalystService, sellerAnalystService);
    }

    @Test
    public void testProcessFileFilenameOutput() {
        var input = FileInputData.builder()
            .sellers(TreePVector.empty())
            .customers(TreePVector.empty())
            .orders(TreePVector.empty())
            .fileName("filename.dat")
            .build();

        Mockito.when(customerAnalystService.findNumberOfCustomers(any()))
            .thenReturn(12L);
        Mockito.when(orderAnalystService.findExpensiveSaleId(any()))
            .thenReturn("1");
        Mockito.when(sellerAnalystService.findNumberOfSellers(any()))
            .thenReturn(10L);
        Mockito.when(sellerAnalystService.findWorseSellerName(any()))
            .thenReturn("Luciano");

        FileOutputData output = service.processFile(input).toCompletableFuture().join();

        assertThat(output.getOutputFileName(), equalTo("filename.done.dat"));
        assertThat(output.getWorseSellerName(), equalTo("Luciano"));
        assertThat(output.getNumberOfSellers(), equalTo(10L));
        assertThat(output.getNumberOfCustomers(), equalTo(12L));
        assertThat(output.getMostExpensiveSaleId(), equalTo("1"));

    }


}
