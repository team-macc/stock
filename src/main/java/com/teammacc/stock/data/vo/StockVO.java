package com.teammacc.stock.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.teammacc.stock.entity.Stock;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id","idProduto", "quantidadeProduto"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StockVO extends RepresentationModel<StockVO> implements Serializable{

	private static final long serialVersionUID = 5098670473577699777L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("idProduto")
	private Long idProduto;

	@JsonProperty("quantidadeProduto")
	private Integer quantidadeProduto;
	
	public static StockVO create(Stock stock) {
		return new ModelMapper().map(stock, StockVO.class);
	}
}
