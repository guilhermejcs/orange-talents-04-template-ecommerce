package br.com.zupacademy.guilhermejcs.mercadolivre.adicionaPergunta;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.NovaCaracteristicaRequest;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String titulo;
    @ManyToOne
    private @NotNull @Valid Usuario interessada;
    @ManyToOne
    private @NotNull @Valid Produto produto;
    private LocalDate instante;

    @Deprecated
    public Pergunta() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(titulo, pergunta.titulo) && Objects.equals(interessada, pergunta.interessada) && Objects.equals(produto, pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, interessada, produto);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }

    public String getTitulo() {
        return titulo;
    }
}
