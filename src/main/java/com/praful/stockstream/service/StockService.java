package com.praful.stockstream.service;

import com.praful.stockstream.model.Stock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockService {
    private Map<String, Stock> stockData = new HashMap<>();

    Random random = new Random();

    @Scheduled(fixedRate = 1000)
    public void updatePrices() {
        for (Stock stock : stockData.values()){
            double change = (random.nextDouble() - 0.5) * 10;   //price fluctuation
            double newPrice = stock.getPrice() + change;

            //prevent negative price
            if (newPrice < 0) {
                newPrice = 1;
            }
           String.format(stock.setPrice(newPrice)) ;
        }
        System.out.println("Price Updated: " + stockData);
    }

    public StockService() {
        stockData.put("NIFTY", new Stock("NIFTY", 22150.0));
        stockData.put("BANKNIFTY", new Stock("BANKNIFTY", 47800.0));
        stockData.put("RELIANCE", new Stock("RELIANCE", 2950.0));
    }



    public List<Stock> getAllStocks() {
        return new ArrayList<>(stockData.values());
    }

    public Stock getStockBySymbol(String symbol) {
        return stockData.get(symbol.toUpperCase());
    }
}
