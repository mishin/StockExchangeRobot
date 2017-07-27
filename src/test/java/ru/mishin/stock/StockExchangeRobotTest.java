package ru.mishin.stock;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

/**
 * JUnit тесты.
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class StockExchangeRobotTest {
    private Map<String, String> expectedProperties = new HashMap<>();
    private StockExchangeRobot stockExchangeRobot;
    private Properties prop = null;

    public StockExchangeRobotTest() {
    }

    @org.junit.Before
    public void setUp() throws Exception {
        expectedProperties.put("root", "c:/Users/Mishin737/Documents/work_line/24072017/StockExchangeRobot");
        expectedProperties.put("clientsFile", "clients.txt");
        expectedProperties.put("ordersFile", "orders.txt");
        expectedProperties.put("targetFile", "result.txt");
        expectedProperties.put("defaultPropertyPath", "src/main/resources");

        stockExchangeRobot = new StockExchangeRobot();
        prop = stockExchangeRobot.readProperties();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldReadLocalPropFile() {
        assertNotNull(prop);
        assertFalse(prop.isEmpty());
        for (final Map.Entry<Object, Object> e : prop.entrySet()) {
            assertEquals(expectedProperties.get(e.getKey()), e.getValue());
        }
    }

    @Test
    public void shouldReadCsv() throws Exception {
        StockExchangeRobot stockExchangeRobot = new StockExchangeRobot();
        Set<Client> distinctClient = stockExchangeRobot.readClient(prop);
        for (Client client : distinctClient) {
            System.out.println(client.toString());
        }
    }

    @Test
    public void shouldCreateClient() throws Exception {
        HashMap<StockType, Integer> currentStockMap = new HashMap<>();
//            C1	1000	130	240	760	320
        currentStockMap.put(StockType.A, 130);
        currentStockMap.put(StockType.B, 240);
        currentStockMap.put(StockType.C, 760);
        currentStockMap.put(StockType.D, 320);
        assertEquals((new Client("C1", 1000, currentStockMap)).toString(),
                "Client [name=C1, balance=1000 , A = 130 , B = 240 , C = 760 , D = 320]"); // добавляем всех клиентов в set
    }

    @Test
    public void shouldReadOrders() throws Exception {
        StockExchangeRobot stockExchangeRobot = new StockExchangeRobot();
        ArrayList<Order> orders = stockExchangeRobot.readOrders(prop);
        /*for (Order order : orders) {
            System.out.println(order.toString());
        }*/
    }


    @Test
    public void shouldCreateOrders() throws Exception {
        StockExchangeRobot stockExchangeRobot = new StockExchangeRobot();
//        C8	b	C	15	4
        Order order = new Order("C8",
                stockExchangeRobot.getOperationType("b"),
                stockExchangeRobot.getStockType("C"),
                parseInt("15"),
                parseInt("4"));
        System.out.println(order.toString());
//        ArrayList<Order> orders = stockExchangeRobot.readOrders(prop);
        /*for (Order order : orders) {
            System.out.println(order.toString());
        }*/
    }

    @Test
    public void shouldMakeBalance() throws Exception {
        StockExchangeRobot stockExchangeRobot = new StockExchangeRobot();
        ArrayList<Order> orders = stockExchangeRobot.readOrders(prop);
        Set<Client> distinctClient = stockExchangeRobot.readClient(prop);
        for (Order order : orders) {
//            System.out.println(order.toString());
        }
    }

}
