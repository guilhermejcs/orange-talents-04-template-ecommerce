package br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(@Email @NotBlank String email,
                   @Valid @NotBlank SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "email não pode ser em branco");
        Assert.notNull(senhaLimpa, "o objeto do tipo senha limpa não pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
