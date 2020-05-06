package guzinski.services;

import guzinski.model.FileInputData;
import guzinski.model.Order;
import guzinski.model.OrderItem;
import guzinski.model.Seller;
import guzinski.service.OrderAnalystService;
import guzinski.service.SellerAnalystService;
import guzinski.service.SellerAnalystServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class SellerAnalystServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule()
        .strictness(Strictness.STRICT_STUBS);

    @Mock
    private OrderAnalystService orderAnalystService;


    private SellerAnalystService service;

    @Before
    public void setUp() throws Exception {
        service = new SellerAnalystServiceImpl(orderAnalystService);
    }


    @Test
    public void testNumbersOfSellersEmptyList() {
        var input = FileInputData.builder()
            .sellers(sellersListMockEmpty())
            .build();

        var number = service.findNumberOfSellers(input);

        assertThat(number, equalTo(0L));;
    }

    @Test
    public void testNumbersOfSellers() {
        var input = FileInputData.builder()
            .sellers(sellersListMock())
            .build();

        var number = service.findNumberOfSellers(input);

        assertThat(number, equalTo(3L));;
    }

    @Test
    public void testNumbersOfSellersWithRepeated() {
        var input = FileInputData.builder()
            .sellers(sellersListMockRepeated())
            .build();

        var number = service.findNumberOfSellers(input);

        assertThat(number, equalTo(2L));;
    }

    @Test
    public void testWorseSeller() {
        when(orderAnalystService.orderAmount(eq(orderAmount80Mock())))
            .thenReturn(80d);
        when(orderAnalystService.orderAmount(eq(orderAmount100Mock())))
            .thenReturn(100d);
        when(orderAnalystService.orderAmount(eq(orderAmount120Mock())))
            .thenReturn(120d);
        when(orderAnalystService.orderAmount(eq(orderAmount350Mock())))
            .thenReturn(350d);

        var input = FileInputData.builder()
            .orders(getOrdersMock())
            .sellers(sellersListMock())
            .build();
        var sellerName = service.findWorseSellerName(input);

        assertThat(sellerName, equalTo("Fabricio"));
    }

    private PVector<Seller> sellersListMock() {
        return TreePVector
            .singleton(Seller.builder()
                .name("Luciano")
                .documentNumber("123456")
                .salary(1000d)
                .build())
            .plus(Seller.builder()
                .name("Fabricio")
                .documentNumber("1234568")
                .salary(1000d)
                .build())
            .plus(Seller.builder()
                .name("Emanuel")
                .documentNumber("42656456")
                .salary(1000d)
                .build());
    }


    private PVector<Seller> sellersListMockRepeated() {
        return TreePVector
            .singleton(Seller.builder()
                .name("Luciano")
                .documentNumber("123456")
                .salary(1000d)
                .build())
            .plus(Seller.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .salary(1000d)
                .build())
            .plus(Seller.builder()
                .name("Fabrício")
                .documentNumber("1234568")
                .salary(1000d)
                .build());
    }


    private PVector<Seller> sellersListMockEmpty() {
        return TreePVector.empty();
    }

    private Order orderAmount100Mock() {
        var item1 = OrderItem
            .builder()
            .id("1")
            .quantity(5L)
            .price(5d)
            .build();

        var item2 = OrderItem
            .builder()
            .id("2")
            .quantity(3L)
            .price(25d)
            .build();

        return Order
            .builder()
            .sellerName("Emanuel")
            .items(TreePVector.singleton(item1).plus(item2))
            .id("1")
            .build();
    }

    private Order orderAmount120Mock() {
        var item1 = OrderItem
            .builder()
            .id("1")
            .quantity(9L)
            .price(5d)
            .build();

        var item2 = OrderItem
            .builder()
            .id("2")
            .quantity(3L)
            .price(25d)
            .build();

        return Order
            .builder()
            .sellerName("Emanuel")
            .items(TreePVector.singleton(item1).plus(item2))
            .id("2")
            .build();
    }

    private Order orderAmount350Mock() {
        var item1 = OrderItem
            .builder()
            .id("1")
            .quantity(50L)
            .price(5d)
            .build();

        var item2 = OrderItem
            .builder()
            .id("2")
            .quantity(4L)
            .price(25d)
            .build();

        return Order
            .builder()
            .sellerName("Luciano")
            .items(TreePVector.singleton(item1).plus(item2))
            .id("3")
            .build();
    }


    private Order orderAmount80Mock() {
        var item1 = OrderItem
            .builder()
            .id("1")
            .quantity(16L)
            .price(5d)
            .build();

        return Order
            .builder()
            .sellerName("Fabricio")
            .items(TreePVector.singleton(item1))
            .id("4")
            .build();
    }


    private PVector<Order> getOrdersMock() {
        return TreePVector
            .singleton(orderAmount80Mock())
            .plus(orderAmount100Mock())
            .plus(orderAmount120Mock())
            .plus(orderAmount350Mock());
    }


}
