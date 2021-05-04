package br.com.zupacademy.guilhermejcs.mercadolivre.fechamentocompra;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    private GatewayPagamento gatewayPagamento;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoASerComprado=" + produtoASerComprado +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gatewayPagamento=" + gatewayPagamento +
                ", transacoes=" + transacoes +
                '}';
    }

    public Compra(@NotNull @Valid Produto produtoASerComprado,
                  @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, GatewayPagamento gateway) {
        this.produtoASerComprado = produtoASerComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gatewayPagamento = gateway;
    }

    public Long getId() {
        return id;
    }

    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe um transação igual a essa processada " + novaTransacao);
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluída com sucesso");

        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacaosConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Tem mais de uma transação concluída com sucesso na compra"+this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacaosConcluidasComSucesso().isEmpty();
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getDonoProduto() {
        return produtoASerComprado.getDono();
    }
}
