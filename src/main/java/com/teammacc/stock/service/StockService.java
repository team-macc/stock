package com.teammacc.stock.service;

import com.teammacc.stock.data.vo.StockVO;
import com.teammacc.stock.entity.Stock;
import com.teammacc.stock.exception.ResourceNotFoundException;
import com.teammacc.stock.exception.StockNotFoundException;
import com.teammacc.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockVO findById(Long id) {

        var entity = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encotrado para o ID especificado"));

        return StockVO.create(entity);
    }

    private StockVO convertToStockVO(Stock stock) {
        return StockVO.create(stock);
    }

    public StockVO findByIdProduto(Long idProduto) {
        var entity = stockRepository.findByIdProduto(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encotrado para o ID especificado"));
        return StockVO.create(entity);
    }

    public StockVO baixar(Long idProduto, Integer quantidade) {
        var entity = stockRepository.findByIdProduto(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encotrado para o ID especificado"));
        verificarEstoque(entity, quantidade);
        var quantidadeProduto = entity.getQuantidadeProduto();
        if (quantidadeProduto == 0) {
            throw new StockNotFoundException("No stock found for this product");
        }
        entity.setQuantidadeProduto(quantidadeProduto - quantidade);
        stockRepository.save(entity);
        return StockVO.create(entity);
    }

    public StockVO atualizar(Long idProduto, Integer quantidade) {
        var entity = stockRepository.findByIdProduto(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encotrado para o ID especificado"));
        entity.setQuantidadeProduto(quantidade);
        stockRepository.save(entity);
        return StockVO.create(entity);
    }

    public StockVO acrescentar(Long idProduto, Integer quantidade) {
        var entity = stockRepository.findByIdProduto(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encotrado para o ID especificado"));
        var quantidadeProduto = entity.getQuantidadeProduto();
        entity.setQuantidadeProduto(quantidade + quantidadeProduto);
        stockRepository.save(entity);
        return StockVO.create(entity);
    }

    public Boolean verificar(Long idProduto, Integer quantidade) {
        var entity = stockRepository.findByIdProduto(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encotrado para o ID especificado"));
        verificarEstoque(entity, quantidade);
        return true;
    }

    private void verificarEstoque(Stock entity, Integer quantidade) {
        var quantidadeProduto = entity.getQuantidadeProduto();
        if (quantidadeProduto < quantidade) {
            throw new StockNotFoundException("Estoque encotrado é inferior a quantidade informada para o produto especificado");
        }
    }
}
