package com.praful.stockstream.controller;

import com.praful.stockstream.model.Stock;
import com.praful.stockstream.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {

    private final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/prices")
    public List<Stock> getAllPrices(){
        return stockService.getAllStocks();
    }

    @GetMapping("/price/{symbol}")
    public Stock getPrice(@PathVariable String symbol) {
        return stockService.getStockBySymbol(symbol);
    }
}
