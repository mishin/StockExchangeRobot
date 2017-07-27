package ru.mishin.stock;
/**
 * Created by Nikolay Mishin on 20.02.2017.
 * Read Excel file
 */

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

class StockExchangeRobot {

    private List<String> familyCode = new ArrayList<>();

    public static void main(String[] args) {
        StockExchangeRobot stockExchangeRobot = new StockExchangeRobot();
        stockExchangeRobot.readMatketFile();
    }

    public StockExchangeRobot() {
    }

    private void readMatketFile() {
        Properties prop = readProperties();
        String root = prop.getProperty("root");//"c:\\Users\\ira\\Documents\\генеалогия\\github\\";
/*        String fileName = root + prop.getProperty("readMatketFile");//"mishin_family.xlsx";
        out.println("fileName: " + fileName);
        String fileForWrite = root + prop.getProperty("writeFile");//"pedigree.xlsx";
        try {
            Sheet sheet = getSheet(fileName);
            List<Pedigree> xlsxData = readSheetPedigree(sheet);
            writePedigreeListToExcel(xlsxData, fileForWrite);
            fillTemplate(prop, xlsxData);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public Set<Client> readClient(Properties prop) throws IOException {
        List<String> lines = FileUtils.readLines(new File(prop.getProperty("root") + "/"
                + prop.getProperty("defaultPropertyPath") + "/"
                + prop.getProperty("clientsFile")));
        Set<Client> distinctClient = new LinkedHashSet<>();
        for (String line : lines) {
            String[] parts = line.split("\t");
            HashMap<StockType, Integer> currentStockMap = new HashMap<>();
            currentStockMap.put(StockType.A, parseInt(parts[2]));
            currentStockMap.put(StockType.B, parseInt(parts[3]));
            currentStockMap.put(StockType.C, parseInt(parts[4]));
            currentStockMap.put(StockType.D, parseInt(parts[5]));
            distinctClient.add(new Client(parts[0], parseInt(parts[1]), currentStockMap)); // добавляем всех клиентов в set
        }
        return distinctClient;
    }

    public ArrayList<Order> readOrders(Properties prop) throws IOException {
        List<String> lines = FileUtils.readLines(new File(prop.getProperty("root") + "/"
                + prop.getProperty("defaultPropertyPath") + "/"
                + prop.getProperty("ordersFile")));
        ArrayList<Order> listOrders = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\t");
//            currentStockMap.put(StockType.D, parseInt(parts[5]));
            listOrders.add(new Order(parts[0], getOperationType(parts[1]), getStockType(parts[2]), parseInt(parts[3]), parseInt(parts[4]))); // добавляем всех клиентов в set
        }
        return listOrders;
    }

    public OperationType getOperationType(String part) {
        OperationType type = null;
        switch (part) {
            case "s":
                type = OperationType.S;
                break;
            case "b":
                type = OperationType.B;
                break;
        }
        return type;
    }

    public StockType getStockType(String part) {
        StockType type = null;
        switch (part) {
            case "A":
                type = StockType.A;
                break;
            case "B":
                type = StockType.B;
                break;
            case "C":
                type = StockType.C;
                break;
            case "D":
                type = StockType.D;
                break;
        }
        return type;
    }

    public Properties readProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/config.properties");
            InputStreamReader isr = new InputStreamReader(input, "UTF-8");
            prop.load(isr);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
