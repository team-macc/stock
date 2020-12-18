package com.teammacc.stock.repository;

import com.teammacc.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long>  {

    //@Query("select s from Stock s where s.idProduto = :idProduto")
    //Optional<Stock> findByIdDoProduto(@Param("idProduto") Long idProduto);
    Optional<Stock> findByIdProduto(Long idProduto);
}
