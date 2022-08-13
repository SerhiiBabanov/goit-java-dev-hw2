package ua.goit.grocer;

import java.util.*;
import java.util.stream.Collectors;

public class Grocer {
    private final Repository repository;

    public Grocer(Repository repository) {
        this.repository = repository;
    }

    public double getTotalPrice(String order) {
        double totalPrice;

        if (Objects.isNull(order)) throw new  NullPointerException();
        if (order.isEmpty()) return 0.0;

        Map<Item, Long> map = new HashMap<>();
        for (Map.Entry<String, Long> e : Arrays.stream(order.strip().split(""))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .entrySet()) {
            Item key = repository.getItem(e.getKey()).orElseThrow(() -> new MissingResourceException("In repository don`t present item", repository.getClass().getName(), e.getKey()));
            map.put(key, e.getValue());
        }
        totalPrice = map
                .entrySet().stream()
                .map(itemLongEntry -> getPrice(itemLongEntry.getKey(), itemLongEntry.getValue()))
                .mapToDouble(Double::doubleValue).sum();

        return totalPrice;
    }

    private double getPrice(Item item, long count) {

        if (0 == item.promotionCount()) {
            return count * item.price();
        }

        double total;
        long promotionCountInOrder = count / item.promotionCount();
        long res = count % item.promotionCount();
        total = promotionCountInOrder * item.promotionPrice() + res * item.price();
        return total;
    }

}

