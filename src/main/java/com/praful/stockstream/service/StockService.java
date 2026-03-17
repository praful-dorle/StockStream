package com.praful.stockstream.service;

import com.praful.stockstream.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockService {
    private Map<String, Stock> stockData = new HashMap<>();

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
