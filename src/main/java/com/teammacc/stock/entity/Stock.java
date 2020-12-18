package com.teammacc.stock.entity;


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
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    @Column(name = "quantidade_produto", nullable = false)
    private Integer quantidadeProduto;
}
