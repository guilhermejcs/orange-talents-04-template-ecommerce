package br.com.zupacademy.guilhermejcs.mercadolivre.cadastraUsuario;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioResquest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioResquest(@Email @NotBlank String email,
                               @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        return new Usuario(email, new SenhaLimpa(senha));
    }
}
