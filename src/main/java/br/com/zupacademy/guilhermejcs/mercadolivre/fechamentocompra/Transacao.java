package br.com.zupacademy.guilhermejcs.mercadolivre.fechamentocompra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusTransacao status;
    private @NotBlank String idTransacaoGateway;
    @NotNull
    private LocalDateTime instante;
    @ManyToOne
    private @NotNull @Valid Compra compra;

    @Deprecated
    public Transacao() {
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacaoGateway='" + idTransacaoGateway + '\'' +
                ", instante=" + instante +
                '}';
    }

    public Transacao(@NotNull StatusTransacao status,
                     @NotBlank String idTransacaoGateway,
                     @NotNull @Valid Compra compra){
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
