package br.com.zupacademy.guilhermejcs.mercadolivre.adicionaOpiniao;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class NovaOpiniaoRequest {

    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(@Min(5) @Max(5) int nota,
                              @NotBlank String titulo,
                              @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
        return new Opiniao(nota, titulo, descricao, produto, consumidor);
    }
}
