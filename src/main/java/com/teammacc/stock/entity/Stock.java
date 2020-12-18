package com.teammacc.stock.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "stock")
@ApiModel(description = "Detalhes sobre o estoque")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="ID único do estoque")
    private Long id;

    @Column(name = "id_produto", nullable = false)
    @ApiModelProperty(notes="ID único do produto")
    private Long idProduto;

    @Column(name = "quantidade_produto", nullable = false)
    @ApiModelProperty(notes="Quantidade do estoque do produto")
    private Integer quantidadeProduto;
}
