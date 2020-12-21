package com.teammacc.stock.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

@JsonPropertyOrder({"idProduto", "possuiEstoque"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StockVerificationVO implements Serializable{

	private static final long serialVersionUID = 5098670473577699777L;

	@JsonProperty("idProduto")
	private Long idProduto;

	@JsonProperty("possuiEstoque")
	private Boolean possuiEstoque;

}
