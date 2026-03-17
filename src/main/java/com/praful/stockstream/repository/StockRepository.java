package com.praful.stockstream.repository;

import com.praful.stockstream.model.Stock;
import com.praful.stockstream.model.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

}
