package guzinski.service;

import guzinski.model.FileInputData;
import guzinski.model.Seller;

import javax.inject.Inject;
import java.util.Comparator;

public class SellerAnalystServiceImpl implements SellerAnalystService {

    private final OrderAnalystService orderAnalystService;

    @Inject
    public SellerAnalystServiceImpl(OrderAnalystService orderAnalystService) {
        this.orderAnalystService = orderAnalystService;
    }

    @Override
    public Long findNumberOfSellers(FileInputData inputData) {
        return inputData.getSellers()
                .stream()
                .distinct()
                .count();
    }

    @Override
    public String findWorseSellerName(FileInputData inputData) {
        return inputData.getSellers()
                .stream()
                .min(Comparator.comparingDouble(seller -> totalAmountSoldBySeller(seller, inputData)))
                .map(Seller::getName)
                .orElse("");
    }

    private double totalAmountSoldBySeller(Seller seller, FileInputData inputData) {
        return inputData.getOrders()
                .stream()
                .filter(order -> order.getSellerName().equals(seller.getName()))
                .mapToDouble(orderAnalystService::orderAmount)
                .peek(System.out::println)
                .sum();
    }

}
