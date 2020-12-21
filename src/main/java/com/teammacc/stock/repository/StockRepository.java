package com.teammacc.stock.repository;

import com.teammacc.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long>  {

    Optional<Stock> findByIdProduto(Long idProduto);
}
