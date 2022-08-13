package ua.goit.grocer;

import java.util.Objects;

public record Item(String code, double price, int promotionCount, double promotionPrice) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return code.equals(item.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "ItemInBd{" +
                "code='" + code + '\'' +
                ", price=" + price +
                ", promotionPrice=" + promotionPrice +
                ", promotionCount=" + promotionCount +
                '}';
    }
}
