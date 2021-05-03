package br.com.zupacademy.guilhermejcs.mercadolivre.detalheProduto;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {

    private String nome;
    private String descricao;

    public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
