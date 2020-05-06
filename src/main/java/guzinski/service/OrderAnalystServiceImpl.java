package guzinski.service;

import guzinski.model.FileInputData;
import guzinski.model.Order;
import guzinski.model.OrderItem;

import java.util.Comparator;

public class OrderAnalystServiceImpl implements OrderAnalystService {

    @Override
    public String findExpensiveSaleId(FileInputData inputData) {
        return inputData.getOrders()
                .stream()
                .max(Comparator.comparingDouble(this::orderAmount))
                .map(Order::getId)
                .orElse("");
    }

    @Override
    public double orderAmount(Order order) {
        return order.getItems().stream()
                .mapToDouble(this::orderItemTotalAmount)
                .sum();
    }

    private double orderItemTotalAmount(OrderItem orderItem) {
        return orderItem.getQuantity() * orderItem.getPrice();
    }

}
