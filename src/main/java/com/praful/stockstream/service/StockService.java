package com.praful.stockstream.service;

import com.praful.stockstream.model.Stock;
import com.praful.stockstream.model.StockEntity;
import com.praful.stockstream.repository.StockRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StockService {

    private final StockRepository stockRepository;

    private Map<String, Stock> stockData = new HashMap<>();

    private final SimpMessagingTemplate messagingTemplate;

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
            stock.setPrice(newPrice);

            //Save to DB
            StockEntity entity = new StockEntity(stock.getSymbol(),stock.getPrice(), LocalDateTime.now());

            stockRepository.save(entity);

            // Send Update to client
            messagingTemplate.convertAndSend("/topic/prices", stock);
        }
        System.out.println("Prices updated and pushed via WebSocket");
    }

    public StockService(StockRepository stockRepository, SimpMessagingTemplate messagingTemplate) {
        this.stockRepository = stockRepository;
        this.messagingTemplate = messagingTemplate;
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
