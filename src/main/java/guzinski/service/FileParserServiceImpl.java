package guzinski.service;


import guzinski.model.Customer;
import guzinski.model.FileInputData;
import guzinski.model.FileOutputData;
import guzinski.model.Order;
import guzinski.model.OrderItem;
import guzinski.model.Seller;
import org.pcollections.TreePVector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileParserServiceImpl implements FileParserService {

    private final String sellerCode = "001";
    private final String customerCode = "002";
    private final String orderCode = "003";
    private final String stringSplitter = "ç";

    @Override
    public CompletionStage<FileInputData> readFile(Path path) {

        var futureLines = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readAllLines(path);
            } catch (IOException e) {
                throw new RuntimeException("Cannot read file", e);
            }
        });

        var futureSellers = futureLines.thenApply(this::findAndParseSellers);
        var futureConsumers = futureLines.thenApply(this::findAndParseCustomers);
        var futureOrders = futureLines.thenApply(this::findAndParseOrders);

        return futureSellers
            .thenCompose(sellers -> futureConsumers
                .thenCompose(customers -> futureOrders
                    .thenApply(orders -> FileInputData.builder()
                            .customers(TreePVector.from(customers))
                            .sellers(TreePVector.from(sellers))
                            .orders(TreePVector.from(orders))
                            .fileName(path.getFileName().toString())
                            .build())));

    }

    @Override
    public String saveFile(FileOutputData outputData, String pathDir) {
        createDirectoryIfNotExists(pathDir);

        var content = String.join(stringSplitter, outputData.getNumberOfCustomers().toString(),
            outputData.getNumberOfSellers().toString(),
            outputData.getMostExpensiveSaleId(),
            outputData.getWorseSellerName());
        try {
            Files.write(Paths.get(pathDir + "/" + outputData.getOutputFileName()), content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Not able to write the file", e);
        }

        return outputData.getOutputFileName();
    }

    private void createDirectoryIfNotExists(String pathDir) {
        var path = Paths.get(pathDir);
        boolean dirExists = Files.exists(path);
        if(!dirExists) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Not able to create out dir ", e);
            }
        }
    }

    private List<Order> findAndParseOrders(List<String> lines) {
        return lines.stream()
                .parallel()
                .filter(s -> s.startsWith(orderCode))
                .map(this::parseOrder)
                .collect(Collectors.toList());
    }

    private List<Customer> findAndParseCustomers(List<String> lines) {
        return lines.stream()
                .parallel()
                .filter(s -> s.startsWith(customerCode))
                .map(this::parseCustomer)
                .collect(Collectors.toList());
    }

    private List<Seller> findAndParseSellers(List<String> lines) {
        return lines.stream()
                .parallel()
                .filter(s -> s.startsWith(sellerCode))
                .map(this::parseSeller)
                .collect(Collectors.toList());
    }

    private Seller parseSeller(String sellerLine) {
        var split = sellerLine.split(stringSplitter);
        return Seller.builder()
                .documentNumber(split[1])
                .name(split[2])
                .salary(Double.parseDouble(split[3]))
                .build();
    }

    private Customer parseCustomer(String consumerLine) {
        var parts = consumerLine.split(stringSplitter);
        return Customer.builder()
                .documentNumber(parts[1])
                .name(parts[2])
                .businessArea(parts[3])
                .build();
    }

    private Order parseOrder(String orderLine) {
        var parts = orderLine.split(stringSplitter);
        var items = parts[2].replaceAll("\\[", "").replaceAll("]", "").split(",");

        List<OrderItem> orderItems = Stream.of(items)
                .map(this::parseOrderItem)
                .collect(Collectors.toList());

        return Order.builder()
                .id(parts[1])
                .items(TreePVector.from(orderItems))
                .sellerName(parts[3])
                .build();
    }

    private OrderItem parseOrderItem(String textOrderItem) {
        var parts = textOrderItem.split("-");
        return OrderItem.builder()
                .id(parts[0])
                .quantity(Long.valueOf(parts[1]))
                .price(Double.valueOf(parts[2]))
                .build();
    }






}
