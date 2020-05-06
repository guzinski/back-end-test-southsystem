package guzinski.services;

import guzinski.model.FileInputData;
import guzinski.model.Order;
import guzinski.model.OrderItem;
import guzinski.service.OrderAnalystService;
import guzinski.service.OrderAnalystServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.pcollections.TreePVector;

public class OrderAnalystServiceTest {


    private OrderAnalystService service;

    @Before
    public void setUp() throws Exception {
        service = new OrderAnalystServiceImpl();
    }

    @Test
    public void testOrderAmount100() {
        var order = orderAmount100Mock();

        var amount = service.orderAmount(order);

        MatcherAssert.assertThat(amount, Matchers.equalTo(100d));
    }

    @Test
    public void testOrderAmount80() {
        var order = orderAmount80Mock();

        var amount = service.orderAmount(order);

        MatcherAssert.assertThat(amount, Matchers.equalTo(80d));
    }

    @Test
    public void testOrderAmount350() {
        var order = orderAmount350Mock();

        var amount = service.orderAmount(order);

        MatcherAssert.assertThat(amount, Matchers.equalTo(350d));
    }

    @Test
    public void testMostExpensiveOrder() {
        var input = FileInputData.builder()
            .orders(TreePVector
                .singleton(orderAmount350Mock())
                .plus(orderAmount80Mock())
                .plus(orderAmount100Mock()))
            .build();

        var expensiveSaleId = service.findExpensiveSaleId(input);

        MatcherAssert.assertThat(expensiveSaleId, Matchers.equalTo("2"));
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
            .sellerName("test")
            .items(TreePVector.singleton(item1).plus(item2))
            .id("1")
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
            .sellerName("test")
            .items(TreePVector.singleton(item1).plus(item2))
            .id("2")
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
            .sellerName("test")
            .items(TreePVector.singleton(item1))
            .id("3")
            .build();
    }


}
