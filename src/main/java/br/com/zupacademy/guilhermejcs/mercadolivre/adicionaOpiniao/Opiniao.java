package br.com.zupacademy.guilhermejcs.mercadolivre.adicionaOpiniao;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Min(1) @Max(5) int nota;
    private @NotBlank  String titulo;
    private @NotBlank @Size(max = 500) String descricao;
    @ManyToOne
    private @NotNull @Valid Produto produto;
    @ManyToOne
    private @NotNull @Valid Usuario consumidor;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) int nota,
                   @NotBlank String titulo,
                   @NotBlank @Size(max = 500) String descricao,
                   @NotNull @Valid Produto produto,
                   @NotNull @Valid Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", consumidor=" + consumidor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opiniao opiniao = (Opiniao) o;
        return Objects.equals(titulo, opiniao.titulo) && Objects.equals(descricao, opiniao.descricao) && Objects.equals(produto, opiniao.produto) && Objects.equals(consumidor, opiniao.consumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, produto, consumidor);
    }

    public Object getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }
}
