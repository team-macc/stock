package com.teammacc.stock.controller;

import com.teammacc.stock.data.vo.StockVO;
import com.teammacc.stock.service.StockService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Api( tags = "Stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Retorna a entidade especificada pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a entidade especificada pelo ID"),
            @ApiResponse(code = 400, message = "Nenhum registro encotrado para o ID especificado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
    })
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public StockVO findById(@ApiParam(value = "Número do ID do estoque que se deseja recuperar") @PathVariable("id") Long id) {
        return stockService.findById(id);
    }

    @ApiOperation(value = "Retorna a entidade especificada pelo ID do produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a entidade especificada pelo idProduto"),
            @ApiResponse(code = 400, message = "Nenhum registro encotrado para o ID especificado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
    })
    @GetMapping(value = "/consultar/{idProduto}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public StockVO findByIdProduto(@ApiParam(value = "Número do ID do produto que se deseja recuperar") @PathVariable("idProduto") Long idProduto) {
		return stockService.findByIdProduto(idProduto);
    }

    @ApiOperation(value = "Verifica se o produto informado possui em estoque, pelo menos, a quantidade informada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "true - Indica que o estoque do porduto especificado possui a quantidade especificada"),
            @ApiResponse(code = 400, message = "Nenhum registro encotrado para o ID especificado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
    })
    @GetMapping(value = "/verificar/{idProduto}/{quantidade}")
    public Boolean verificar(@ApiParam(value = "Número do ID do produto que se deseja verificar estoque") @PathVariable("idProduto") Long idProduto,
							 @ApiParam(value = "Quantidade do estoque que se deseja verificar a existência") @PathVariable("quantidade") Integer quantidade) {
        return stockService.verificar(idProduto, quantidade);
    }

    @ApiOperation(value = "Reduz o estoque do produto informado na quantidade informada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Indica que o estoque do porduto especificado foi baixado da quantidade especificada"),
            @ApiResponse(code = 400, message = "Nenhum registro encotrado para o ID especificado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
    })
    @PutMapping(value = "/baixar/{idProduto}/{quantidade}")
    public StockVO baixar(@ApiParam(value = "Número do ID do produto que se deseja baixar o estoque") @PathVariable("idProduto") Long idProduto,
						  @ApiParam(value = "Quantidade do estoque que se deseja baixar") @PathVariable("quantidade") Integer quantidade) {
        return stockService.baixar(idProduto, quantidade);
    }

    @ApiOperation(value = "Salva um novo estoque no valor da quantidade informada para o produto informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Indica que o estoque do porduto especificado foi atualizado para a quantidade especificada"),
            @ApiResponse(code = 400, message = "Nenhum registro encotrado para o ID especificado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
    })
    @PutMapping(value = "/atualizar/{idProduto}/{quantidade}")
    public StockVO cadastrar(@ApiParam(value = "Número do ID do produto que se deseja cadastrar estoque") @PathVariable("idProduto") Long idProduto,
							 @ApiParam(value = "Quantidade do estoque que se deseja cadastrar") @PathVariable("quantidade") Integer quantidade) {
        return stockService.atualizar(idProduto, quantidade);
    }

    @ApiOperation(value = "Acrescenta mais a quantidade informada para o estoque do produto informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Indica que o estoque do porduto especificado foi acrescido da quantidade especificada"),
            @ApiResponse(code = 400, message = "Nenhum registro encotrado para o ID especificado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
    })
    @PutMapping(value = "/acrescentar/{idProduto}/{quantidade}")
    public StockVO acrescentar(@ApiParam(value = "Número do ID do produto que se deseja acrescentar estoque") @PathVariable("idProduto") Long idProduto,
							   @ApiParam(value = "Quantidade do estoque que se deseja acrescentar") @PathVariable("quantidade") Integer quantidade) {
        return stockService.acrescentar(idProduto, quantidade);
    }
}
