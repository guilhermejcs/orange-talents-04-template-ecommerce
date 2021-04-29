package br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeUsuarioComEmailDuplicadoValidator implements Validator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ProibeUsuarioComEmailDuplicadoValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoUsuarioResquest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoUsuarioResquest request = (NovoUsuarioResquest) target;
        Optional<Usuario> possivelUsuario = usuarioRepository.findByEmail(request.getEmail());

        if (possivelUsuario.isPresent()) {
            errors.rejectValue("email", null, "ja existe este email no sistema");
        }
    }
}