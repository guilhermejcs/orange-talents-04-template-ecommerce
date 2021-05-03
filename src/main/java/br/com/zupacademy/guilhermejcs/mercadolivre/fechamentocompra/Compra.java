package br.com.zupacademy.guilhermejcs.mercadolivre.fechamentocompra;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoASerComprado;
    @Positive
    private int quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;
    @Enumerated
    @NotNull
    private final GatewayPagamento gatewayPagamento;

    public Compra(@NotNull @Valid Produto produtoASerComprado,
                  @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, GatewayPagamento gateway) {
        this.produtoASerComprado = produtoASerComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gatewayPagamento = gateway;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoASerComprado=" + produtoASerComprado +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                '}';
    }

    public Long getId() {
        return id;
    }
}
