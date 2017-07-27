package ru.mishin.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Заявка
 */
public class Order {
    private String name;//имя клиента
    private OperationType type;//S - sell, продажа,  B - buy, покупка
    private StockType stockName;//имя ценной бумаги: A, B, C, D
    private Integer price;//цена за 1 штуку ценной бумаги
    private Integer numberItem;//число покупаемых и продаваемых ценных бумаг

    public Order(String name, OperationType type, StockType stockName, Integer price, Integer numberItem) {
        this.name = name;
        this.type = type;
        this.stockName = stockName;
        this.price = price;
        this.numberItem = numberItem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("name", name).
                append("type", type).
                append("stockName", stockName).
                append("price", price).
                append("numberItem", numberItem).
                toString();
    }

    public String getName() {
        return name;
    }

    public OperationType getType() {
        return type;
    }

    public StockType getStockName() {
        return stockName;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getNumberItem() {
        return numberItem;
    }
}
