package br.com.zupacademy.guilhermejcs.mercadolivre.adicionaOpiniao;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

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
}
