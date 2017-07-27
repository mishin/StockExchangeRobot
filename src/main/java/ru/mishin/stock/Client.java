package ru.mishin.stock;

import java.util.HashMap;

public class Client {
    private Integer balance = 0; // баланс клиента по долларам
    private String name;//имя клиента
    private HashMap<StockType, Integer> stockMap;//баланс по ценным бумагам A, B, C, D

    // constructor
    public Client(String clientName, Integer initialBalance, HashMap<StockType, Integer> clientStockMap) {
// validate that initialBalance is greater than 0;
// if it is not, balance is initialized to the default value 0
        if (initialBalance > 0)
            balance = initialBalance;
        this.name = clientName;
        this.stockMap = clientStockMap;
    } // end Client constructor


    // credit (add) an amount to the account
    public void credit(Integer amount) {
        balance = balance + amount; // add amount to balance
    } // end method credit

    //withdraw (subtract) an amount to the account
    public void withdraw(Integer amount) {
        balance = balance - amount; // subtract amount to balance
    } // end method withdraw

    // return the account balance
    public double getBalance() {
        return balance; // gives the value of balance to the calling method
    } // end method getBalance

    @Override
    public String toString() {
        return "Client [name=" + name + ", balance=" + balance + getClientStock() + "]";
    }

    private String getClientStock() {
        String answer = "";
        answer += getStockDump(StockType.A);
        answer += getStockDump(StockType.B);
        answer += getStockDump(StockType.C);
        answer += getStockDump(StockType.D);
        return answer;
    }

    private String getStockDump(StockType stockType) {
        return " , " + stockType + " = " + stockMap.get(stockType);
    }
} // end class Client