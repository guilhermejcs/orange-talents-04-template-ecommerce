package br.com.zupacademy.guilhermejcs.mercadolivre.adicionaPergunta;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.NovaCaracteristicaRequest;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String titulo;
    @ManyToOne
    private @NotNull @Valid Usuario interessada;
    @ManyToOne
    private @NotNull @Valid Produto produto;
    private LocalDate instante;

    public Pergunta(@NotBlank String titulo,
                    @NotNull @Valid Usuario interessada,
                    @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.interessada = interessada;
        this.produto = produto;
        this.instante = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", interessada=" + interessada +
                ", produto=" + produto +
                '}';
    }

    public Usuario getInteressada() {
        return interessada;
    }

    public Usuario getDonoProduto(){
        return produto.getDono();
    }
}
