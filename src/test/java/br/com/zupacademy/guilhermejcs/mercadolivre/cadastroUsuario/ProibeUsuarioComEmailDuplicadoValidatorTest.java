package br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;
import java.util.stream.Stream;

public class ProibeUsuarioComEmailDuplicadoValidatorTest {

    @DisplayName("deveria lidar com email duplicado")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void teste1(Optional<Usuario> possivelUsuario, boolean esperado) throws Exception{
        UsuarioRepository usuarioRepository = Mockito
                .mock(UsuarioRepository.class);
        ProibeUsuarioComEmailDuplicadoValidator validator = new ProibeUsuarioComEmailDuplicadoValidator(
                usuarioRepository);
        Object target = new NovoUsuarioResquest("email@email.com.br","senhaa");
        Errors errors = new BeanPropertyBindingResult(target, "teste");
        Mockito.when(usuarioRepository.findByEmail("email@email.com.br"))
                .thenReturn(possivelUsuario);

        validator.validate(target, errors);

        Assertions.assertEquals(esperado, errors.hasFieldErrors("email"));
    }

    private static Stream<Arguments>geradorTeste1(){
        Optional<Usuario> usuario = Optional.of(new Usuario("email@email.com.br",
                new SenhaLimpa("senhaa")));
        return Stream.of(Arguments.of(usuario,true),
                Arguments.of(Optional.empty(),false));
    }

}
