package com.teammacc.stock.controller;

import com.teammacc.stock.data.vo.StockVO;
import com.teammacc.stock.service.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/stock")
public class StockController {
	
	private final StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@ApiOperation(value = "Retorna a entidade especificada pelo ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a entidade especificada pelo ID"),
			@ApiResponse(code = 400, message = "No records found for this ID"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
	})
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public StockVO findById(@PathVariable("id") Long id) {
		StockVO stockVO = stockService.findById(id);
		stockVO.add(linkTo(methodOn(StockController.class).findById(id)).withSelfRel());
		return stockVO;
	}

	@ApiOperation(value = "Retorna a entidade especificada pelo ID do produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a entidade especificada pelo idProduto"),
			@ApiResponse(code = 400, message = "No records found for this ID"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
	})
	@GetMapping(value = "/consultar/{idProduto}", produces = {"application/json","application/xml","application/x-yaml"})
	public StockVO consultar(@PathVariable("idProduto") Long idProduto) {
		return stockService.findByIdProduto(idProduto);
	}

	@ApiOperation(value = "Verifica se o produto informado possui em estoque, pelo menos, a quantidade informada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "true - Indica que o estoque do porduto especificado possui a quantidade especificada"),
			@ApiResponse(code = 400, message = "No records found for this ID"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
	})
	@GetMapping(value = "/verificar/{idProduto}/{quantidade}")
	public Boolean verificar(@PathVariable("idProduto") Long idProduto, @PathVariable("quantidade") Integer quantidade) {
		return stockService.verificar(idProduto, quantidade);
	}

	@ApiOperation(value = "Reduiz o estoque do produto informado na quantidade informada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Indica que o estoque do porduto especificado foi baixado da quantidade especificada"),
			@ApiResponse(code = 400, message = "No records found for this ID"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
	})
	@PutMapping(value = "/baixar/{idProduto}/{quantidade}")
	public StockVO baixar(@PathVariable("idProduto") Long idProduto, @PathVariable("quantidade") Integer quantidade) {
		return stockService.baixar(idProduto, quantidade);
	}

	@ApiOperation(value = "Salva um novo estoque no valor da quantidade informada para o produto informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Indica que o estoque do porduto especificado agora é igual a quantidade especificada"),
			@ApiResponse(code = 400, message = "No records found for this ID"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
	})
	@PutMapping(value = "/atualizar/{idProduto}/{quantidade}")
	public StockVO cadastrar(@PathVariable("idProduto") Long idProduto, @PathVariable("quantidade") Integer quantidade) {
		return stockService.atualizar(idProduto, quantidade);
	}

	@ApiOperation(value = "Acrescenta mais a quantidade informada para o estoque do produto informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Indica que o estoque do porduto especificado agora é tem mais a igual a quantidade especificada"),
			@ApiResponse(code = 400, message = "No records found for this ID"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor"),
	})
	@PutMapping(value = "/acrescentar/{idProduto}/{quantidade}")
	public StockVO acrescentar(@PathVariable("idProduto") Long idProduto, @PathVariable("quantidade") Integer quantidade) {
		return stockService.acrescentar(idProduto, quantidade);
	}
}
