package br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastrocategorias.Categoria;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @Positive int quantidade;

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    private @NotBlank @Length(max = 1000) String descricao;
    private @NotNull @Positive BigDecimal valor;
    @ManyToOne
    private @NotNull @Valid Categoria categoria;
    @ManyToOne
    private @NotNull @Valid Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    public Produto(@NotBlank String nome,
                   @Positive int quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario dono,
                   @Size(min = 3) @Valid Collection<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = dono;
        this.caracteristicas.addAll(caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa te no mínimo 3 ou mais características");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
